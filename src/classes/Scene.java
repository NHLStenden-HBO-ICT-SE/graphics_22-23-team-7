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

    /**
     * calculates color of pixel that rays shoots through
     * @param ray
     * @return color of pixel
     */
    public Color calculatePixel(Ray ray) {

        var closestShape = getClosestShape(ray);

        if (!closestShape.isIntersected()) return Color.black;

        Point3D intersectionPoint = ray.getOrigin().addVector(ray.getDirection().multiply(closestShape.getLength()));

        double intensity = calculateIntensity(intersectionPoint);

        return new Color(clamp((int) (255 * intensity), 0, 255), clamp((int) (255 * intensity), 0, 255), clamp((int) (255 * intensity), 0, 255));
    }

    private IntersectionHandler getClosestShape(Ray ray) {
        //init closest shape
        var closestShape = new IntersectionHandler(false, Double.MAX_VALUE);

        //loop through shapes
        for (Shape shape : shapes) {

            //get intersection of current shape
            IntersectionHandler currentShape = shape.intersection(ray);

            //go to next shape if ray doesn't hit object
            if (!currentShape.isIntersected()) continue;

            //if current shape length is smaller than closest shape length then set closest shape to current shape
            if (currentShape.getLength() < closestShape.getLength()) closestShape = currentShape;

        }
        return closestShape;
    }

    /**
     * calculates intensity of light on point
     * @param point
     * @return intensity
     */
    private double calculateIntensity(Point3D point) {
        //init intensity
        double intensity = 0;

        //loop through lights
        for (Light light : lights) {

            //get normalized point light direction vector
            Vector3D olDirection = point.getVector(light.getPosition()).normalize();

            //make ray from point to light
            Ray ray = new Ray(point, olDirection, point.distance(light.getPosition()));

            //loop through shapes
            for (Shape shape : shapes) {

                //if shape is in the way of light go to the next
                if (shape.intersection(ray).isIntersected()) continue;

                //get the normal vector from shape
                Vector3D N = shape.getOrigin().getVector(point).normalize();

                //get the angle between normal and direction
                double angle = N.dot(olDirection);

                //if angle > 90 degrees go to the next
                if (angle < 0) continue;

                //incr intensity
                intensity += angle * light.inverseSquareLaw(ray);

            }
        }
        return intensity;
    }


}
