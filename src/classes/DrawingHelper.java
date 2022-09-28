package classes;

import classes.math.Point3D;
import classes.math.Ray;
import classes.math.Vector3D;
import classes.objects.IntersectionHandler;
import classes.objects.Sphere;
import classes.view.Camera;
import classes.view.Light;

import java.awt.*;

import static classes.math.GenericMath.*;

public class DrawingHelper {
    private final MainWindow window;

    //*****************************
    // Constructors
    //*****************************

    public DrawingHelper() {
        this(480, 480);
    }

    // Initialize DrawingHelper and window at a specified size
    public DrawingHelper(int width, int height) {
        this.window = new MainWindow(width, height);
    }

    public boolean draw(Camera camera, Sphere sphere, Light light) {
        // Render

        for (int j = 0; j < this.window.getHeight(); j++) {
            for (int i = 0; i < this.window.getWidth(); i++) {

                Ray ray = camera.makeRay((double) i / this.window.getWidth(), (double) j / this.window.getHeight());
                IntersectionHandler intersectionHandler = sphere.intersection(ray);

                //check if ray intersects with sphere
                if (intersectionHandler.isIntersected()) {

                    //get point of intersection of sphere
                    Point3D intersectionPoint = ray.getOrigin().addVector(ray.getDirection().multiply(intersectionHandler.getLength()));

                    //get direction from sphere to light
                    Vector3D directionslRay = intersectionPoint.getVector(light.getPosition());

                    //distance between light and sphere
                    double slDistance = intersectionPoint.distance(light.getPosition());

                    //create new ray from intersection to light
                    Ray slRay = new Ray(intersectionPoint, directionslRay, slDistance);

                    //get intersection from sphere and light
                    var slIntersectionHandler = sphere.intersection(slRay);

                    //init intensity
                    double intensity = 0;

                    if (!slIntersectionHandler.isIntersected())
                        intensity = light.calculateIntensity(slDistance);


                    //light on a black intersectionHandler
                    //new color should be moved to light
                    this.window.Draw(i, j, new Color(clamp((int) (255 * intensity), 0, 255), clamp((int) (255 * intensity), 0, 255), clamp((int) (255 * intensity), 0, 255)));
                }
            }
        }
        return true;
    }

    // Blank the screen
    public void blank() {
        this.window.blank();
    }

    public int getHeight() {
        return this.window.getHeight();
    }

    public int getWidth() {
        return this.window.getWidth();
    }
}
