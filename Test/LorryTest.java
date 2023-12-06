package Test;

import org.junit.Before;
import org.junit.Test;
import src.hingeable.Ramp;
import src.util.Vector;
import src.vehicle.Lorry;
import src.vehicle.Volvo240;

import static org.junit.Assert.*;

public class LorryTest {
    private Lorry lorry;

    @Before
    public void initializeLorry() {
        lorry = new Lorry(new Ramp(5));
    }

    @Test
    public void testCannotStartEngineWithRampDown(){
        lorry.lowerRamp();
        lorry.startEngine();
        lorry.gas(1);
        lorry.move();
        assertEquals(lorry.getPosition(), Vector.zero());
    }

    @Test
    public void testAddEntityNearLorryToLorrysRamp() {
        Volvo240 entity = new Volvo240();
        entity.setPosition(new Vector(1,1));
        lorry.lowerRamp();
        lorry.setPosition(new Vector(1,1));
        lorry.addEntity(entity);
        assertEquals(1, lorry.getEntityCount());
    }

    @Test
    public void testSpeedFactor(){
        assertEquals(lorry.speedFactor(), 2, 0.00001);
    }

    @Test
    public void testCannotAddEntityWithRampUp(){
        lorry.raiseRamp();
        lorry.addEntity(new Volvo240());
        assertEquals(0, lorry.getEntityCount());
    }

    @Test
    public void testCannotLowerRampWhenDriving(){
        lorry.startEngine();
        lorry.raiseRamp();
        lorry.startEngine();
        lorry.move();
        lorry.lowerRamp();
        assertFalse(lorry.attacheableIsDown());
    }


    @Test
    public void testRemoveEntitySetsCorrectPosition(){
        Volvo240 entity = new Volvo240();
        Volvo240 entity2 = new Volvo240();

        lorry.lowerRamp();
        lorry.addEntity(entity);
        lorry.addEntity(entity2);
        lorry.raiseRamp();
        lorry.startEngine();
        lorry.turnLeft();

        lorry.move();
        lorry.move(); // Position: (-0.2, 0.0)
        lorry.stopEngine();
        lorry.lowerRamp();
        lorry.removeVehicle();
        lorry.removeVehicle();

        assertEquals(new Vector(-0.2 +  2.0, 0.0), entity.getPosition());
        assertEquals(new Vector(-0.2 +  4.0, 0.0), entity2.getPosition());
    }

    @Test
    public void testRampKeepsEntitysAtLorryPosition(){
        Volvo240 entity = new Volvo240();
        lorry.addEntity(entity);
        lorry.startEngine();
        lorry.move();
        lorry.move();
        assertEquals(lorry.getPosition(), entity.getPosition());
    }


}
