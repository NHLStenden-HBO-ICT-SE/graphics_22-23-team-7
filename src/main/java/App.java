import classes.DrawingHelper;
import classes.Recorder;
import classes.Scene;
import classes.math.Point3D;
import classes.math.Vector3D;
import classes.math.imaginary.Quaternion;
import classes.objects.Model;
import classes.solarSystem.Gravity;
import classes.solarSystem.Planet;
import classes.utilities.OBJReader;
import classes.utilities.PathHandler;
import classes.view.Camera;
import classes.view.Light;
import interfaces.objects.Shape;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        //init background image
        Path backgroundpath = Path.of(PathHandler.getFile("background.jpg"));
        //init point earth
        Point3D earthOrigin = new Point3D(0, 0, 0);
        //init point moon
        Point3D moonOrigin = new Point3D(0, 0, -5);
        Model moon = OBJReader.parseObj(Color.lightGray, "models", "Moon.obj");
        moon.setPosition(moonOrigin);
        java.util.List<Model> models = new ArrayList<>();
        java.util.List<Shape> shapes = new ArrayList<>();
        java.util.List<Planet> planets = new ArrayList<>();
        int[] colors = new int[]{0x6DA100, 0x9FC57C, 0x42A6F2, 0x358E00, 0xB8BF71, 0xD07A47, 0xDCA967, 0xE3C066, 0xE19152, 0xF5F5F5, 0xFDBD6A};
        for (int i = 0; i < colors.length; i++) {
            Model model = OBJReader.parseObj(new Color(colors[i]), "models", "earth", Integer.toHexString(colors[i]).toUpperCase() + ".obj");
            model.setPosition(earthOrigin);
            models.add(model);
        }

        models.add(moon);

        models.forEach(m -> shapes.addAll(m.getTriangles()));
        planets.add(new Planet(0.2, new Vector3D(), earthOrigin));
        planets.add(new Planet(0, new Vector3D(0.02, 0.04, 0), moonOrigin));

        //init recorder
        Recorder recorder = new Recorder(10, 1);

        //init view direction
        Vector3D direction = new Vector3D(0, 0, 1);

        //init lights
        Point3D originL = new Point3D(5, 5, -10);
        Light[] lights = new Light[]{new Light(50, originL)};

        //init drawing-helper
        DrawingHelper dh = new DrawingHelper(320, 240);

        //init camera
        Point3D positionC = new Point3D(0, 0, -10);
        Camera camera = new Camera(direction, positionC, 1F, dh.getWidth(), dh.getHeight());

        //init scene
        Scene scene = new Scene(camera, shapes.toArray(new Shape[0]), lights, backgroundpath);
        int lastHeight = dh.getHeight();
        int lastWidth = dh.getWidth();

        Vector3D rotationVector = new Vector3D(0.5, -0.5, 0);

        //repeatedly draw scene
        while (true) {
            //starts timer
            long startTime = System.currentTimeMillis();

            //check if window size changed
            if (lastHeight != dh.getHeight() || lastWidth != dh.getWidth()) {
                scene.setCamera(new Camera(direction, positionC, 1F, dh.getWidth(), dh.getHeight())); //make new camera with proper canvas
                lastHeight = dh.getHeight();
                lastWidth = dh.getWidth();
            }
            if (dh.drawScene(scene)) {
                dh.update();
                // Additional sleep as update returns before finishing render
                Thread.sleep(7, 500);

                for (int i = 0; i < models.size(); i++) {
                    var modelPos = models.get(i).getPosition();
                    models.get(i).getTriangles().forEach(x -> {
                        var positions = x.getVertices();
                        x.setVertex(0, Quaternion.rotation(rotationVector, positions[0].sub(modelPos), 2).add(modelPos));
                        x.setVertex(1, Quaternion.rotation(rotationVector, positions[1].sub(modelPos), 2).add(modelPos));
                        x.setVertex(2, Quaternion.rotation(rotationVector, positions[2].sub(modelPos), 2).add(modelPos));
                    });
                }

                Gravity.movePlanets(planets.toArray(new Planet[0]));


                earthOrigin = planets.get(0).getPosition();
                moonOrigin = planets.get(1).getPosition();

                for (int i = 0; i < models.size(); i++) {
                    Model model = models.get(i);
                    if (i == models.size() - 1) { // Moon
                        model.setPosition(moonOrigin);
                        models.set(i, model);
                    } else {
                        model.setPosition(earthOrigin);
                        models.set(i, model);
                    }
                }
                // checks if the recorder is finished.
                if (recorder.isRecording()) {
                    recorder.generateImage(dh);
                }


            }

            //ends timer
            long endTime = System.currentTimeMillis();

            //prints timer
            System.out.println(1000 / (endTime - startTime));
        }
    }
}

