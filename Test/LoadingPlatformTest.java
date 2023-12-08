package Test;

import org.junit.Before;
import org.junit.Test;
import src.model.hingeable.LoadingPlatform;

import static org.junit.Assert.*;

public class LoadingPlatformTest {
    LoadingPlatform loadingPlatform;

    @Before
    public void LoadingPlatformInitializer() {
        loadingPlatform = new LoadingPlatform();
    }

    @Test
    public void testRaiseIsUp(){
        loadingPlatform.increasePlatformAngle(40.0);
        assertTrue(loadingPlatform.attacheableIsDown());
    }

    @Test
    public void testRaiseUpThenDownIsDown(){
        loadingPlatform.increasePlatformAngle(40.0);
        loadingPlatform.decreasePlatformAngle(40.0);
        assertFalse(loadingPlatform.attacheableIsDown());
    }

    @Test
    public void testDefaultDown(){
        assertFalse(loadingPlatform.attacheableIsDown());
    }

    @Test
    public void testCannotRaiseHigherThan70(){
        loadingPlatform.increasePlatformAngle(100.0);
        assertEquals(70.0, loadingPlatform.getAngle(), 0.0001);
    }

    @Test
    public void testCannotLowerMoreThan0(){
        loadingPlatform.decreasePlatformAngle(0.0);
        assertEquals(0.0, loadingPlatform.getAngle(), 0.0001);
    }

    @Test
    public void testCannotLowerNegative(){
        loadingPlatform.decreasePlatformAngle(-100.0);
        assertEquals(0.0, loadingPlatform.getAngle(), 0.0001);
    }


    @Test
    public void testCannotRaiseNegative(){
        loadingPlatform.increasePlatformAngle(-100.0);
        assertEquals(0.0, loadingPlatform.getAngle(), 0.0001);
    }


}
