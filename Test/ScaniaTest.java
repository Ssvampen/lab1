package Test;

import org.junit.Before;
import org.junit.Test;

import src.model.vehicle.Scania;
import src.model.hingeable.LoadingPlatform;
import src.model.util.Vector;

import static org.junit.Assert.*;

public class ScaniaTest {
    Scania scania;

    @Before
    public void ScaniaInitializer() {
        scania = new Scania(new LoadingPlatform());
    }

    @Test
    public void testCannotStartEngineWithLoadingPlatformDown(){
        scania.increasePlatformAngle(20);
        scania.startEngine();
        scania.gas(1);
        scania.move();
        assertEquals(scania.getPosition(), Vector.zero());

    }

    @Test
    public void testSpeedFactor(){
        assertEquals(scania.speedFactor(), 0.5, 0.00001);
    }

    @Test
    public void testCannotIncreaseLoadingPlatformWhenDriving(){
        scania.startEngine();
        scania.gas(1);
        scania.increasePlatformAngle(1);
        assertFalse(scania.attacheableIsDown());
    }


    @Test
    public void testCannotDecreaseLoadingPlatformWhenDriving(){
        scania.startEngine();
        scania.gas(1);
        scania.increasePlatformAngle(70);
        scania.decreasePlatformAngle(70);
        assertFalse(scania.attacheableIsDown());
    }
}
