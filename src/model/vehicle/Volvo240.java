package src.model.vehicle;

import java.awt.*;

import src.model.util.WeightClass;

public class Volvo240 extends Vehicle {
    public final static double trimFactor = 1.25;

    public Volvo240(){
        super(4, Color.black, 100, "Volvo240", WeightClass.SMALL);
    }

    /**
     * Calculates the the cars speed factor based on the engine power and trimfactor
     * @return A speed factor which is a (double)
     */
    @Override
    public double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

}