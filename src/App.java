import classes.DrawingHelper;
import classes.Scene;
import classes.math.Point3D;
import classes.math.Vector3D;
import classes.objects.Sphere;
import classes.view.Camera;
import classes.view.Light;

import java.awt.*;
import java.awt.image.BufferedImage;

public class App {
    public static void main(String[] args) throws Exception {
        //view direction
        Vector3D direction = new Vector3D(0, 0, 1);

        //spheres
        Point3D originS = new Point3D(0, 1, 9);
        Point3D originS2 = new Point3D(-2.5, 1, 12);
        Sphere[] spheres = {
                new Sphere(originS, 0.3),
                new Sphere(originS2, 2)};

        //lights
        Point3D originL = new Point3D(2, 1, 5);
        Point3D originL2 = new Point3D(-2.5, 1, 7);
        Light[] lights = {
                new Light(10, originL),
                new Light(3, originL2)};

        //init drawinghelper
        DrawingHelper dh = new DrawingHelper(1000, 700);

        //init camera
        Point3D positionC = new Point3D();
        Camera camera = new Camera(direction,positionC, 4F, dh.getWidth(), dh.getHeight());

        //init scene
        Scene scene = new Scene(camera, spheres, lights);

        int lastHeight = dh.getHeight();
        int lastWidth = dh.getWidth();

        int lastFrameRate = 0,
            lastFrameTime = 0;

        //repeatedly draw scene
        while (true) {

            //starts timer
            long startTime = System.currentTimeMillis();

            //check if window size changed
            if (lastHeight != dh.getHeight() || lastWidth != dh.getWidth()) {
                scene.setCamera(new Camera(direction, positionC,4F, dh.getWidth(), dh.getHeight())); //make new camera with proper canvas
                lastHeight = dh.getHeight();
                lastWidth = dh.getWidth();
            }
            if (dh.drawScene(scene)) {
                BufferedImage image = dh.getImage();
                // Use image here for video file rendering, no performance overlay on screen.

            }
            // Sphere movement
            spheres[0].setPosition(spheres[0].getPosition().add(new Point3D(-0.01, 0, 0)));

            // Overlay here

            if (lastFrameRate != 0 && lastFrameTime != 0) {
                BufferedImage image = dh.getImage();
                Graphics g = image.getGraphics();
                g.setColor(Color.RED);
                g.drawRoundRect(5, 25, 160, 50, 5, 5);
                g.drawString(String.format("Framerate: %d", lastFrameRate), 15, 40);
                g.drawString(String.format("Frametime: %d ms", lastFrameTime), 15, 65);
            }

            dh.update();
            // Additional sleep as update returns before finishing render
            Thread.sleep(7, 500);
            //ends timer
            long endTime = System.currentTimeMillis();
            lastFrameRate = 1000 / (int)(endTime - startTime);
            lastFrameTime = (int)(endTime - startTime);
        }
    }
}

