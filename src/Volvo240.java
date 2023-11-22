package src;

import java.awt.*;

import src.util.WeightClass;

public class Volvo240 extends Car{
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
        return enginePower * 0.01 * trimFactor;
    }

}