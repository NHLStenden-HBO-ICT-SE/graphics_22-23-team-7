package classes.objects;

import classes.math.Point3D;

public class Shape {
    private boolean intersects;
    private double intensity; //might not need this, have to check later
    public Point3D point;

    //*****************************
    // Constructors
    //*****************************

    /**
     *
     * @param intersects
     */
    public Shape(boolean intersects) {
        this(intersects, null); //maybe change to new Point3D()
    }

    /**
     *
     * @param intersects
     * @param point
     */
    public Shape(boolean intersects, Point3D point) {
        this.intersects = intersects;
        this.point = point;
    }

    //*****************************
    // Methods
    //*****************************


    public double getIntensity() {
        return intensity;
    }

    public boolean isIntersects() {
        return intersects;
    }
}
