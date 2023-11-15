package Test;

import src.Ramp;
import src.Car;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import src.Volvo240;

import java.awt.*;


public class RampTest {
    private Ramp ramp;
    private int maxCars;

    @Before
    public void RampInitializer(){
        maxCars = 5;
        ramp = new Ramp(maxCars);
    }
}
