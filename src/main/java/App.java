import classes.DrawingHelper;
import classes.Recorder;
import classes.Scene;
import classes.math.Point3D;
import classes.math.Vector3D;
import classes.objects.Sphere;
import classes.solarSystem.Planet;
import classes.view.Camera;
import classes.view.Light;
import interfaces.objects.Shape;

import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws Exception {
        //stores fps and duration in seconds
        int frames = 60;
        int duration = 3;
        int totalFrames = (frames * duration);
        String documents = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
        Path recorderPath = Paths.get(documents, "nhlstenden", "solarsystem", "recordings");
        if (Files.notExists(recorderPath)) {
            Files.createDirectories(recorderPath);
        }
        int currentFrame = 0;
        Recorder recorder = new Recorder(frames);

        //view direction
        Vector3D direction = new Vector3D(0, 0, 1);

        //earth
        Point3D earthPos = new Point3D(0, 0, 9);
        Sphere earthS = new Sphere(earthPos, 1);
        Vector3D earthVel = new Vector3D(0, 0, 0);

        //satellite
        Point3D satPos = new Point3D(3.5, -0.5, 8);
        Sphere satS = new Sphere(satPos, 0.2);
        Vector3D satVel = new Vector3D(0, 0, 0.1);

        Shape[] spheres = {new Planet(0.8, earthVel, earthS), new Planet(0, satVel, satS),};

        //lights
        Point3D originL = new Point3D(-8, 1, 5);
        Light[] lights = {new Light(12, originL),};

        //init drawing-helper
        DrawingHelper dh = new DrawingHelper(700, 700);

        //init camera
        Point3D positionC = new Point3D(0, 0, -10);
        Camera camera = new Camera(direction, positionC, 4F, dh.getWidth(), dh.getHeight());

        //init scene
        Scene scene = new Scene(camera, spheres, lights);

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


                //for recording. stacks buffered images for later use and executes recording generator when duration has expired\
                if (currentFrame < totalFrames) {
                    //because of high resolution. the heap size can be too small. i have to catch the error since i cant seem to get the available heap size |Runtime.getRuntime().freeMemory()| correctly before it happends
                    try {
                        recorder.snapShot(Recorder.deepCopyBufferedImage(dh.getWindow().getImage()), recorderPath.toString(), currentFrame);

                        currentFrame++;
                    } catch (OutOfMemoryError e) {
                        //Todo: write error to screen
                        System.out.println("ERROR: OUT OF HEAP SPACE, STOPPING RECORDING...");
                        currentFrame = totalFrames + 1;
                    }

                } else if (currentFrame == totalFrames) {
                    //rendering into a mp4 file
                    String[] strings = new String[totalFrames];
                    for (int i = 0; i < totalFrames; i++) {
                        strings[i] = "frame" + i + ".png";
                    }
                    recorder.generateFromMemory(recorderPath.toString(), strings);
                    currentFrame++;
                }

            }

            //ends timer
            long endTime = System.currentTimeMillis();

            //prints timer
            System.out.println(1000 / (endTime - startTime));
        }
    }
}

