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
 * <p>
 * x.add(y,z)        means x = y + z.
 * </p>
 * ==============================================================
 */
public class Dimension3 {
    protected double x, y, z;

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
     * @param dimension3
     */

    public Dimension3(Dimension3 dimension3) {
        this.x = dimension3.x;
        this.y = dimension3.y;
        this.z = dimension3.z;
    }

    /**
     * sets x, y and z values of object
     *
     * @param dimension3
     */
    public void set(Dimension3 dimension3) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


}
