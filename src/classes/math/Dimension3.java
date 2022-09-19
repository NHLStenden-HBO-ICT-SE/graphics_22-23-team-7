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
public abstract class Dimension3<T extends Dimension3> { //TODO: volgende week zal wouter uitleg geven over self-generic types
    public double x, y, z;
    /**
     * generic constructor with x, y and z = 0
     */
    public Dimension3() {
        this(0, 0, 0);
    }

    /**
     * generic constructor with x, y and z
     *
     * @param x coordinates
     * @param y coordinates
     * @param z coordinates
     */
    public Dimension3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * generic constructor with Dimension3
     *
     * @param T
     */

    public Dimension3(T T) {
        this.x = T.x;
        this.y = T.y;
        this.z = T.z;
    }


    /**
     * subtracts two 3D vectors
     *
     * @param T
     * @return subtraction
     */
    public T sub(T T) {
        T.x -= this.x;
        T.y -= this.y;
        T.z -= this.z;
        return T;
    }

    /**
     * subtracts two 3D vectors
     * and sets the current object to the result
     *
     * @param T
     */
    public void setSub(T T) {
        this.x -= T.x;
        this.y -= T.y;
        this.z -= T.z;
    }

    /**
     * calculates the sum of this dimension3 and another 3D dimension3
     *
     * @param T
     * @return sum
     */
    public T add(T T) {
        T.x += this.x;
        T.y += this.y;
        T.z += this.z;
        return T;
    }

    /**
     * calculates the sum of this T and another 3D T
     * and sets the current object to the result
     *
     * @param T
     */
    public void setAdd(T T) {
        this.x += T.x;
        this.y += T.y;
        this.z += T.z;
    }

    /**
     * multiplication of vector with n
     *
     * @param n
     * @return multiplication
     */
    public T selfMultiply(double n) {
        this.setMultiply(n);
        return (T) this;
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
    public T selfDivide(int n) {
        this.setDivide(n);
        return (T) this;
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


}
