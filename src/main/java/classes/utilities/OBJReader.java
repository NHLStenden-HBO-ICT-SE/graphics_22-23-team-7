package classes.utilities;

import classes.math.Point3D;
import classes.math.Vector3D;
import classes.objects.Model;
import classes.objects.Triangle;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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

    /**
     * parses a obj file to an object model
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */

    public static Model parseObj(Color color, String... file) throws FileNotFoundException, IOException {
        ArrayList<Point3D> vertices = new ArrayList<>();
        ArrayList<Triangle> triangles = new ArrayList<>();
        ArrayList<Face> faces = new ArrayList<>();
        ArrayList<Vector3D> normals = new ArrayList<>();
        //for reading the file

        try {
            FileReader fileReader = new FileReader(PathHandler.getFile(file));
            //for going through the file later in mem
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String[] values = line.split(" ");
                //break if no lines


                switch (values[0]) {

                    case vertexNormal:
                        for (Vector3D v : GetNormal(values)) {
                            normals.add(v);
                        }
                        break;
                    case obj_vertex:
                        vertices.add(parseVertex(values));
                        break;
                    case obj_faces:
                        Collections.addAll(faces, parseFace(values));
                        break;
                    default: {
                        continue;
                    }
                }

            }
            return makeModel(faces, vertices, color);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static Model parseObj(String file) throws IOException {
        return parseObj(new Color(50, 50, 50), file);
    }

    /**
     * for getting the vertex normals of the string
     *
     * @param values the line of the file
     * @return normal vector
     */
    private static Vector3D[] GetNormal(String[] values) {
        Vector3D[] val = new Vector3D[values.length - 3];
        Double[] axis = new Double[3];

        for (int v = 1; v < values.length - 2; v++) {
            if (values[1].contains("//")) {
                axis[0] = Double.parseDouble(values[1].split("//")[0]) - 1;
                axis[1] = Double.parseDouble(values[v + 1].split("//")[0]) - 1;
                axis[2] = Double.parseDouble(values[v + 2].split("//")[0]) - 1;
                val[v - 1] = new Vector3D(axis[0], axis[1], axis[2]);
            } else if (values[1].contains("/")) {
                axis[0] = Double.parseDouble(values[1].split("/")[0]) - 1;
                axis[1] = Double.parseDouble(values[v + 1].split("/")[0]) - 1;
                axis[2] = Double.parseDouble(values[v + 2].split("/")[0]) - 1;
                val[v - 1] = new Vector3D(axis[0], axis[1], axis[2]);
            } else {
                axis[0] = Double.parseDouble(values[1]) - 1;
                axis[1] = Double.parseDouble(values[v + 1]) - 1;
                axis[2] = Double.parseDouble(values[v + 2]) - 1;
                val[v - 1] = new Vector3D(axis[0], axis[1], axis[2]);
            }
        }
        return val;
    }

    /**
     * combines extracted info to convert to triangles
     *
     * @param faces    faces
     * @param vertices vertices
     * @param normals  vertex normals
     * @return
     */
    private static Model makeModel(ArrayList<Face> faces, ArrayList<Point3D> vertices, ArrayList<Vector3D> normals) {
        var val = new ArrayList<Triangle>();
        for (Face face : faces) {
            val.add(new Triangle(vertices.get(face.indices[0]), vertices.get(face.indices[1]), vertices.get(face.indices[2]), normals.get(face.normal)));
        }
        return new Model(val, new Point3D(0, 0, 0));
    }


    /**
     * combines extracted info to convert to triangles
     *
     * @param faces    faces
     * @param vertices vertices
     * @return
     */
    private static Model makeModel(ArrayList<Face> faces, ArrayList<Point3D> vertices) {
        return makeModel(faces, vertices, new Color(50, 50, 50));
    }

    private static Model makeModel(ArrayList<Face> faces, ArrayList<Point3D> vertices, Color color) {
        var val = new ArrayList<Triangle>();
        for (Face face : faces) {
            val.add(new Triangle(vertices.get(face.indices[2]), vertices.get(face.indices[1]), vertices.get(face.indices[0]), color));
        }
        return new Model(val, new Point3D(0, 0, 0));
    }

    /**
     * get the face from the line and check format
     *
     * @param values line of file
     * @return returns list of faces
     */
    private static Face[] parseFace(String[] values) {
        Face[] val = new Face[values.length - 3];
        int[] indices = new int[3];

        for (int v = 1; v < values.length - 2; v++) {
            if (values[1].contains("//")) {
                indices[0] = Integer.parseInt(values[1].split("//")[0], 10) - 1;
                indices[1] = Integer.parseInt(values[v + 1].split("//")[0], 10) - 1;
                indices[2] = Integer.parseInt(values[v + 2].split("//")[0], 10) - 1;
                val[v - 1] = new Face(indices);
            } else if (values[1].contains("/")) {
                indices[0] = Integer.parseInt(values[1].split("/")[0], 10) - 1;
                indices[1] = Integer.parseInt(values[v + 1].split("/")[0], 10) - 1;
                indices[2] = Integer.parseInt(values[v + 2].split("/")[0], 10) - 1;
                if (values[1].split("/").length > 1)
                    val[v - 1] = new Face(indices, Integer.parseInt(values[v + 2].split("/")[1], 10) - 1);
                val[v - 1] = new Face(indices);
            } else {
                indices[0] = Integer.parseInt(values[1], 10) - 1;
                indices[1] = Integer.parseInt(values[v + 1], 10) - 1;
                indices[2] = Integer.parseInt(values[v + 2], 10) - 1;
                val[v - 1] = new Face(indices);
            }
        }
        return val;

    }

    /**
     * get the vertices from the line
     *
     * @param values the line from the file
     * @return the vertex as point3D
     */
    //for vertexes
    private static Point3D parseVertex(String[] values) {
        //set indexes for vertices
        return new Point3D(Double.parseDouble(values[1]), Double.parseDouble(values[2]), Double.parseDouble(values[3]));
    }

}
