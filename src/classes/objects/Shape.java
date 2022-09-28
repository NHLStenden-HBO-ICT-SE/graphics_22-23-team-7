package classes.objects;

import classes.math.Point3D;

public class Shape {
    private Point3D point;
    private final boolean intersects;
    private double intensity; //might not need this, have to check later

    //*****************************
    // Constructors
    //*****************************

    /**
     * @param intersects
     */
    public Shape(boolean intersects) {
        this(intersects, null); //maybe change to new Point3D()
    }

    /**
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

    /**
     * gets intensity of the light hitting the shape
     * @return
     */
    public double getIntensity() {
        return intensity;
    }

    /**
     * gets point of shape
     * @return
     */
    public Point3D getPoint() {
        return point;
    }

    /**
     * returns true if ray intersects with shape
     * @return
     */
    public boolean isIntersected() {
        return intersects;
    }

}
