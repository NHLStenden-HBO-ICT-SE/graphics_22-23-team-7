import classes.DrawingHelper;
import classes.Scene;
import classes.math.Point3D;
import classes.math.Vector3D;
import classes.objects.Sphere;
import classes.solarSystem.Gravity;
import classes.solarSystem.Planet;
import classes.view.Camera;
import classes.view.Light;

public class App {
    public static void main(String[] args) throws Exception {
        //
        // Background
        //
        var posB = new Point3D(-2.5, 2, 42); //position

        var sphereB = new Sphere(posB, 16);

        //mass
        double massB = 0; //massless object

        //velocity
        var velB = new Vector3D(0, 0, 0); //no velocity

        //
        // small moon
        //
        var posM = new Point3D(-3, 0, 3); //position

        var sphereM = new Sphere(posM, 0.3);

        //mass
        double massM = 0; //massless object

        //velocity
        var velM = new Vector3D(0.1, 0.1, 0);


        //
        // big moon
        //
        var posBigM = new Point3D(0, 0, 10); //position

        var sphereBigM = new Sphere(posBigM, 2);

        //mass
        double massBigM = 2;

        //velocity
        var velBigM = new Vector3D(0, 0, 0);

        //
        // planets
        //
        Planet[] planets = {
                new Planet(massM, velM, sphereM),
                new Planet(massBigM, velBigM, sphereBigM),
                new Planet(massB, velB, sphereB, true),
        };

        //lights
        Point3D originL = new Point3D(-4, -3, -1.3);
        Point3D originL2 = new Point3D(3, 3, 10.5);
        Light[] lights = {

                new Light(69, originL), //light1
                new Light(12, originL2) //light2
        };

        //init drawinghelper
        DrawingHelper dh = new DrawingHelper(1000, 700);

        //
        // init camera
        //
        Vector3D direction = new Vector3D(0, 0, 1); //view direction

        Point3D positionC = new Point3D(0, 0, -50); //position

        Camera camera = new Camera(direction, positionC, 4F, dh.getWidth(), dh.getHeight());

        //init scene
        Scene scene = new Scene(camera, planets, lights);

        int lastHeight = dh.getHeight();
        int lastWidth = dh.getWidth();

        //repeatedly draw scene
        while (true) {

            //starts timer
            long startTime = System.currentTimeMillis();

            //check if window size changed
            if (lastHeight != dh.getHeight() || lastWidth != dh.getWidth()) {
                scene.setCamera(new Camera(direction, positionC, 4F, dh.getWidth(), dh.getHeight())); //make new camera with proper canvas
                lastHeight = dh.getHeight();
                lastWidth = dh.getWidth();
            }
            if (dh.drawScene(scene)) {
                dh.update();
                // Additional sleep as update returns before finishing render
                Thread.sleep(7, 500);
            }

            // planet movement
            Gravity.movePlanets(planets);

            //ends timer
            long endTime = System.currentTimeMillis();

            //prints timer
            System.out.println(1000 / (endTime - startTime));
        }
    }
}

