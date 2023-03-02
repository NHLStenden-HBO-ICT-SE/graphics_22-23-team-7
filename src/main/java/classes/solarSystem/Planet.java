package classes.solarSystem;

import classes.math.Point3D;
import classes.math.Vector3D;
import errors.math.NegativeNumException;

/**
 *
 */
public class Planet {
    private double mass;
    private Vector3D velocity;
    private Point3D origin;
    private Vector3D force;
    public Planet(double mass, Vector3D velocity, Point3D origin) {
        this.mass = mass;
        this.velocity = velocity;
        this.origin = origin;
    }

    public void setMass(double mass) throws NegativeNumException {
        if (mass < 0) {
            throw new NegativeNumException("Mass has to be positive.");
        }
        this.mass = mass;
    }

    public double getMass() {
        return mass;
    }

    public void setVelocity(Vector3D velocity) {
        this.velocity = velocity;
    }

    public Vector3D getVelocity() {
        return velocity;
    }

    public Vector3D getForce() {
        return force;
    }

    public void setForce(Vector3D force) {
        this.force = force;
    }

    public Point3D getPosition() {
        return origin;
    }

    public void setPosition(Point3D origin) {
        this.origin = origin;
    }
}
