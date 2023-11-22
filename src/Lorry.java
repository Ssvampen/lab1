package src;

import java.awt.*;

/**
 * Represents a Lorry that can transport other cars.
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
     * Adds a car to this lorry's ramp. The car must be within 1 meters of the lorry.
     * Sets the child car position to the lorry position.
     *
     * @param car Car to add.
     */
    public void addCar(Car car) {
        if(car.getPosition().distance(this.getPosition()) >= 1.0){
            return;
        }

        this.ramp.addCar(car);
        car.setPosition(this.getPosition()); // Set car position to parent position
    }

    @Override
    public void move() {
        super.move();
        this.ramp.teleportCars(getPosition());
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
     * Removes car from the lorry's ramp.
     */
    public void removeCar(){
        this.ramp.removeCar(getDirection());
    }

    @Override
    public int getCarCount(){
        return ramp.getCarCount();
    }

    @Override
    public double speedFactor() {
        return enginePower * 0.01;
    }

}
