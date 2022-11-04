package classes.math.imaginary;

import classes.math.Point3D;
import classes.math.Vector3D;

public class Quaternion {

    //imaginary numbers
    private final double i;
    private final double j;
    private final double k;

    //real number
    private final double real;

    /**
     *
     * @param real
     * @param i
     * @param j
     * @param k
     */
    public Quaternion(double real, double i, double j, double k) {
        this.real = real;
        this.i = i;
        this.j = j;
        this.k = k;
    }

    /**
     *
     * @param vector rotation vector around an axis
     * @param point3D point that you'd like to rotate
     * @param theta angle of rotation (degrees)
     * @return new point
     */
    public static Point3D rotation(Vector3D vector, Point3D point3D, double theta) {
        //theta from degrees to rad
        theta = Math.toRadians(theta / 2);

        //get cos and sin
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);

        //get unitvector (aka normalized vector mult sin)
        Vector3D unitVector = vector.normalize().multiply(sin);

        //quaternion
        Quaternion Q = new Quaternion(cos, unitVector.x, unitVector.y, unitVector.z);

        //conjugated quaternion of Q
        Quaternion conQ = new Quaternion(cos, -unitVector.x, -unitVector.y, -unitVector.z);

        //quaternion from given point
        Quaternion p = new Quaternion(0, point3D.x, point3D.y, point3D.z);

        var result = conQ.multiply(p).multiply(Q);

        return new Point3D(result.i, result.j, result.k);
    }

    /**
     * multiply
     * @param quaternion to multiply with
     * @return multiplied quaternion
     */
    public Quaternion multiply(Quaternion quaternion) {
        double realN = (real * quaternion.real - i * quaternion.i - j * quaternion.j - k * quaternion.k);
        double iN = (real * quaternion.i + i * quaternion.real + j * quaternion.k - k * quaternion.j);
        double jN = (real * quaternion.j - i * quaternion.k + j * quaternion.real + k * quaternion.i);
        double kN = (real * quaternion.k + i * quaternion.j - j * quaternion.i + k * quaternion.real);
        return new Quaternion(realN, iN, jN, kN);
    }
}
