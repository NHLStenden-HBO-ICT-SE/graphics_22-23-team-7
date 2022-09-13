package classes.Utility.Math;

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
 *
 * x.add(y,z)        means x = y + z.
 * </p>
 * ==============================================================
 */
public class Vector3D extends Dimension3 {

    //*****************************
    // Constructors
    //*****************************
    public Vector3D() {
        super(0, 0, 0);
    }

    public Vector3D(double x, double y, double z) {
        super(x, y, z);
    }

    public Vector3D(Vector3D vector) {
        super(vector);
    }

    public Vector3D(Point3D point1, Point3D point2) {
        super(point2.x - point1.x, point2.y - point1.y, point2.z - point1.z);
    }

    //*****************************
    // Methods
    //*****************************

    /**
     * subtracts two 3D vectors
     *
     * @param vector
     * @return subtraction
     */
    public Vector3D sub(Vector3D vector) {
        return new Vector3D((this.x - vector.x), (this.y - vector.y), (this.z - vector.z));
    }

    /**
     * subtracts two 3D vectors
     * and sets the current object to the result
     *
     * @param vector
     */
    public void setSub(Vector3D vector) {
        this.x -= vector.x;
        this.y -= vector.y;
        this.z -= vector.z;
    }

    /**
     * calculates the sum of this vector and another 3D vector
     *
     * @param vector
     * @return sum
     */
    public Vector3D add(Vector3D vector) {
        return new Vector3D((this.x + vector.x), (this.y + vector.y), (this.z + vector.z));
    }

    /**
     * calculates the sum of this vector and another 3D vector
     * and sets the current object to the result
     *
     * @param vector
     */
    public void setAdd(Vector3D vector) {
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
    }

    /**
     * multiplication of vector with n
     *
     * @param n
     * @return multiplication
     */
    public Vector3D multiply(double n) {
        return new Vector3D((this.x * n), (this.y * n), (this.z * n));
    }

    /**
     * multiplication of vector with n
     * and sets the current object to the result
     *
     * @param n
     */
    public void setMultiply(double n) {
        this.x *= n;
        this.y *= n;
        this.z *= n;
    }

    /**
     * divides all elements in a vector by n
     *
     * @param n
     * @return division
     */
    public Vector3D divide(int n) {
        return new Vector3D((this.x / n), (this.y / n), (this.z / n));
    }

    /**
     * divides all elements in a vector by n
     * and sets the current object to the result
     *
     * @param n
     */
    public void setDivide(int n) {
        this.x /= n;
        this.y /= n;
        this.z /= n;
    }

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
