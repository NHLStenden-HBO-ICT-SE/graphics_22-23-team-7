import classes.DrawingHelper;
import classes.Recorder;
import classes.Scene;
import classes.math.Point3D;
import classes.math.Vector3D;
import classes.objects.Sphere;
import classes.view.Camera;
import classes.view.Light;

import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        //stores fps and duration in seconds
        int frames = 60;
        int duration = 3;
        int totalframes = (frames * duration);
        String documents = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
        Path recorderpath = Paths.get(documents, "nhlstenden", "solarsystem", "recordings");
        if (Files.notExists(recorderpath)) {
            Files.createDirectories(recorderpath);
        }
        long heapspace = Runtime.getRuntime().freeMemory();
        //ceep track of current frame
        int currentframe = 0;
        Recorder recorder = new Recorder(frames);
        // stores all bufferedimages. calculates length of video aswell
        BufferedImage[] images = new BufferedImage[totalframes];

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
        DrawingHelper dh = new DrawingHelper(2560, 1600);

        //init camera
        Point3D positionC = new Point3D();
        Camera camera = new Camera(direction,positionC, 4F, dh.getWidth(), dh.getHeight());

        //init scene
        Scene scene = new Scene(camera, spheres, lights);

        int lastHeight = dh.getHeight();
        int lastWidth = dh.getWidth();

        //repeatedly draw scene
        while (true) {
            heapspace = Runtime.getRuntime().freeMemory();
            //starts timer
            long startTime = System.currentTimeMillis();

            //check if window size changed
            if (lastHeight != dh.getHeight() || lastWidth != dh.getWidth()) {
                scene.setCamera(new Camera(direction, positionC,4F, dh.getWidth(), dh.getHeight())); //make new camera with proper canvas
                lastHeight = dh.getHeight();
                lastWidth = dh.getWidth();
            }
            if (dh.drawScene(scene)) {
                dh.update();
                // Additional sleep as update returns before finishing render
                Thread.sleep(7, 500);


                //for recording. stacks bufferedimages for later use and executes recording generator when duration has expired\
                if (currentframe < totalframes){
                    //because of high resolution. the heap size can be too small. i have to catch the error since i cant seem to get the available heap size |Runtime.getRuntime().freeMemory()| correctly before it happends
                  try {
                      recorder.snapShot(Recorder.deepCopyBufferedImage(dh.getWindow().getImage()), recorderpath.toString(), currentframe);
                     // images[currentframe] = recorder.deepCopyBufferedImage(dh.getWindow().getImage());
                      currentframe++;
                  }
                  catch (OutOfMemoryError e) {
                      //Todo: write error to screen
                      System.out.println("ERROR: OUT OF HEAP SPACE, STOPPING RECORDING...");
                     // images = null;
                      currentframe = totalframes+1;
                  }

                } else if (currentframe == totalframes){
                    //rendering into a mp4 file
                    String[] strings = new String[totalframes];
                    for (int i = 0; i <  totalframes; i++) {
                        strings[i] = "frame"+i+".png";
                    }
                    recorder.generateFromMemory( recorderpath.toString(), strings);
                    images = new BufferedImage[(frames * duration)];
                    //increase frames
                    currentframe++;
                }

            }
            // Sphere movement
            spheres[0].setPosition(spheres[0].getPosition().add(new Point3D(-0.01, 0, 0)));

            //ends timer
            long endTime = System.currentTimeMillis();

            //prints timer
            System.out.println(1000 / (endTime - startTime));
        }
    }
}
