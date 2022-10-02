package classes.solarSystem;

import classes.math.Point3D;
import classes.math.Vector3D;

/**
 * credit: https://rosettacode.org/wiki/N-body_problem#Java
 */
public final class Gravity {
    //universal gravitational constant
//    private static final double G = 0.00000000066743; // m^3 kg^-1 s^-2
    private static final double G = 0.1; //gc
    private static final Vector3D nullVector = new Vector3D(0, 0, 0);

    private static void calculateAccelerations(Planet[] planets) {
        for (var planet : planets) {
            if (planet.ignore) continue;
            planet.acceleration = nullVector;

            Point3D planetPos = planet.getPosition();

            for (var planet2 : planets) {

                if (planet == planet2 || planet2.mass == 0 || planet2.ignore) continue;

                Point3D planet2Pos = planet2.getPosition();

                double magnitude = Math.pow(planetPos.distance(planet2Pos), 3);

                double length = (G * planet2.mass) / magnitude;

                var vector = planet.acceleration.add(planetPos.getVector(planet2Pos).multiply(length));
                planet.acceleration = vector;
            }
        }


    }

    private static void calculatePositions(Planet[] planets) {
        for (Planet planet : planets) {
            planet.setPosition(planet.getPosition().addVector(planet.velocity).addVector(planet.acceleration.multiply(0.5)));
            var a = 0;
        }
    }

    private static void calculateVelocities(Planet[] planets) {
        for (Planet planet : planets) {
            planet.velocity = planet.velocity.add(planet.acceleration);
        }
    }

    public static void movePlanets(Planet[] planets) {
        calculateAccelerations(planets);
        calculatePositions(planets);
        calculateVelocities(planets);
    }

}
