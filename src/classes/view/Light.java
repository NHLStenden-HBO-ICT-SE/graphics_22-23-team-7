package classes.view;

import classes.math.Point3D;
import classes.math.Ray;

public class Light {
    private Point3D position;
    private double intensity;
    private Color color; //add later

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
     * gets intensity of the light
     *
     * @return
     */
    public double getIntensity() {
        return intensity;
    }

    /**
     * sets intensity of the light
     *
     * @param intensity
     */
    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    /**
     * gets color of the light
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * gets color of the light
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * get position of light
     *
     * @return point3D
     */
    public Point3D getPosition() {
        return position;
    }

    /**
     * sets position of the light
     *
     * @param position
     */
    public void setPosition(Point3D position) {
        this.position = position;
    }

    /**
     * inverse square law
     * <br>
     * read more at: https://en.wikipedia.org/wiki/Inverse-square_law
     *
     * @param ray
     * @return intensity across distance
     */
    public double inverseSquareLaw(Ray ray) {
        var length = ray.getOrigin().distance(this.position);
        return intensity / (length * length);
    }

    /**
     * inverse square law
     * <br>
     * read more at: https://en.wikipedia.org/wiki/Inverse-square_law
     *
     * @param point
     * @return intensity across distance
     */
    public double inverseSquareLaw(Point3D point) {
        var length = point.distance(this.position);
        return intensity / (length * length);
    }

    /**
     * inverse square law
     * <br>
     * read more at: https://en.wikipedia.org/wiki/Inverse-square_law
     *
     * @param length
     * @return intensity across distance
     */

    public double inverseSquareLaw(double length) {
        return intensity / (length * length);
    }
}