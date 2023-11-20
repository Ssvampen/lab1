package Test;

import org.junit.Before;
import org.junit.Test;

import src.Scania;
import src.LoadingPlatform;
import src.util.Vector;

import static org.junit.Assert.*;

public class ScaniaTest {
    Scania scania;

    @Before
    public void ScaniaInitializer() {
        scania = new Scania(new LoadingPlatform());
    }

    @Test
    public void testCannotStartEngineWithLoadingPlatformDown(){
        scania.increaseLoadingPlatformAngle(20);
        scania.startEngine();
        scania.gas(1);
        scania.move();
        assertEquals(scania.getPosition(), Vector.zero());

    }

    @Test
    public void testSpeedFactor(){
        assertEquals(scania.speedFactor(), 2, 0.00001);
    }

    @Test
    public void testCannotIncreaseLoadingPlatformWhenDriving(){
        scania.startEngine();
        scania.gas(1);
        scania.increaseLoadingPlatformAngle(1);
        assertFalse(scania.attacheableIsDown());
    }


    @Test
    public void testCannotDecreaseLoadingPlatformWhenDriving(){
        scania.startEngine();
        scania.gas(1);
        scania.increaseLoadingPlatformAngle(70);
        scania.decreaseLoadingPlatformAngle(70);
        assertFalse(scania.attacheableIsDown());
    }
}
