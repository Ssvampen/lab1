package src;

import java.awt.*;

import src.util.WeightClass;

public class Saab95 extends Car {

    public boolean turboOn;

    public Saab95() {
        super(2, Color.red, 125, "Saab95", WeightClass.SMALL);
        turboOn = false;
    }

    /**
     * Sets the attribte turboOn to true.
     */
    public void setTurboOn() {
        turboOn = true;
    }

    /**
     * Sets the attribte turboOn to false.
     */
    public void setTurboOff() {
        turboOn = false;
    }

    /**
     * Calculates the the cars speed factor based on the engine power and turbo
     * @return A speed factor which is a (double)
     */
    @Override
    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }
}

