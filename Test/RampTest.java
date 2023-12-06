package Test;

import src.vehicle.Lorry;
import src.hingeable.Ramp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import src.vehicle.Volvo240;


public class RampTest {
    private Ramp ramp;
    private int maxEntitys;

    @Before
    public void RampInitializer(){
        maxEntitys = 5;
        ramp = new Ramp(maxEntitys);
    }

    @Test
    public void testRaiseRampIsUp(){
        ramp.raiseRamp();
        assertFalse(ramp.attacheableIsDown());
    }

    @Test
    public void testRaiseAndLowerIsDown(){
        ramp.raiseRamp();
        ramp.lowerRamp();
        assertTrue(ramp.attacheableIsDown());
    }

    @Test
    public void testAddTwoEntitysCount2(){
        ramp.addEntity(new Volvo240());
        ramp.addEntity(new Volvo240());
        assertEquals(2, ramp.getEntityCount());
    }


    @Test
    public void testAddAndRemoveEntityCount0(){
        Volvo240 entity = new Volvo240();
        ramp.lowerRamp();
        ramp.addEntity(entity);
        ramp.removeEntity();
        assertEquals(0, ramp.getEntityCount());
    }

    @Test
    public void testCannotAddRampedEntity(){
        Lorry lorry = new Lorry(new Ramp(10));
        ramp.addEntity(lorry);
        assertEquals(0, lorry.getEntityCount());
    }

}
