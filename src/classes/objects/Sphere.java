package classes.objects;

import classes.math.Point3D;
import classes.math.Ray;
import classes.math.Vector3D;
import errors.math.NegativeNumException;
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
        _setRadius(radius); //throws exception if radius is negative
        this.origin = point;
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
        _setRadius(radius);
    }

    /**
     * this is a private set, due to the possibility of children overriding "setRadius"
     *
     * @param radius
     */
    private void _setRadius(double radius) {
        try {
            //if radius is negative throw exception
            if (radius < 0) throw new NegativeNumException();

            //else ->
            this.radius = radius;

        } catch (NegativeNumException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * calculates intersection ray -> sphere, returns a new object of shape
     *
     * @param ray
     * @return new shape
     */
    public IntersectionHandler intersection(Ray ray) {

        //fast and smart way to check if ray is able to intersect sphere
        if (!isRayInRangeOfShape(ray)) return new IntersectionHandler(false);

        //gets vector from points: ray origin and sphere center
        Vector3D ocVec = ray.getOrigin().getVector(origin);

        //get dot product of origin-center-Vector and normalizedDirection
        double t = ocVec.dot(ray.getDirection());

        //length from ray origin to sphere center
        Vector3D q = ray.getDirection().multiply(t).sub(ocVec);

        // |q|^2
        double p2 = q.dot(q);

        //radius^2
        double r2 = radius * radius;

        if (p2 > r2) return new IntersectionHandler(false); //a smart way to check if ray intersects before taking the sqrt

        //calculate distance to intersection
        t = t - Math.sqrt(r2 - p2);

        var owo = ray.getOrigin().distance(origin);

        var uwu = owo - t;

        //if we intersect sphere return the length
        if (t < ray.getLength() && t > 0)
            return new IntersectionHandler(true, t, this, ray);

        //else
        return new IntersectionHandler(false);
    }

    /**
     * @param ray
     * @return
     */
    public Boolean isRayInRangeOfShape(Ray ray) {
       return ray.getOrigin().distance(origin) - radius < ray.getLength();
    }


}