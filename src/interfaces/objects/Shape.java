package interfaces.objects;

import classes.math.Point3D;
import classes.math.Ray;
import classes.objects.IntersectionHandler;

public interface Shape {

    public Point3D getOrigin();
    public IntersectionHandler intersection(Ray ray);
    public Boolean isRayInRangeOfShape(Ray ray);
}
