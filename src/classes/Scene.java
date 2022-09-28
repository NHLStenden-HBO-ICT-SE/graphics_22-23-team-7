package classes;

import classes.math.Point3D;
import classes.math.Ray;
import classes.math.Vector3D;
import classes.objects.IntersectionHandler;
import classes.objects.Sphere;
import classes.view.Camera;
import classes.view.Light;

import java.awt.*;

import static classes.math.GenericMath.clamp;

public class Scene {
    private Camera camera;
    private Sphere[] spheres; //TODO: change to generic object later
    private Light[] lights;

    public Scene(Camera camera, Sphere[] spheres, Light[] lights) {
        this.camera = camera;
        this.spheres = spheres;
        this.lights = lights;
    }

    public Color calculatePixel(Ray ray) {
        for (Sphere sphere : spheres) {
            IntersectionHandler intersectionHandler = sphere.intersection(ray);

            if (!intersectionHandler.isIntersected()) return new Color(255, 255, 255);

            Point3D intersectionPoint = ray.getOrigin().addVector(ray.getDirection().multiply(intersectionHandler.getLength()));

            double intensity = calculateIntensity(intersectionPoint);

            return new Color(clamp((int) (255 * intensity), 0, 255), clamp((int) (255 * intensity), 0, 255), clamp((int) (255 * intensity), 0, 255));
        }
        return new Color(0, 0, 0); //TODO: try catch
    }


    private double calculateIntensity(Point3D origin) {
        double intensity = 0;
        for (Light light : lights) {

            Vector3D direction = origin.getVector(light.getPosition()).normalize();

            Ray ray = new Ray(origin, direction, origin.distance(light.getPosition()));

            for (Sphere sphere : spheres) {

                if (sphere.intersection(ray).isIntersected()) continue;

                Vector3D N = sphere.getCenter().getVector(origin).normalize();

                double angle = N.dot(direction);

                if (angle < 0) continue;

                intensity += angle * light.inverseSquareLaw(ray);

            }
        }
        return intensity;
    }


}
