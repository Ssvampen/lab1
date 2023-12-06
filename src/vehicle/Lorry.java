package src.vehicle;

import src.hingeable.Ramp;
import src.hingeable.Rampable;
import src.util.Vector;

import java.awt.*;

/**
 * Represents a Lorry that can transport other vehicles.
 */
public class Lorry extends Truck implements Rampable {
    // Is not set to final because the lorry can (if implemented) change ramp
    private Ramp ramp;

    /**
     * Contructor.
     * @param ramp The ramp connected to this instance of a lorry.
     */
    public Lorry(Ramp ramp) {
        super(2, Color.YELLOW, 200, "någon långtradarmodell", ramp);
        this.ramp = ramp;
    }

    /**
     * Adds a vehicle to this lorry's ramp. The vehicle must be within 1 meters of the lorry.
     * Sets the child vehicle position to the lorry position.
     *
     * @param vehicle Vehicle to add.
     */
    public void addEntity(Vehicle vehicle) {
        // The vehicle is only added if it is close enough to the lorry and the ramp is down.
        this.ramp.addEntity(vehicle);
        vehicle.setPosition(this.getPosition()); // Set vehicle position to parent position
    }

    @Override
    public void move() {
        super.move();
        this.ramp.setPosition(getPosition());
    }

    @Override
    public void raiseRamp(){
        this.ramp.raiseRamp();
    }

    @Override
    public void lowerRamp(){
        if(!vehicleIsStationary())
            return;

        this.ramp.lowerRamp();
    }

    /**
     * Removes vehicle from the lorry's ramp.
     */
    public void removeVehicle(){
        this.ramp.removeEntity();
    }

    @Override
    public int getEntityCount(){
        return ramp.getEntityCount();
    }

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01;
    }

    @Override
    public void setPosition(Vector position) {
        super.setPosition(position);
        ramp.setPosition(position);
    }

    @Override
    public void setDirection(Vector direction) {
        super.setDirection(direction);
        ramp.setDirection(direction);
    }

}
