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
public class Point3D extends Dimension3<Point3D> { //TODO: volgende week zal wouter uitleg geven over self-generic types
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


    public Vector3D getVector(Point3D point) {
        return new Vector3D(point.x - x, point.y - y, point.z - z);
    }

    public Point3D addVector(Vector3D point) {
        return new Point3D(point.x + x, point.y + y, point.z + z);
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
