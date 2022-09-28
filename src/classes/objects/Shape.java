package classes.objects;

import classes.math.Point3D;
import classes.math.Vector3D;

public class Shape {
    private Vector3D vector;
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
     * @param vector
     */
    public Shape(boolean intersects, Vector3D vector) {
        this.intersects = intersects;
        this.vector = vector;
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
        return vector;
    }

    /**
     * returns true if ray intersects with shape
     * @return
     */
    public boolean isIntersected() {
        return intersects;
    }

}
