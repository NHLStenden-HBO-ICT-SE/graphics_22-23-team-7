package classes.math;

/**
 * ==============================================================
 * This class assumes when:
 *
 * <br></br>
 * <br></br>
 *
 * <p>
 * x.distance(point) means distance from point to x
 *
 * <br></br>
 * <p>
 * x.add(y)          means x = x + y.
 *
 * <br></br>
 * <p>
 * x.add(y,z)        means x = y + z.
 * </p>
 * ==============================================================
 */
public class Point3D extends Dimension3 {
    //*****************************
    // Constructors
    //*****************************
    public Point3D() {
        super(0, 0, 0);
    }

    public Point3D(double x, double y, double z) {
        super(x, y, z);
    }

    public Point3D(Point3D point) {
        super(point);
    }


    //*****************************
    // Methods
    //*****************************

    /**
     * adds a 3D vector to current 3D point
     *
     * @param vector
     * @return
     */
    public Point3D add(Vector3D vector) {
        return new Point3D(x + vector.x, y + vector.y, z + vector.z);
    }

    /**
     * adds a 3D vector to current 3D point
     * and sets the current object to the result
     *
     * @param vector
     * @return new 3D point
     */
    public void setAdd(Vector3D vector) {
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
    }


    /**
     * subtracts a 3D vector from current point
     *
     * @param vector
     * @return new 3D point
     */
    public Point3D sub(Vector3D vector) {
        return new Point3D(x - vector.x, y - vector.y, z - vector.z);
    }

    /**
     * subtracts a 3D vector from current point
     * and sets the current object to the result
     * @param vector
     */
    public void setSub(Vector3D vector) {
        this.x -= vector.x;
        this.y -= vector.y;
        this.z -= vector.z;
    }


    public Vector3D getVector(Point3D point) {
        return new Vector3D(point.x - x, point.y - y, point.z - z); //TODO: figure out what happens with negative points
    }

    /**
     * calculates the distance between two points
     *
     * @param point
     * @return length
     */
    public double distance(Point3D point) {
        //formula: sqrt( (x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2 )
        double dx = this.x - point.x;
        double dy = this.y - point.y;
        double dz = this.z - point.z;

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }


}
