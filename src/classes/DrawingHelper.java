package classes;

import classes.math.Ray;
import classes.objects.Object;
import classes.objects.Sphere;
import classes.view.Camera;
import classes.view.Light;

import java.awt.*;

public class DrawingHelper {
    private final MainWindow window;

    //*****************************
    // Constructors
    //*****************************

    public DrawingHelper()
    {
        this(480, 480);
    }

    // Initialize DrawingHelper and window at a specified size
    public DrawingHelper(int width, int height)
    {
        this.window = new MainWindow(width, height);
    }

    public boolean draw(Camera camera, Sphere sphere, Light light) {
        // Render

        for (int j = 0; j < this.window.getHeight(); j++) {
            for (int i = 0; i < this.window.getWidth(); i++) {

                Ray ray = camera.makeRay((double) i / this.window.getWidth(), (double) j / this.window.getHeight());
                Object object = sphere.intersection(ray);

                //check if ray intersects with sphere
                if (object.intersects) {

                    double intensity = light.getIntensity(object.point);

                    //light on a black object
                    //new color should be moved to light
                    this.window.Draw(i, j,  new Color((int) (255 * intensity), (int) (255 * intensity), (int) (255 * intensity)));
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
