package classes;

import classes.objects.Sphere;
import classes.view.Camera;

public class DrawingHelper {
    private final MainWindow window;

    // Initialize DrawingHelper and window at a specified size
    public DrawingHelper(int width, int height) {
        this.window = new MainWindow(width, height);
    }

    public DrawingHelper() {
        this(480,480);
    }

    public boolean draw(Camera camera, Sphere sphere) {
        // Render

        for (int j = 0; j < this.window.getHeight(); j++) {
            for (int i = 0; i < this.window.getWidth(); i++) {

                var ray = camera.makeRay((double) i / this.window.getWidth(), (double) j / this.window.getHeight());
                var intersection = sphere.intersection(ray);

                if (intersection) {

                    this.window.Draw(i, j, java.awt.Color.black);
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
