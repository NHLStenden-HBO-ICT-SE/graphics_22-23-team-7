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
        this.window = new MainWindow();
    }

    public boolean Draw(Camera camera, Sphere sphere) {
        // Render

        for (int j = 0; j < this.window.getHeight(); j++) {
            for (int i = 0; i < this.window.getWidth(); i++) {
//                double r = i / ((double) this.window.getWidth() - 1);
//                double g = j / ((double) this.window.getHeight() - 1);
//                double b = 0.25;
//
//                int ir = (int) (255.999 * r);
//                int ig = (int) (255.999 * g);
//                int ib = (int) (255.999 * b);

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
    public void Blank() {
        this.window.Blank();
    }
}
