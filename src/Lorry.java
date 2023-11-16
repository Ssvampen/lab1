package src;

import java.awt.*;

public class Lorry extends Truck implements Rampable {
    // Is not set to final because the lorry can (if implemented) change ramp
    private Ramp ramp;

    public Lorry(Ramp ramp) {
        super(2, Color.YELLOW, 200, "någon långtradarmodell", ramp);
        this.ramp = ramp;
    }

    public void addCar(Car car) {
        if(car.getPosition().distance(this.getPosition()) >= 1.0){
            return;
        }

        this.ramp.addCar(car);
    }

    @Override
    public void move() {
        super.move();
        this.ramp.teleportCars(getPosition());
    }

    public void raiseRamp(){
        this.ramp.raise();
    }

    public void lowerRamp(){
        if(!vehicleIsStationary())
            return;

        this.ramp.lower();
    }

    public Car removeCar(){
        return this.ramp.removeCar(getDirection());
    }

    @Override
    public double speedFactor() {
        return enginePower * 0.01;
    }

    @Override
    public Ramp getRamp() {
        return this.ramp;
    }
}
