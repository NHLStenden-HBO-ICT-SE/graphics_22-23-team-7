import classes.DrawingHelper;
import classes.math.Point3D;
import classes.math.Vector3D;
import classes.objects.Sphere;
import classes.view.Camera;

public class App {
    public static void main(String[] args) throws Exception {
        //view direction
        Vector3D direction = new Vector3D(0, 0, 1);

        //sphere
        Point3D originS = new Point3D(0, 0, 12);
        Sphere sphere = new Sphere(originS, 1);

        //init drawinghelper
        DrawingHelper dh = new DrawingHelper();

        //init camera
        Camera camera = new Camera(direction, 4F, dh.getHeight(), dh.getWidth());

        int lastHeight = dh.getHeight();
        int lastWidth = dh.getWidth();

        //repeatedly draw scene
        while (true) {
            //check if window size changed
            if (lastHeight != dh.getHeight() || lastWidth != dh.getWidth()) {
                camera = new Camera(direction, 4F, dh.getHeight(), dh.getWidth()); //make new camera with proper canvas
                lastHeight = dh.getHeight();
                lastWidth = dh.getWidth();
            }
            if (dh.draw(camera, sphere)) {
                Thread.sleep(1500);
                dh.blank();
            }
        }
    }
}

