package classes.utilities;

import classes.math.Point3D;
import classes.objects.Triangle;
import classes.objects.Model;

import java.io.*;
import java.util.ArrayList;

/**
 * inspired by:
 * https://github.com/seanrowens/oObjLoader/blob/master/com/owens/oobjloader/parser/Parse.java
 * https://www.freecodecamp.org/news/java-string-to-int-how-to-convert-a-string-to-an-integer/
 * https://winterbloed.be/reading-an-obj-file-in-processing/
 */
public class OBJReader {
    //local variables for easy access
    private final static String obj_vertex = "v";
    private final static String vertexNormal = "vn";
    private final static String obj_faces = "f";

    public static Model parseObj(String file) throws FileNotFoundException, IOException {
        ArrayList<Point3D> vertices = new ArrayList<>();
        ArrayList<Triangle> triangles = new ArrayList<>();
        ArrayList<Face> faces = new ArrayList<>();
        //for reading the file
        FileReader fileReader = new FileReader(new File(file));
        //for going trough the file later in mem
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            String[] values = line.split(" ");
            //break if no lines


            switch (values[0]) {

                case obj_vertex:
                    vertices.add(parseVertex(values));
                    break;
                case obj_faces:
                    for (Face f: parseFace(values))
                    {
                        faces.add(f);
                    }
                    break;
                default: {
                    continue;
                }
            }

        }

        return makemodel(faces, vertices);

    }

    private static Model makemodel(ArrayList<Face> faces, ArrayList<Point3D> vertices) {
        var val = new ArrayList<Triangle>();
        for (Face face : faces) {
            val.add(new Triangle(vertices.get(face.indices[0]), vertices.get(face.indices[1]), vertices.get(face.indices[2])));
        }
        return new Model(val, new Point3D(0, 0, 0));
    }


    private static Face[] parseFace(String[] values) {
        Face[] val =  new Face[values.length-3];
        int[] indices = new int[3];

        for (int v = 1; v < values.length-2; v++) {

            indices[0] = Integer.parseInt(values[1], 10)-1;
            indices[1] = Integer.parseInt(values[v+1], 10)-1;
            indices[2] = Integer.parseInt(values[v+2], 10)-1;
            val[v-1] = new Face(indices);
        }

    return val;


        /**
         *     try {
         *             //for squares
         *             if (values.length == 5) {
         *
         *                 int[] indices = new int[]{Integer.parseInt(values[1], 10) - 1, Integer.parseInt(values[2], 10) - 1, Integer.parseInt(values[3], 10) - 1, Integer.parseInt(values[4], 10) - 1};
         *                 return new Face[]{
         *                         new Face(new int[]{indices[0], indices[1], indices[2]}),
         *                         new Face(new int[]{indices[0], indices[2], indices[3]})
         *                 };
         *
         *             } else {
         *                 int[] indices = new int[]{Integer.parseInt(values[1], 10) - 1, Integer.parseInt(values[2], 10) - 1, Integer.parseInt(values[3], 10) - 1};
         *                 return new Face[]{new Face(new int[]{indices[0], indices[1], indices[2]})};
         *             }
         *         } catch (NumberFormatException e) {
         *             return new Face[]{new Face(new int[]{0, 0, 0})};
         *         }
         */

    }
    //for vertexes
    private static Point3D parseVertex(String[] values) {
        //set indexes for vertices
        return new Point3D(Double.parseDouble(values[1]), Double.parseDouble(values[2]), Double.parseDouble(values[3]));
    }

}
