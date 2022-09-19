import classes.DrawingHelper;
import classes.math.Point3D;
import classes.math.Vector3D;
import classes.objects.Sphere;
import classes.view.Camera;

public class App {
    public static void main(String[] args) throws Exception {
//        DrawingHelper dh = new DrawingHelper(1280, 720);
//        while (true) {
//            if (dh.Draw()) {
//                Thread.sleep(1500);
//                dh.Blank();
//            }
//}
        //view direction
        Vector3D direction = new Vector3D(0, 0, 1);

        //sphere
        Point3D originS = new Point3D(0, 0, 12);
        Sphere sphere = new Sphere(originS, 1);

        //init drawinghelper
        DrawingHelper dh = new DrawingHelper();

        //init camera
        var camera = new Camera(direction, 4F);

        //draw sphere
        while (true) {
            if (dh.Draw(camera, sphere)) {
                Thread.sleep(1500);
                dh.Blank();
            }
        }

    }
}

