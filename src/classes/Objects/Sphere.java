package classes.Objects;

import classes.Utility.Math.Point3D;
import classes.Utility.Math.Ray;
import classes.Utility.Math.Vector3D;

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


    public void intersection(Ray ray) {

        //normalize the direction of the ray
        Vector3D normalizedDirection = ray.direction.normalize();

        //gets vector from point origin and point center of sphere
        Vector3D ocVec = ray.origin.getVector(center);

        //get dot product of origin-center-Vector and normalizedDirection
        double t = ocVec.dot(normalizedDirection);


        Vector3D q = ray.direction.multiply(t).sub(ocVec);
        double p2 = q.dot(q);

        if (p2 > radius * radius) return;

        t = t - Math.sqrt(radius * radius - p2);
        if (t < ray.t && t > 0) {
            ray.t = t;
        }
    }


}
