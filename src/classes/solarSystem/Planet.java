package classes.solarSystem;

import classes.math.Point3D;
import classes.math.Vector3D;
import classes.objects.Sphere;

import java.math.BigDecimal;

public class Planet extends Sphere {
    double mass; //amount of earth masses
    Vector3D acceleration;
    Vector3D velocity;

    public Planet(double mass, Vector3D velocity, Sphere sphere){
        super(sphere);
        this.mass = mass;
        this.acceleration = new Vector3D();
        this.velocity = velocity;
    }

}
