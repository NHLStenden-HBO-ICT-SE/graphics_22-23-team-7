package classes;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class DrawingHelper implements ComponentListener {
    MainWindow window;
    boolean halt;
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

                // Check if the window has been resized or moved, which usually disrupts the current image.
                if (halt) {
                    halt = false;
                    return false;
                }

                // Fallback check for colors above 255, indicates an overflow caused by resizing the window.
                if (ir > 255 || ig > 255 || ib > 255) {
                    halt = false;
                    return false;
                }
                this.window.Draw(i, j, new Color(ir, ig, ib));
            }
        }
        return true;
    }

    public void Blank() {
        this.window.removeComponentListener(this);
        this.window.setSize(this.window.getWidth(), this.window.getHeight() - 1);
        this.window.setSize(this.window.getWidth(), this.window.getHeight() + 1);
        this.window.addComponentListener(this);

    }

    public DrawingHelper(int width, int height) {
        this.window = new MainWindow(width, height);
        this.window.addComponentListener(this);
        this.halt = false;
    }

    @Override
    public void componentResized(ComponentEvent e) { this.halt = true; }

    @Override
    public void componentMoved(ComponentEvent e) { this.halt = true; }

    @Override
    public void componentShown(ComponentEvent e) { }

    @Override
    public void componentHidden(ComponentEvent e) { }
}
