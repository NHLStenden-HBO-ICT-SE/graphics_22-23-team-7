package classes.view;

import classes.math.Point3D;
import classes.math.Ray;

public class Light {
    private final Point3D position;
    private final double intensity;
    private final Color color; //add later

    //*****************************
    // Constructors
    //*****************************

    /**
     * @param intensity
     * @param position
     */
    public Light(double intensity, Point3D position) {
        this(intensity, position, new Color());
    }

    /**
     * @param intensity
     * @param position
     * @param color
     */
    public Light(double intensity, Point3D position, Color color) {
        this.intensity = intensity;
        this.color = color;
        this.position = position;
    }

    //*****************************
    // Methods
    //*****************************


    /**
     * get position of light
     *
     * @return point3D
     */
    public Point3D getPosition() {
        return position;
    }

    /**
     * inverse square law
     * <br>
     * read more at: https://en.wikipedia.org/wiki/Inverse-square_law
     *
     * @param ray
     * @return intensity across distance
     */
    public double getIntensity(Ray ray) {
        var length = ray.getOrigin().distance(this.position);
        return clampIntensity(1 / ((length * length) / intensity));
    }

    /**
     * inverse square law
     * <br>
     * read more at: https://en.wikipedia.org/wiki/Inverse-square_law
     *
     * @param point
     * @return intensity across distance
     */
    public double getIntensity(Point3D point) {
        var length = point.distance(this.position);
        return clampIntensity(1 / ((length * length) / intensity));
    }

    /**
     * inverse square law
     * <br>
     * read more at: https://en.wikipedia.org/wiki/Inverse-square_law
     *
     * @param length
     * @return intensity across distance
     */

    public double getIntensity(double length) {
        return clampIntensity(1 / ((length * length) / intensity));
    }

    /**
     * input can't exceed 1
     *
     * @param input
     * @return
     */
    private double clampIntensity(double input) {
        return input > 1 ? 1 : input;
    }

}
