package Test;

import org.junit.Before;
import org.junit.Test;
import src.LoadingPlatform;

import javax.swing.plaf.SplitPaneUI;

import static org.junit.Assert.*;

public class LoadingPlatformTest {
    LoadingPlatform loadingPlatform;

    @Before
    public void LoadingPlatformInitializer() {
        loadingPlatform = new LoadingPlatform();
    }

    @Test
    public void testRaiseIsUp(){
        loadingPlatform.raise(40.0);
        assertTrue(loadingPlatform.attacheableIsUp());
    }

    @Test
    public void testRaiseUpThenDownIsDown(){
        loadingPlatform.raise(40.0);
        loadingPlatform.lower(40.0);
        assertFalse(loadingPlatform.attacheableIsUp());
    }

    @Test
    public void testDefaultDown(){
        assertFalse(loadingPlatform.attacheableIsUp());
    }

    @Test
    public void testCannotRaiseHigherThan70(){
        loadingPlatform.raise(100.0);
        assertEquals(70.0, loadingPlatform.getAngle(), 0.0001);
    }

    @Test
    public void testCannotLowerMoreThan0(){
        loadingPlatform.lower(0.0);
        assertEquals(0.0, loadingPlatform.getAngle(), 0.0001);
    }

    @Test
    public void testCannotLowerNegative(){
        loadingPlatform.lower(-100.0);
        assertEquals(0.0, loadingPlatform.getAngle(), 0.0001);
    }


    @Test
    public void testCannotRaiseNegative(){
        loadingPlatform.raise(-100.0);
        assertEquals(0.0, loadingPlatform.getAngle(), 0.0001);
    }


}
