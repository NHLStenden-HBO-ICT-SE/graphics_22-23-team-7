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
     * @param intersects if ray intersects shape
     */
    public IntersectionHandler(boolean intersects) {
        this(intersects, 0, null, null);
    }

    /**
     * @param length length of ray when it intersects shape
     */
    public IntersectionHandler(double length) {
        this(true, length, null, null);
    }

    /**
     * @param intersects if ray intersects shape
     * @param length length if ray intersects shape
     */
    public IntersectionHandler(boolean intersects, double length) {
        this(intersects, length, null, null);
    }

    /**
     *
     * @param intersects if ray intersects shape
     * @param length length if ray intersects shape
     * @param shape which shape is being hit
     */
    public IntersectionHandler(boolean intersects, double length, Shape shape) {
        this(intersects, length, shape, null);
    }
    /**
     * @param intersects if ray intersects shape
     * @param length length if ray intersects shape
     * @param ray the ray shooting at the shape
     */
    public IntersectionHandler(boolean intersects, double length, Ray ray) {
        this(intersects, length, null, ray);
    }

    /**
     * @param intersects if ray intersects shape
     * @param length length if ray intersects shape
     * @param shape which shape is being hit
     */
    public IntersectionHandler(boolean intersects, double length, Shape shape, Ray ray) {
        this.intersects = intersects;
        //TODO: do we need length, shape, ray if ray never hits shape
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
        return ray.getPosition().addVector(ray.getDirection().multiply(length));
    }
    public Point3D calculateIntersectionPoint(Ray ray) {
        return ray.getPosition().addVector(ray.getDirection().multiply(length));
    }
}