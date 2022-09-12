package classes.Utility;

import classes.Utility.Vector3D;

public class Ray {
    Point3D origin; /*Ray startpunt */
    Vector3D direction; /*Ray richting */
    float t; /*afstand scalar */


    public Ray(Point3D origin, Vector3D direction)
    {
        this.origin = new Point3D(origin);
        this.direction = new Vector3D(direction);
    }

}
