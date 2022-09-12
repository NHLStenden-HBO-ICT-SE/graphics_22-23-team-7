package classes.Utility;

/**
 * a point in 3D space
 */
public class Point3D {
    protected double x, y, z;

    /**
     * default values, java will already do this normally but just in case
     */
    public Point3D()
    {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public Point3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(Point3D point)
    {
        this.x = point.x;
        this.y = point.y;
        this.z = point.z;
    }

    /**
     * adds two 3D points
     */

    public Point3D add(Point3D point)
    {
        return new Point3D(x + point.x, y + point.y, z + point.z);
    }

    /**
     * subtracts two 3D points
     */
    public Point3D sub(Point3D point)
    {
        return new Point3D(x - point.x, y - point.y, z - point.z);
    }
}
