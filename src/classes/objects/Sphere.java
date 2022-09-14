package classes.objects;

import classes.utility.math.Point3D;
import classes.utility.math.Ray;
import classes.utility.math.Vector3D;

public class Sphere {
    protected Point3D center;
    protected double radius;

    public Sphere() {
        this.center = new Point3D();
        this.radius = 1.0;
    }

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


    //TODO: change this method
    public void intersection(Ray ray) {

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

        if (p2 > r2) return; //a smart way to check if ray intersects before taking the sqrt

        t = t - Math.sqrt(r2 - p2);

        //TODO: return bool maybe?

        if (t < ray.t && t > 0) {

            //TODO: to get the point of intersection -> normalizedDirection.multiply(ray.t);

            ray.t = t;
        }
    }


}
