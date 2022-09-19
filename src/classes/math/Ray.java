package classes.math;

public class Ray {
    public Point3D origin; /*Ray origin */
    public Vector3D direction; /*Ray direction */
    public double t; /*distance */


    public Ray(Point3D origin, Vector3D direction, float distance) {
        this.origin = new Point3D(origin);
        this.direction = new Vector3D(direction); //TODO: normalize?
        this.t = distance;
    }

    public Ray(Vector3D direction, float distance) {
        this.origin = new Point3D(0, 0, 0);
        this.direction = new Vector3D(direction);
        this.t = distance;
    }

}
