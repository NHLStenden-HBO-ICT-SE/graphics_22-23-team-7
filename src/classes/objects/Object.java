package classes.objects;

import classes.math.Point3D;
import classes.math.Vector3D;

public class Object {
    public boolean intersects;
    public double intensity; //might not need this, have to check later
    public Point3D point;

    //*****************************
    // Constructor
    //*****************************

    /**
     *
     * @param intersects
     */
    public Object(boolean intersects) {
        this.intersects = intersects;
        this.point = null; //maybe change to new Point3D()
    }

    /**
     *
     * @param intersects
     * @param point
     */
    public Object(boolean intersects, Point3D point) {
        this.intersects = intersects;
        this.point = point;
    }
}
