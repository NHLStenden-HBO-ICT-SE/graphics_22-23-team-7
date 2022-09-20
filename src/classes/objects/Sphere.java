package classes.objects;

import classes.math.Point3D;
import classes.math.Ray;
import classes.math.Vector3D;

public class Sphere {
    private Point3D center;
    private double radius;

    /**
     * center = 0,0,0
     * radius = 1
     */
    public Sphere() {
        this.center = new Point3D();
        this.radius = 1.0;
    }

    /**
     *
     * @param point
     * @param radius
     */
    public Sphere(Point3D point, double radius) {
        this.center = point;
        this.radius = radius;
    }

    /**
     * sets the center of the sphere
     *
     * @param point
     */
    public void setCenter(Point3D point) {
        this.center = point;
    }

    /**
     * sets the radius of the sphere
     *
     * @param radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Object intersection(Ray ray) {

        //normalize the direction of the ray
        Vector3D normalizedDirection = ray.direction.normalize();

        //gets vector from points: ray origin and sphere center
        Vector3D ocVec = ray.origin.getVector(center);

        //get dot product of origin-center-Vector and normalizedDirection
        double t = ocVec.dot(normalizedDirection);

        //length from ray origin to sphere center
        Vector3D q = normalizedDirection.multiply(t).sub(ocVec);

        // |q|^2
        double p2 = q.dot(q);

        //radius^2
        double r2 = radius * radius;

        if (p2 > r2) return new Object(false); //a smart way to check if ray intersects before taking the sqrt

        t = t - Math.sqrt(r2 - p2);

        if (t < ray.t && t > 0) {
            return new Object(true, ray.origin.addVector(normalizedDirection.multiply(t)));
        }
        return new Object(false);
    }


}