package Test;

import src.Lorry;
import src.Ramp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import src.Volvo240;
import src.util.Direction;


public class RampTest {
    private Ramp ramp;
    private int maxCars;

    @Before
    public void RampInitializer(){
        maxCars = 5;
        ramp = new Ramp(maxCars);
    }

    @Test
    public void testRaiseRampIsUp(){
        ramp.raiseRamp();
        assertTrue(ramp.attacheableIsUp());
    }

    @Test
    public void testRaiseAndLowerIsDown(){
        ramp.raiseRamp();
        ramp.lowerRamp();
        assertFalse(ramp.attacheableIsUp());
    }

    @Test
    public void testAddTwoCarsCount2(){
        ramp.addCar(new Volvo240());
        ramp.addCar(new Volvo240());
        assertEquals(2, ramp.getCarCount());
    }


    @Test
    public void testAddAndRemoveCarCount0(){
        Volvo240 car = new Volvo240();
        ramp.addCar(car);
        ramp.removeCar(Direction.NORTH);
        assertEquals(0, ramp.getCarCount());
    }

    @Test
    public void testCannotAddRampedCar(){
        Lorry lorry = new Lorry(new Ramp(10));
        ramp.addCar(lorry);
        assertEquals(0, lorry.getCarCount());
    }

}
