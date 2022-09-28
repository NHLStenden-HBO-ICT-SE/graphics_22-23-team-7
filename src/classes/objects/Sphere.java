package classes.objects;

import classes.math.Point3D;
import classes.math.Ray;
import classes.math.Vector3D;
import interfaces.objects.Shape;

public class Sphere implements Shape {
    private Point3D origin;
    private double radius;

    /**
     * center = 0,0,0
     * radius = 1
     */
    public Sphere() {
        this(new Point3D(), 1.0);
    }

    /**
     * @param point
     * @param radius
     */
    public Sphere(Point3D point, double radius) {
        this.origin = point;
        this.radius = radius;
    }

    /**
     * gets center of the sphere
     *
     * @return
     */
    public Point3D getOrigin() {
        return origin;
    }

    /**
     * sets the center of the sphere
     *
     * @param point
     */
    public void setOrigin(Point3D point) {
        this.origin = point;
    }

    /**
     * gets radius of sphere
     *
     * @return
     */
    public double getRadius() {
        return radius;
    }

    /**
     * sets the radius of the sphere
     *
     * @param radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * calculates intersection ray -> sphere, returns a new object of shape
     *
     * @param ray
     * @return new shape
     */
    public IntersectionHandler intersection(Ray ray) {

        //normalize the direction of the ray
        Vector3D normalizedDirection = ray.getDirection().normalize();

        //gets vector from points: ray origin and sphere center
        Vector3D ocVec = ray.getOrigin().getVector(origin);

        //get dot product of origin-center-Vector and normalizedDirection
        double t = ocVec.dot(normalizedDirection);

        //length from ray origin to sphere center
        Vector3D q = normalizedDirection.multiply(t).sub(ocVec);

        // |q|^2
        double p2 = q.dot(q);

        //radius^2
        double r2 = radius * radius;

        if (p2 > r2)
            return new IntersectionHandler(false); //a smart way to check if ray intersects before taking the sqrt

        t = t - Math.sqrt(r2 - p2);

        if (t < ray.getLength() && t > 0) {
            return new IntersectionHandler(true, t);
        }
        return new IntersectionHandler(false);
    }


}