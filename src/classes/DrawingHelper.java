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

    public boolean draw(Camera camera, Sphere[] spheres, Light[] lights) {
        // Render
        for (int j = 0; j < this.window.getHeight(); j++) {
            for (int i = 0; i < this.window.getWidth(); i++) {

                Ray ray = camera.makeRay((double) i / this.window.getWidth(), (double) j / this.window.getHeight());
                Scene scene = new Scene(camera, spheres, lights);

                //light on a black intersectionHandler
                //new color should be moved to light
                this.window.Draw(i, j, scene.calculatePixel(ray));
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
