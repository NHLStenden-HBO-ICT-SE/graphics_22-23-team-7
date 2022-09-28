package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainWindow extends JFrame {

    private final JLabel iLabel;
    private BufferedImage image;

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
    }

    public void update() {
        // Fetch new image from pointer and display
        this.repaint();
    }

    // Blank the screen
    public void blank() {
        this.image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.update();
    }
}
