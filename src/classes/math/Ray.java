package classes.math;

public class Ray {
    private final Point3D origin; /*Ray origin */
    private final Vector3D direction; /*Ray direction */
    private final double t; /*distance */

    //*****************************
    // Constructors
    //*****************************

    public Ray(Vector3D direction, float distance) {
        this(new Point3D(), direction, distance);
    }

    public Ray(Point3D origin, Vector3D direction, float distance) {
        this.origin = new Point3D(origin);
        this.direction = new Vector3D(direction); //TODO: normalize?
        this.t = distance;
    }

    //*****************************
    // Methods
    //*****************************

    /**
     * gets origin of ray
     *
     * @return origin
     */
    public Point3D getOrigin() {
        return origin;
    }

    /**
     * gets direction of ray
     *
     * @return vector
     */
    public Vector3D getDirection() {
        return direction;
    }

    /**
     * gets length of ray
     *
     * @return length
     */
    public double getLength() {
        return t;
    }
}
