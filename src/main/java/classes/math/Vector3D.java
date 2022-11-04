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
public class Vector3D extends Dimension3<Vector3D> {

    //*****************************
    // Constructors
    //*****************************
    public Vector3D() {
        this(0, 0, 0);
    }

    public Vector3D(Point3D point1, Point3D point2) {
        this(point2.x - point1.x, point2.y - point1.y, point2.z - point1.z);
    }

    public Vector3D(Vector3D vector) {
        this(vector.x, vector.y, vector.z);
    }

    public Vector3D(double x, double y, double z) {
        super(x, y, z);
    }


    //*****************************
    // Methods
    //*****************************

    /**
     * subtracts two generic types
     *
     * @param T
     * @return subtraction
     */
    @Override
    public Vector3D sub(Vector3D T) {
        return new Vector3D(x - T.x, y - T.y, z - T.z);
    }

    /**
     * subtracts two generic types
     * and sets the current object to the result
     *
     * @param T
     */
    @Override
    public void setSub(Vector3D T) {
        x -= T.x;
        y -= T.y;
        z -= T.z;
    }

    /**
     * calculates the sum of this dimension3 and another 3D dimension3
     *
     * @param T
     * @return sum
     */
    @Override
    public Vector3D add(Vector3D T) {
        return new Vector3D(x + T.x, y + T.y, z + T.z);
    }

    /**
     * calculates the sum of this T and another T
     * and sets the current object to the result
     *
     * @param T
     */
    @Override
    public void setAdd(Vector3D T) {
        x += T.x;
        y += T.y;
        z += T.z;
    }

    /**
     * multiplication of vector with n
     *
     * @param n
     * @return multiplication
     */
    public Vector3D multiply(double n) {
        return new Vector3D(x * n, y * n, z * n);
    }

    /**
     * multiplication of generic type with n
     * and sets the current object to the result
     *
     * @param n
     */
    @Override
    public void setMultiply(double n) {
        x *= n;
        y *= n;
        z *= n;
    }

    /**
     * divides all elements in a generic type by n
     *
     * @param n
     * @return division
     */
    @Override
    public Vector3D divide(double n) {
        return new Vector3D(x / n, y / n, z / n);
    }

    /**
     * divides all elements in a generic type by n
     * and sets the current object to the result
     *
     * @param n
     */
    @Override
    public void setDivide(double n) {
        x /= n;
        y /= n;
        z /= n;
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

        if (length == 0) return new Vector3D();

        return new Vector3D(x, y, z).divide(length);
    }

    /**
     * reduce the 3D vector from its value to a value between -1 and 1
     * and sets the current object to the result
     */
    public void setNormalize() {
        double length = length();

        if (length == 0) return;

        setDivide(length);

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
     * @return angle of vector (in degrees)
     */
    public double angle(Vector3D vector) {
        return Math.toDegrees(Math.acos(this.dot(vector) / (this.length() * vector.length())));
    }

    public Matrix getMatrix() {
        return new Matrix(new double[][]{{this.x}, {this.y}, {this.z}});
    }

    //sets matrix value to the vector3D
    public void setMatrix(Matrix m) {
        this.x = m.getMatrix()[0][0];
        this.y = m.getMatrix()[1][0];
        this.z = m.getMatrix()[2][0];
    }


}
