package classes.math;

/**
 * ==============================================================
 * This class assumes when:
 *
 * <br></br>
 * <br></br>
 *
 * <p>
 * x.add(y)          means x = x + y.
 *
 * <br></br>
 * <p>
 * x.add(y,z)        means x = y + z.
 * </p>
 * ==============================================================
 */
public class Vector3D extends Dimension3<Vector3D> { //TODO: volgende week zal wouter uitleg geven over self-generic types

    //*****************************
    // Constructors
    //*****************************
    public Vector3D() {
        this(0,0,0);
    }

    public Vector3D(double x, double y, double z) {
        super(x, y, z);
    }

    public Vector3D(Point3D point1, Point3D point2) {
        this(point2.x - point1.x, point2.y - point1.y, point2.z - point1.z);
    }

    public Vector3D(Vector3D vector) {
        super(vector);
    }

    //*****************************
    // Methods
    //*****************************

    /**
     * multiplication of vector with n
     *
     * @param n
     * @return multiplication
     */
    public Vector3D multiply(double n) {
        return new Vector3D(this.x * n, this.y * n, this.z * n);
    } //TODO:

    /**
     * calculates cross product from this vector and another one
     *
     * @param vector
     * @return cross product of second vector
     */
    public Vector3D cross(Vector3D vector) {
        return new Vector3D(
                /*X*/  (this.y * vector.z) - (this.z * vector.y),
                /*Y*/  (this.z * vector.x) - (this.x * vector.z),
                /*Z*/  (this.x * vector.y) - (this.y * vector.x));
    }

    /**
     * calculates cross product from this vector and another one
     * and sets the current object to the result
     *
     * @param vector
     */
    public void setCross(Vector3D vector) {
        this.x = (this.y * vector.z) - (this.z * vector.y);
        this.y = (this.z * vector.x) - (this.x * vector.z);
        this.z = (this.x * vector.y) - (this.y * vector.x);
    }

    /**
     * reduce the 3D vector from its value to a value between -1 and 1
     *
     * @return normalized 3D vector
     */
    public Vector3D normalize() {
        double length = length();

        if (length != 0) return new Vector3D(x / length, y / length, z / length);

        return new Vector3D(0, 0, 0);
    }

    /**
     * reduce the 3D vector from its value to a value between -1 and 1
     * and sets the current object to the result
     */
    public void setNormalize() {
        double length = length();

        if (length == 0) return;

        x /= length;
        y /= length;
        z /= length;

    }


    /**
     * calculates dot product from this and another 3D vector
     *
     * @param vector
     * @return dot product of second vector
     */
    public double dot(Vector3D vector) {
        return ((this.x * vector.x) + (this.y * vector.y) + (this.z * vector.z));
    }

    /**
     * calculates length
     *
     * @return length
     */
    public double length() {
        return Math.sqrt((this.x * this.x + this.y * this.y + this.z * this.z));
    }


    /**
     * returns angle in degrees
     *
     * @param vector
     * @return angle of vector
     */
    public double angle(Vector3D vector) {
        return Math.acos(this.dot(vector) / (this.length() * vector.length()));
    }


}
