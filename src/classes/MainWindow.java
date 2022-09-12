package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainWindow extends JFrame {

    private BufferedImage image;
    private final JLabel iLabel;

    // Initialize the main window with a preset size
    public MainWindow() {
        this(640, 480);
    }

    // Initialize the main window with a specific size
    public MainWindow(int width, int height) {
        this.setSize(width, height);
        this.iLabel = new JLabel();
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.iLabel.setIcon(new ImageIcon(this.image));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(this.iLabel);
        this.setVisible(true);
    }

    // Draw a pixel
    public void Draw(int x, int y, Color color) {
        this.image.setRGB(x, y, color.getRGB());
        this.iLabel.setIcon(new ImageIcon(this.image));
    }

    // Blank the screen
    public void Blank() {
        this.image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.iLabel.setIcon(new ImageIcon(this.image));
    }
}
