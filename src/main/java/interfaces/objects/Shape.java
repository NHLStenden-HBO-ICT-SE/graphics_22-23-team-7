package interfaces.objects;

import classes.math.Point3D;
import classes.math.Ray;
import classes.math.Vector3D;
import classes.objects.IntersectionHandler;

import java.awt.*;

public interface Shape {

    Point3D getPosition();
    Color getColor();
    IntersectionHandler intersection(Ray ray);
    Boolean isRayInRangeOfShape(Ray ray);
    Vector3D calcNormal(Point3D point);

}
