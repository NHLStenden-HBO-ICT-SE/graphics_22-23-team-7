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
        var posB = new Point3D(0, 0, 32); //position

        var sphereB = new Sphere(posB, 13);

        //mass
        double massB = 0; //massless object

        //velocity
        var velB = new Vector3D(0, 0, 0); //no velocity

        //
        // small moon
        //
        var posM = new Point3D(-5, 0, 7); //position

        var sphereM = new Sphere(posM, 0.4);

        //mass
        double massM = 1; //massless object

        //velocity
        var velM = new Vector3D(0, 0.14988, 0.13);

        //
        // small moon2
        //
        var posM2 = new Point3D(0, 0, 7); //position

        var sphereM2 = new Sphere(posM2, 0.4);

        //mass
        double massM2 = 1; //massless object

        //velocity
        var velM2 = new Vector3D(-0.05, -0.1, 0);


        //
        // big moon
        //
        var posBigM = new Point3D(-2, 0, 5); //position

        var sphereBigM = new Sphere(posBigM, 0.4);

        //mass
        double massBigM = 1;

        //velocity
        var velBigM = new Vector3D(0.2, 0.1, 0);

        //
        // big moon
        //
        var posBigM2 = new Point3D(2, -5, 3); //position

        var sphereBigM2 = new Sphere(posBigM2, 0.4);

        //mass
        double massBigM2 = 1;

        //velocity
        var velBigM2 = new Vector3D(0.2, 0.1, 0);

        //
        // sun
        //
        var posS = new Point3D(0, -5, 0); //position

        var sphereS = new Sphere(posS, 0.4);

        //mass
        double massS = 1;

        //velocity
        var velS = new Vector3D(-0.05, -0.05, 0);

        //
        // planets
        //
        Planet[] planets = {
                new Planet(massM, velM, sphereM),
                new Planet(massM2, velM2, sphereM2),
                new Planet(massBigM, velBigM, sphereBigM),
                new Planet(massBigM2, velBigM2, sphereBigM2),
                new Planet(massS, velS, sphereS),
                new Planet(massB, velB, sphereB, true),
        };

        //lights
        Point3D originL = new Point3D(-4, -3, -5);
        Point3D originL2 = new Point3D(5, 10, -10);
        Point3D originL3 = new Point3D(-4, 0, 3);
        Light[] lights = {

                new Light(30.024, originL), //light1
                new Light(50.420, originL2), //light2
                new Light(30.69, originL3) //light3
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

