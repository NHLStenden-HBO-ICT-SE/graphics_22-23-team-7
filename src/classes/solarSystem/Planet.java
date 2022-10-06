package classes.solarSystem;

import classes.math.Vector3D;
import classes.objects.Sphere;
import errors.math.NegativeNumException;

public class Planet extends Sphere {
    private double mass; //amount of earth masses
    private Vector3D force;
    private Vector3D velocity;

    public Planet(double mass, Vector3D velocity, Sphere sphere) {
        super(sphere.getPosition(), sphere.getRadius());
        _setMass(mass);
        this.force = new Vector3D();
        this.velocity = velocity;
    }

    /**
     * sets mass
     * private due to potential overriding of public setter
     *
     * @param mass
     */
    private void _setMass(double mass) {
        //if check if mass is a negative number throw exception
        try {
            if (mass < 0) throw new NegativeNumException("mass can't be a negative number");
        } catch (NegativeNumException e) {
            throw new RuntimeException(e);
        }

        //else ->
        this.mass = mass;
    }

    public void setMass(double mass) {
        _setMass(mass);
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
}
