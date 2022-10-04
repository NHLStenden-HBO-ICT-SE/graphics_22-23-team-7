package testing.object;

import classes.math.Point3D;
import classes.math.Ray;
import classes.math.Vector3D;
import classes.objects.Sphere;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the intersectionHandler class
 */
public class IntersectionHandlerTest {

    /**
     * Test Method that calculates intersection point between Sphere and Ray
     */
    @Test
    public void calculateIntersectionPoint() {


        //
        // sphere init
        //
        Point3D posS = new Point3D(0, 0, 5); //position
        double radS = 2; //radius

        Sphere s = new Sphere(posS, radS);

        //
        // Ray init
        //
        Point3D posR = new Point3D(0, 0, 1); //position
        Vector3D directionR = new Vector3D(0, 0.5, 1); //direction
        double lengthR = 50; //length

        Ray testRay = new Ray(posR, directionR, lengthR);

        // Sphere intersection with ray
        Point3D rsIntersection = s.intersection(testRay).calculateIntersectionPoint();

        //
        // Calculated intersection point init
        //
        Point3D result = new Point3D(0, 1.2, 3.4);

        //
        // Checks if the intersection point calculation is correct
        //
        assertEquals(result.x,  Math.round(rsIntersection.x * 10d) / 10d);
        assertEquals(result.y,  Math.round(rsIntersection.y * 10d) / 10d);
        assertEquals(result.z,  Math.round(rsIntersection.z * 10d) / 10d);

    }
}
