package src.model.vehicle;

import src.model.hingeable.Hingeable;
import src.model.util.WeightClass;

import java.awt.*;

/**
 * The truck class is a vehicle (Car) that is expected to have a carrige.
 */

public abstract class Truck extends Vehicle implements Hingeable {

    private final Hingeable hingeable;

    /**
     * Constructor
     * All trucks have  the weightclass "HUMONGOUS" by defualt.
     * @param nrDoors Number of doors.
     * @param color Color of the vehicle.
     * @param enginePower The engine power.
     * @param modelName The vehicles model name.
     * @param hingeable The vehicles carrige (e.g. a ramp).
     */
    public Truck(int nrDoors, Color color, int enginePower, String modelName, Hingeable hingeable) {
        super(nrDoors, color, enginePower, modelName, WeightClass.HUMONGOUS);
        this.hingeable = hingeable;
    }

    /**
     * Stars the engine of the truck.
     * Cannot be started if the truck's carriage is up.
     */
    @Override
    public void startEngine() {
        if(hingeable.attacheableIsDown())
            return;

        super.startEngine();
    }

    @Override
    public boolean attacheableIsDown() {
        return hingeable.attacheableIsDown();
    }

}
