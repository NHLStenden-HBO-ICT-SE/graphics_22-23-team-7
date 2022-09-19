package classes.view;

import classes.math.Point3D;
import classes.math.Ray;
import classes.math.Vector3D;

public class Camera {
    private final Point3D origin;
    private final Point3D topLeft;
    private final Point3D topRight;
    private final Point3D botLeft;
    private final Vector3D direction; //look direction
    private final Vector3D horizontalV; //top left to top right 3D vector
    private final Vector3D verticalV; //top left to bot left 3D vector

    public Camera(Vector3D direction, float fov, double screenHeight, double screenWidth) { //TODO: make overloads?

        //gets the normalized direction of camera viewing point
        this.direction = direction.normalize();

        //init origin
        this.origin = new Point3D();

        double aspectRatio = screenWidth / screenHeight;
        double viewportHeight = 2.0;
        double viewportWidth = aspectRatio * viewportHeight;

        //gets the center point of the viewing screen
        Point3D center = this.origin.addVector(this.direction.multiply(fov));

        //defines all edges of the viewing window
        this.topLeft = center.add(new Point3D(-1*(viewportWidth/2), 1*(viewportHeight/2), 0));
        this.topRight = center.add(new Point3D(1*(viewportWidth/2), 1*(viewportHeight/2), 0));
        this.botLeft = center.add(new Point3D(-1*(viewportWidth/2), -1*(viewportHeight/2), 0));


        //defines all vectors between edges
        this.horizontalV = topLeft.getVector(topRight);
        this.verticalV = topLeft.getVector(botLeft);
    }

    public Ray makeRay(double width, double height) {
        //defines all vectors between edges
        Vector3D widthV = horizontalV.multiply(width);
        Vector3D heightV = verticalV.multiply(height);

        //get position where ray shoots through
        var position = topLeft.addVector(widthV).addVector(heightV);

        //return ray
        return new Ray(origin, origin.getVector(position), 50); //TODO: change distance to a variable
    }

}
