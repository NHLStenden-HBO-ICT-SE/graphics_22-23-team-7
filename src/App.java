import classes.DrawingHelper;
import classes.math.Point3D;
import classes.math.Vector3D;
import classes.objects.Sphere;
import classes.view.Camera;
import classes.view.Light;

public class App {
    public static void main(String[] args) throws Exception {
        //view direction
        Vector3D direction = new Vector3D(0, 0, 1);

        //sphere
        Point3D originS = new Point3D(1.5, 0, 9);
        Point3D originS2 = new Point3D(-1.5, 1, 12);
        Sphere[] sphere = {new Sphere(originS, 1), new Sphere(originS2, 1)};

        //light
        Point3D originL = new Point3D(2, 1, 5);
        Point3D originL2 = new Point3D(-1, 0, 7);
        Light[] light = {new Light(3, originL), new Light(2, originL2)};

        //init drawinghelper
        DrawingHelper dh = new DrawingHelper(1000, 700);

        //init camera
        Camera camera = new Camera(direction, 4F, dh.getHeight(), dh.getWidth());

        int lastHeight = dh.getHeight();
        int lastWidth = dh.getWidth();

        //repeatedly draw scene
        while (true) {

            //starts timer
            long startTime = System.currentTimeMillis();


            //check if window size changed
            if (lastHeight != dh.getHeight() || lastWidth != dh.getWidth()) {
                camera = new Camera(direction, 4F, dh.getHeight(), dh.getWidth()); //make new camera with proper canvas
                lastHeight = dh.getHeight();
                lastWidth = dh.getWidth();
            }
            if (dh.draw(camera, sphere, light)) {
                dh.update();
                // Additional sleep as update returns before finishing render
                Thread.sleep(7, 500);
            }
            // Sphere movement
//            sphere[0].setCenter(sphere[0].getCenter().add(new Point3D(0.01, 0, 0)));

            //ends timer
            long endTime = System.currentTimeMillis();

            //prints timer
            System.out.println(endTime - startTime);
        }
    }
}

