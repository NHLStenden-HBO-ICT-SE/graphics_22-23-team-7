package classes.objects;

public class IntersectionHandler {
    private final boolean intersects;
    private final double length; //length
    private double intensity; //might not need this, have to check later

    //*****************************
    // Constructors
    //*****************************

    /**
     * @param intersects
     */
    public IntersectionHandler(boolean intersects) {
        this(intersects, 0);
    }

    /**
     * @param intersects
     */
    public IntersectionHandler(double length) {
        this(true, length);
    }

    /**
     * @param intersects
     * @param length
     */
    public IntersectionHandler(boolean intersects, double length) {
        this.intersects = intersects;
        this.length = length - 0.00001;
    }

    //*****************************
    // Methods
    //*****************************

    /**
     * gets intensity of the light hitting the shape
     *
     * @return
     */
    public double getIntensity() {
        return intensity;
    }

    /**
     * gets point of shape
     *
     * @return
     */
    public double getLength() {
        return length;
    }

    /**
     * returns true if ray intersects with shape
     *
     * @return
     */
    public boolean isIntersected() {
        return intersects;
    }

}