package testing.object;

import classes.math.Point3D;
import classes.math.Ray;
import classes.math.Vector3D;
import classes.objects.IntersectionHandler;
import classes.objects.Sphere;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Sphere class
 */
public class SphereTest {

    /**
     * * Test method that checks if the ray does intersect with a sphere
     */
    @Test
    public void testIntersectionHit() {

        //
        // Sphere init
        //
        Point3D posS = new Point3D(5, 5, 5); //position
        double radS = 2; //radius

        Sphere s = new Sphere(posS, radS);

        //
        // Ray init
        //
        Point3D posR = new Point3D(0, 0, 1); //position
        Vector3D directionR = new Vector3D(1, 1, 1); //direction
        double lengthR = 50; //length

        Ray testRay = new Ray(posR, directionR, lengthR);


        // Sphere intersection with ray
        IntersectionHandler rsIntersection = s.intersection(testRay);

        // Check if ray intersects sphere
        assertTrue(rsIntersection.isIntersected());

        // Length of ray intersection equals this amount
        assertEquals(6.257, Math.round(rsIntersection.getLength() * 1000D) / 1000D);

    }

    /**
     * Test method that checks if the ray doesn't intersect with a sphere
     */
    @Test
    public void testIntersectionNotHit() {

        //
        // sphere init
        //
        Point3D posS = new Point3D(5, 5, 5); //position
        double radS = 2; //radius

        Sphere s = new Sphere(posS, radS);

        //
        // Ray init
        //
        Point3D posR = new Point3D(0, 0, 1); //position
        Vector3D directionR = new Vector3D(2, 2, 0.5); //direction
        double lengthR = 50; //length

        Ray testRay = new Ray(posR, directionR, lengthR);

        // Sphere intersection with ray
        IntersectionHandler rsIntersection = s.intersection(testRay);

        // Checks if ray doesn't intersect with sphere
        assertFalse(rsIntersection.isIntersected());
    }
}