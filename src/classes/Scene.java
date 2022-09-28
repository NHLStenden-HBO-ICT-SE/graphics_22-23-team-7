package classes;

import classes.math.Point3D;
import classes.math.Ray;
import classes.math.Vector3D;
import classes.objects.IntersectionHandler;
import classes.view.Camera;
import classes.view.Light;
import interfaces.objects.Shape;

import java.awt.*;

import static classes.math.GenericMath.clamp;

public class Scene {
    private final Camera camera;
    private final Shape[] shapes;
    private final Light[] lights;

    public Scene(Camera camera, Shape[] shapes, Light[] lights) {
        this.camera = camera;
        this.shapes = shapes;
        this.lights = lights;
    }

    public Color calculatePixel(Ray ray) {

        var closestShape = getClosestShape(ray);

        if (!closestShape.isIntersected()) return Color.black;

        Point3D intersectionPoint = ray.getOrigin().addVector(ray.getDirection().multiply(closestShape.getLength()));

        double intensity = calculateIntensity(intersectionPoint);

        return new Color(clamp((int) (255 * intensity), 0, 255), clamp((int) (255 * intensity), 0, 255), clamp((int) (255 * intensity), 0, 255));
    }

    private IntersectionHandler getClosestShape(Ray ray) {
        var closestShape = new IntersectionHandler(false, Double.MAX_VALUE);

        for (Shape shape : shapes) {

            IntersectionHandler currentShape = shape.intersection(ray);

            //go to next shape if ray doesn't hit object
            if (!currentShape.isIntersected()) continue;

            //if current shape length is smaller than closest shape length then set closest shape to current shape
            if (currentShape.getLength() < closestShape.getLength()) closestShape = currentShape;

        }
        return closestShape;
    }

    private double calculateIntensity(Point3D origin) {
        double intensity = 0;
        for (Light light : lights) {

            Vector3D direction = origin.getVector(light.getPosition()).normalize();

            Ray ray = new Ray(origin, direction, origin.distance(light.getPosition()));

            intensity = 0;

            for (Shape shape : shapes) {

                if (shape.intersection(ray).isIntersected()) continue;

                Vector3D N = shape.getOrigin().getVector(origin).normalize();

                double angle = N.dot(direction);

                if (angle < 0) continue;

                intensity += angle * light.inverseSquareLaw(ray);

            }
        }
        return intensity;
    }


}
