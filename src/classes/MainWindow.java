package classes;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    // Initialize the main window
    public MainWindow() {
        this(640, 480);
    }

    public MainWindow(int width, int height) {
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void Draw(int x, int y, Color color) {
        Graphics g = this.getGraphics();
        g.setColor(color);
        g.drawRect(x, y, 1, 1);
    }
}
