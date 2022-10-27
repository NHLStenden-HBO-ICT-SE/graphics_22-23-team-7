package classes.objects;

import classes.math.Point3D;
import classes.math.Ray;
import classes.math.Vector3D;
import interfaces.objects.Shape;

import java.awt.*;
import java.util.ArrayList;

public class Model {

    private ArrayList<Triangle> triangles;
    private Point3D position = new Point3D(0, 0, 0);

    /**
     * constructor
     *
     * @param triangles the parts
     * @param position  position
     */
    public Model(ArrayList<Triangle> triangles, Point3D position) {
        this.triangles = triangles;
        setPosition(position);
    }


    /**
     * moves the model back to its zero position and moves it to the new coordinates.
     *
     * @param position the new position
     */
    public void setPosition(Point3D position) {
        for (Triangle t : triangles) {
            for (int i = 0; i < 3; i++) {
                t.setVertex(i, t.getVertices()[i].sub(this.position));
            }
        }
        this.position = position;
        for (Triangle t : triangles) {
            for (int i = 0; i < 3; i++) {
                t.setVertex(i, t.getVertices()[i].add(this.position));
            }
        }
    }



    /**
     * returns all triangles
     *
     * @return the model's triangles
     */
    public ArrayList<Triangle> getTriangles() {
        return triangles;
    }

    /**
     * sets triangles of object
     *
     * @param triangles the new triangles
     */
    public void setTriangles(ArrayList<Triangle> triangles) {
        this.triangles = triangles;
    }

    /**
     * returns the position
     *
     * @return position
     */
    public Point3D getPosition() {
        return position;
    }

    public void modelColor (Color color){
        for (Triangle t : triangles)
        {
            t.setColor(color);
        }
    }
}
