package classes.objects;

import classes.math.Point3D;
import classes.math.Ray;
import classes.math.Vector3D;
import interfaces.objects.Shape;

public class Triangle implements Shape {


    /**
     * Triangle constructor. sets vertices.
     */
    private Point3D[] vertices = new Point3D[3];
    private Vector3D surfaceNormal;
    public Triangle(Point3D vertice, Point3D vertice1, Point3D vertice2){
        vertices[0] = vertice;
        vertices[1] = vertice1;
        vertices[2] = vertice2;
        this.calcSurfaceNormal();
        surfaceNormal.normalize();
    }


    /**
     *
     * @return vertices of the triangle
     */
    public Point3D[] getVertices(){
        return this.vertices;
    }

    /**
     * sets specific index of triangle vertices
     * @param index index
     * @param p new point
     */
    public void setVertice(int index, Point3D p) {
        this.vertices[index] = p;
    }

    public void calcSurfaceNormal (){
        //gets the two direction vectors
        Vector3D direction1 = vertices[0].getVector(vertices[1]);
        Vector3D direction2 = vertices[2].getVector(vertices[0]);

        //clear out old vector
        this.surfaceNormal = new Vector3D();
        surfaceNormal.x = ((direction1.y * direction2.z) - (direction1.z * direction2.y));
        surfaceNormal.y = ((direction1.z * direction2.x) -(direction1.x * direction2.z));
        surfaceNormal.z = ((direction1.x * direction2.y) - (direction1.y * direction2.x));
    }

    public Vector3D getSurfaceNormal(){
    return surfaceNormal;
    }

    @Override
    public Point3D getPosition() {
        return null;
    }

    @Override
    public IntersectionHandler intersection(Ray ray) {
        return null;
    }

    @Override
    public Boolean isRayInRangeOfShape(Ray ray) {
        return null;
    }
}
