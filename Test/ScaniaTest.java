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
    public void testIfCanDriveWhenLoadingPlatformDown(){
        scania.startEngine();
        scania.decreaseLoadingPlatformAngle(1);
        scania.gas(1);
        scania.move();
        assertEquals(scania.getPosition(), Vector.zero());

    }

    @Test
    public void testIfCanLowerLoadingPlatformWhileDriving(){
        scania.startEngine();
        scania.gas(1);
        scania.decreaseLoadingPlatformAngle(1);
        assertTrue(scania.attacheableIsUp());
    }
}
