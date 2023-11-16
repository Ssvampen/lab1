package Test;

import org.junit.Before;
import org.junit.Test;
import src.Lorry;
import src.Ramp;
import src.Volvo240;
import src.util.Direction;
import src.util.Vector;
import src.Car;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LorryTest {
    private Lorry lorry;

    @Before
    public void initializeLorry() {
        lorry = new Lorry(new Ramp(5));
    }

    // TODO: we don't want to expose getRamp() but that breaks EVERYTHING with Rampable....


    @Test
    public void testCannotStartEngineWithRampDown(){
        lorry.raiseRamp();
        lorry.startEngine();
        lorry.gas(1);
        lorry.move();
        assertEquals(lorry.getPosition(), Vector.zero());
    }

    @Test
    public void testAddCarNearLorryToLorrysRamp() {
        Volvo240 car = new Volvo240();
        car.setPosition(new Vector(1,1));
        lorry.setPosition(new Vector(1,1));
        lorry.addCar(car);
        assertEquals(1, lorry.getRamp().getCarCount());
    }


    @Test
    public void testSpeedFactor(){
        assertEquals(lorry.speedFactor(), 2, 0.00001);
    }

    @Test
    public void testCannotAddCarWithRampUp(){
       lorry.raiseRamp();
       lorry.addCar(new Volvo240());
       assertEquals(0, lorry.getRamp().getCarCount());
    }

    @Test
    public void testCannotLowerRampWhenDriving(){
        lorry.startEngine();
        lorry.raiseRamp();
        lorry.startEngine();
        lorry.move();
        lorry.lowerRamp();
        assertTrue(lorry.attacheableIsUp());
    }


    @Test
    public void testRemoveCarSetsCorrectPosition(){
        Volvo240 car = new Volvo240();
        lorry.addCar(car);
        lorry.startEngine();
        lorry.turnLeft();
        lorry.move();
        lorry.move(); // Position: (-0.2, 0.0)
        lorry.removeCar();

        assertEquals(new Vector(-0.2 +  2.0, 0.0), car.getPosition());
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
