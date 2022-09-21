package classes.math;
public abstract class Dimension3 {
    public double x, y, z;

    /**
     * generic constructor with x, y and z = 0
     */

    //*****************************
    // Constructors
    //*****************************
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


}
