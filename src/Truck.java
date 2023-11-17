package src;

import src.util.WeightClass;

import java.awt.*;

public abstract class Truck extends Car implements Hinged {

    private final Hinged hinged;

    public Truck(int nrDoors, Color color, int enginePower, String modelName, Hinged hinged) {
        super(nrDoors, color, enginePower, modelName, WeightClass.HUMONGOUS);
        this.hinged = hinged;
    }

    /**
     * Stars the engine of the truck.
     * Cannot be started if the truck's carriage is up.
     */
    @Override
    public void startEngine() {
        if(hinged.attacheableIsUp())
            return;

        super.startEngine();
    }

    @Override
    public boolean attacheableIsUp() {
        return hinged.attacheableIsUp();
    }

}
