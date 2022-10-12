package classes.objects;

import classes.ModelLoader;
import classes.math.Point3D;
import classes.math.Ray;
import classes.math.Vector3D;
import interfaces.objects.Shape;

public class Model implements Shape {

    private Triangle[] triangles;
    private Point3D position = new Point3D(0, 0, 0);

    /**
     * constructor
     *
     * @param triangles the parts
     * @param position  position
     */
    public Model(Triangle[] triangles, Point3D position) {
        this.triangles = triangles;
        setPosition(position);
    }


    /**
     * moves the model back to its zero position and moves it to the new coordinates.
     *
     * @param position the new position
     */
    public void setPosition(Point3D position) {
        for (Triangle t : triangles) {
            for (int i = 0; i < 3; i++) {
                t.setVertex(i, t.getVertices()[i].sub(this.position));
            }
        }
        this.position = position;
        for (Triangle t : triangles) {
            for (int i = 0; i < 3; i++) {
                t.setVertex(i, t.getVertices()[i].add(this.position));
            }
        }
    }


    /**
     * returns all triangles
     *
     * @return the model's triangles
     */
    public Triangle[] getTriangles() {
        return triangles;
    }

    /**
     * sets triangles of object
     *
     * @param triangles the new triangles
     */
    public void setTriangles(Triangle[] triangles) {
        this.triangles = triangles;
    }

    /**
     * returns the position
     *
     * @return position
     */
    @Override
    public Point3D getPosition() {
        return position;
    }

    /**
     * checks if ray has intersected with any of the triangles. once one hits true we dont have to go over the others.
     *
     * @param ray
     * @return
     */
    @Override
    public IntersectionHandler intersection(Ray ray) {
        for (Triangle t : triangles) {
            var intersection = t.intersection(ray);
            if (intersection.isIntersected())
                return intersection;
        }
        return new IntersectionHandler(false);
    }

    /**
     * checks if ray is in range of any of the triangles. once one hits true we dont have to go over the others.
     *
     * @param ray the casted ray
     * @return if the ray is in range
     */
    @Override
    public Boolean isRayInRangeOfShape(Ray ray) {
        for (Triangle t : triangles) {
            var rangeOfShape = t.isRayInRangeOfShape(ray);
            if (rangeOfShape)
                return rangeOfShape;
        }
        return false;
    }

    /**
     * not implemented
     *
     * @param point
     * @return
     */
    @Override
    public Vector3D calcNormal(Point3D point) {
        return null;
    }
}
