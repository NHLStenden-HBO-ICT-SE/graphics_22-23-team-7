package classes.solarSystem;

import classes.math.Point3D;
import classes.math.Vector3D;
import errors.math.ZeroNumException;

/**
 * credit: https://rosettacode.org/wiki/N-body_problem#Java
 * credit: https://github.com/aldebran97/three_body
 * credit: https://math.stackexchange.com/questions/9324/two-body-problem-circular-orbits
 */
public final class Gravity {
    //universal gravitational constant
//    private static final double G = 0.00000000066743; // m^3 kg^-1 s^-2
    private static final double G = 0.05; //gc
    private static final double interval = 0.5; //time interval (used for acceleration)
    private static final double maxF = 5; //max force
    private static final Vector3D resetVector = new Vector3D(0, 0, 0); //used to reset force

    /**
     * calculates force on planet from gravity
     *
     * @param planets
     */
    private static void calculateForce(Planet[] planets) {
        //loop through planets
        for (var planet : planets) {
            planet.setForce(resetVector); //set force to 0

            //planet mass
            double planetM = planet.getMass();

            //planet position
            Point3D planetPos = planet.getPosition();

            //loop through all the other planets
            for (var planet2 : planets) {

                if (planet == planet2) continue; //if planet is the same object as planet2 go to next planet2

                //planet2 mass
                double planet2M = planet2.getMass();

                if (planet2M == 0) continue; //if planet2 mass is 0 we don't need to calculate its gravitational pull

                //planet2 position
                Point3D planet2Pos = planet2.getPosition();

                //distance between planet and planet2
                double r = planetPos.distance(planet2Pos);

                //calculate the |r|^2 of planet to planet2
                double magnitude = Math.pow(r, 2);

                //universal law of gravity -isaac newton
                // Force = Gmm / |r|^2
                double ulog = (G * (planetM == 0 ? 1 : planetM) * planet2M) / magnitude; // kg * m / sE2

                //check if the universal law of gravity exceeds our set max force
                if (ulog > maxF) {
                    // credit: aldebran97
                    ulog = maxF + Math.log(1 + Math.abs(ulog - maxF));
                }

                //calculate the direction of gravity (as vector)
                Vector3D forceDirection = planet.getPosition().getVector(planet2.getPosition());

                //combine all force vectors
                planet.setForce(planet.getForce().add(forceDirection.multiply(ulog / r)));
            }
        }


    }

    /**
     * adds planet velocity to current position
     *
     * @param planets
     */
    private static void calculatePositions(Planet[] planets) {
        //loop through planets
        for (Planet planet : planets) {
            //add vector to current position
            planet.setPosition(planet.getPosition().addVector(planet.getVelocity().multiply(interval)));
        }
    }

    /**
     * turns force (F = ma (kg * m / sE2)) from acceleration into velocity
     *
     * @param planets
     */
    private static void calculateVelocities(Planet[] planets) {
        //loop through planets
        for (Planet planet : planets) {
            var planetM = planet.getMass(); //mass
            var planetF = planet.getForce(); //force

            //F = ma -> a = F/m
            var acceleration = planetM == 0 ? planetF : planetF.divide(planetM); // m / sE2

            // vel =   acc   * s
            // m/s = (m/sE2) * s
            planet.setVelocity(planet.getVelocity().add(acceleration.multiply(interval))); // m/s
        }
    }

    /**
     * calculates position after gravity
     *
     * @param planets
     */
    public static void movePlanets(Planet[] planets) {
        calculatePositions(planets);
        if (planets.length < 2) return;
        calculateForce(planets);
        calculateVelocities(planets);
    }

    /**
     * calculates escape velocity of an object traveling around a planet at a specific distance
     *
     * @param planet   main planet
     * @param distance between center points of planet and object
     * @return velocity
     */
    public static double calculateEscapeVelocity(Planet planet, double distance) {
        if (planet.getMass() == 0 || distance == 0) try {
            throw new ZeroNumException();
        } catch (ZeroNumException e) {
            throw new RuntimeException(e);
        }
        return Math.sqrt(2 * G * planet.getMass() / distance);
    }

    /**
     * calculates the perfect (circular) velocity for an orbit around a planet
     *
     * @param planet   main planet
     * @param distance disntance between center point of planet and object
     * @return velocity
     */
    public static double calculatePerfectVelocity(Planet planet, double distance) {
        if (planet.getMass() == 0 || distance == 0) try {
            throw new ZeroNumException();
        } catch (ZeroNumException e) {
            throw new RuntimeException(e);
        }
        return Math.sqrt(G * planet.getMass() / distance);
    }
}

