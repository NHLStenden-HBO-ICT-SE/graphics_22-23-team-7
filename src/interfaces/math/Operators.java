package interfaces.math;

/**
 * ==============================================================
 * This class assumes when:
 *
 * <br></br>
 * <br></br>
 *
 * <p>
 * x.add(y)          means x = x + y.
 * ==============================================================
 */
public interface Operators<T extends Operators> {

    //*****************************
    // Methods
    // *****************************

    /**
     * subtracts two generic types
     *
     * @param T
     * @return subtraction
     */
    T sub(T T);

    /**
     * subtracts two generic types
     * and sets the current object to the result
     *
     * @param T
     */
    void setSub(T T);

    /**
     * calculates the sum of this dimension3 and another 3D dimension3
     *
     * @param T
     * @return sum
     */
    T add(T T);

    /**
     * calculates the sum of this T and another T
     * and sets the current object to the result
     *
     * @param T
     */
    void setAdd(T T);

    /**
     * multiplication of generic type with n
     *
     * @param n
     * @return multiplication
     */
    T multiply(double n);

    /**
     * multiplication of generic type with n
     * and sets the current object to the result
     *
     * @param n
     */
    void setMultiply(double n);

    /**
     * divides all elements in a generic type by n
     *
     * @param n
     * @return division
     */
    T divide(int n);

    /**
     * divides all elements in a generic type by n
     * and sets the current object to the result
     *
     * @param n
     */
    void setDivide(int n);
}
