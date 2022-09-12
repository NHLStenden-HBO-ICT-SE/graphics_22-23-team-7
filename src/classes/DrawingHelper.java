package classes;

import java.awt.*;

public class DrawingHelper {
    private final MainWindow window;
    public boolean Draw() {
        // Render

        for (int j = (int)(this.window.getHeight() - 1.0); j >= 0; --j) {
            for (int i = 0; i < this.window.getWidth(); ++i) {
                double r = i / ((double)this.window.getWidth()-1);
                double g = j / ((double)this.window.getHeight()-1);
                double b = 0.25;

                int ir = (int)(255.999 * r);
                int ig = (int)(255.999 * g);
                int ib = (int)(255.999 * b);
                this.window.Draw(i, j, new Color(ir, ig, ib));
            }
        }
        return true;
    }

    // Blank the screen
    public void Blank() {
        this.window.Blank();
    }

    // Initialize DrawingHelper and window at a specified size
    public DrawingHelper(int width, int height) {
        this.window = new MainWindow(width, height);
    }
}
