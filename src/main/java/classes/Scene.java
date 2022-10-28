package classes;

import classes.math.Point3D;
import classes.math.Ray;
import classes.math.Vector3D;
import classes.objects.IntersectionHandler;
import classes.objects.Sphere;
import classes.objects.Triangle;
import classes.view.Camera;
import classes.view.Light;
import interfaces.objects.Shape;

import java.awt.*;
import java.util.Arrays;

import static classes.math.GenericMath.clamp;

public class Scene {
    private final Shape[] shapes;
    private final Light[] lights;
    private Camera camera;

    public Scene(Camera camera, Shape[] shapes, Light[] lights) {
        this.camera = camera;
        this.shapes = shapes;
        this.lights = lights;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * calculates color of pixel that rays shoots through
     *
     * @param ray
     * @return color of pixel
     */
    public Color calculatePixel(Ray ray) {

        var closestShape = getClosestShape(ray);


        if (!closestShape.isIntersected()) return Color.black;

        double intensity = calculateIntensity(closestShape);

        var shape = closestShape.getShape().getColor();

        return new Color(clamp((int) (shape.getRed() * intensity), 0, 255), clamp((int) (shape.getGreen() * intensity), 0, 255), clamp((int) (shape.getBlue() * intensity), 0, 255));
    }

    /**
     * finds the closest shape
     * @param ray
     * @return
     */
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
     *
     * @param intersection
     * @return
     */
    private double calculateIntensity(IntersectionHandler intersection) {
        //init intensity
        double intensity = 0;

        //loop through lights
        for (Light light : lights) {

            var shape = intersection.getShape();

            Point3D intersectionPoint = intersection.calculateIntersectionPoint();

            Point3D lightPos = light.getPosition();

            //get normalized intersectionPoint light direction vector
            Vector3D olDirection = intersectionPoint.getVector(lightPos).normalize();

            //get the normal vector from shape
            Vector3D N = shape.calcNormal(intersectionPoint).normalize();

            //get the angle between normal and direction
            double angle = N.dot(olDirection);

            //if angle > 90 degrees go to the next
            if (angle < 0 ) continue;

            Ray ray = new Ray(intersectionPoint, intersectionPoint.getVector(lightPos), intersectionPoint.distance(lightPos));

            //if shape is in the way of light go to the next
            if (isLightBlocked(ray)) continue;

            //incr intensity
            intensity += angle * light.inverseSquareLaw(ray);

        }
        return intensity;
    }

    /**
     * calculates if light is blocked
     * @param ray
     * @return
     */
    private boolean isLightBlocked(Ray ray) {
        //loop through shapes
        for (Shape shape : shapes) {

            //if distance between point and shape origin is bigger than ray length we skip to the next shape
            if (ray.getPosition().distance(shape.getPosition()) > ray.getLength()) continue;

            //get intersection of current shape
            IntersectionHandler currentShape = shape.intersection(ray);

            //if ray intersects return true
            if (currentShape.isIntersected())
                return true;

        }

        return false;
    }
}