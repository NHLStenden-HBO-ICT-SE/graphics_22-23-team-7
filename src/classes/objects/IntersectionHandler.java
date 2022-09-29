package classes.objects;

import classes.math.Point3D;
import classes.math.Ray;
import interfaces.objects.Shape;

public class IntersectionHandler {
    private final boolean intersects;
    private final double length; //length

    private Shape shape;

    private final Ray ray;
    private double intensity; //might not need this, have to check later

    //*****************************
    // Constructors
    //*****************************

    /**
     * @param intersects
     */
    public IntersectionHandler(boolean intersects) {
        this(intersects, 0, null, null);
    }

    /**
     * @param length
     */
    public IntersectionHandler(double length) {
        this(true, length, null, null);
    }

    /**
     * @param intersects
     * @param length
     */
    public IntersectionHandler(boolean intersects, double length) {
        this(intersects, length, null, null);
    }

    /**
     *
     * @param intersects
     * @param length
     */
    public IntersectionHandler(boolean intersects, double length, Shape shape) {
        this(intersects, length, shape, null);
    }
    /**
     *
     * @param intersects
     * @param length
     * @param ray
     */
    public IntersectionHandler(boolean intersects, double length, Ray ray) {
        this(intersects, length, null, ray);
    }

    /**
     * @param intersects
     * @param length
     * @param shape
     */
    public IntersectionHandler(boolean intersects, double length, Shape shape, Ray ray) {
        this.intersects = intersects;
        this.length = length - 0.00001;
        this.shape = shape;
        this.ray = ray;
    }

    //*****************************
    // Methods
    //*****************************

    /**
     * gets intensity of the light hitting the shape
     *
     * @return
     */
    public double getIntensity() {
        return intensity;
    }

    /**
     * gets point of shape
     *
     * @return
     */
    public double getLength() {
        return length;
    }

    /**
     * returns true if ray intersects with shape
     *
     * @return
     */
    public boolean isIntersected() {
        return intersects;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Ray getRay() {
        return ray;
    }

    public Point3D calculateIntersectionPoint() {
        return ray.getOrigin().addVector(ray.getDirection().multiply(length));
    }
    public Point3D calculateIntersectionPoint(Ray ray) {
        return ray.getOrigin().addVector(ray.getDirection().multiply(length));
    }
}