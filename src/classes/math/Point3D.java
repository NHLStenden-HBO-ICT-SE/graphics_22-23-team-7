package classes.math;

import interfaces.math.Operators;

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
public class Point3D extends Dimension3 implements Operators<Point3D> {
    //*****************************
    // Constructors
    //*****************************
    public Point3D() {
        this(0, 0, 0);
    }

    public Point3D(Point3D point) {
        this(point.x, point.y, point.z);
    }

    public Point3D(double x, double y, double z) {
        super(x, y, z);
    }


    //*****************************
    // Methods
    //*****************************


    /**
     * gets vector between two points
     *
     * @param point
     * @return
     */
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

    @Override
    public Point3D sub(Point3D T) {
        return new Point3D(x - T.x, y - T.y, z - T.z);
    }

    @Override
    public void setSub(Point3D T) {
        x -= T.x;
        y -= T.y;
        z -= T.z;
    }

    @Override
    public Point3D add(Point3D T) {
        return new Point3D(x + T.x, y + T.y, z + T.z);
    }

    @Override
    public void setAdd(Point3D T) {
        x += T.x;
        y += T.y;
        z += T.z;
    }

    @Override
    public Point3D multiply(double n) {
        return new Point3D(x * n, y * n, z * n);
    }

    @Override
    public void setMultiply(double n) {
        x *= n;
        y *= n;
        z *= n;
    }

    @Override
    public Point3D divide(int n) {
        return new Point3D(x / n, y / n, z / n);
    }

    @Override
    public void setDivide(int n) {
        x /= n;
        y /= n;
        z /= n;
    }
}
