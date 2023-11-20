package Test;

import org.junit.Before;
import org.junit.Test;
import src.*;
import src.util.Vector;

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
    public void testAddCarNearLorryToLorrysRamp() {
        Volvo240 car = new Volvo240();
        car.setPosition(new Vector(1,1));
        lorry.lowerRamp();
        lorry.setPosition(new Vector(1,1));
        lorry.addCar(car);
        assertEquals(1, lorry.getCarCount());
    }

    @Test
    public void testSpeedFactor(){
        assertEquals(lorry.speedFactor(), 2, 0.00001);
    }

    @Test
    public void testCannotAddCarWithRampUp(){
       lorry.raiseRamp();
       lorry.addCar(new Volvo240());
       assertEquals(0, lorry.getCarCount());
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
    public void testRemoveCarSetsCorrectPosition(){
        Volvo240 car = new Volvo240();
        Volvo240 car2 = new Volvo240();

        lorry.lowerRamp();
        lorry.addCar(car);
        lorry.addCar(car2);
        lorry.raiseRamp();
        lorry.startEngine();
        lorry.turnLeft();

        lorry.move();
        lorry.move(); // Position: (-0.2, 0.0)
        lorry.stopEngine();
        lorry.lowerRamp();
        lorry.removeCar();
        lorry.removeCar();

        assertEquals(new Vector(-0.2 +  2.0, 0.0), car.getPosition());
        assertEquals(new Vector(-0.2 +  4.0, 0.0), car2.getPosition());
    }

    @Test
    public void testRampKeepsCarsAtLorryPosition(){
        Volvo240 car = new Volvo240();
        lorry.addCar(car);
        lorry.startEngine();
        lorry.move();
        lorry.move();
        assertEquals(lorry.getPosition(), car.getPosition());
    }


}
