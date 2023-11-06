package Test;

import org.junit.Before;
import org.junit.Test;
import src.Saab95;
import src.Volvo240;

import static org.junit.Assert.assertEquals;

public class SaabTest {

    Saab95 saab;

    @Before
    public void CarInitzilizer() {
        saab = new Saab95();
    }

    @Test
    public void testEnginePower(){
        assertEquals(saab.getEnginePower(), 125, 0.000001);
    }

    @Test
    public void testSpeedFactorWithTurboOff(){
        saab.setTurboOff();
        assertEquals(saab.speedFactor(), 1.25, 0.00001);
    }

    @Test
    public void testSpeedFactorWithTurboOn(){
        saab.setTurboOn();
        assertEquals(saab.speedFactor(), 1.625, 0.00001);
    }

    @Test
    public void testSetTurboOn(){
        saab.setTurboOn();
        assertEquals(saab.turboOn, true);
    }

    @Test
    public void testSetTurboOff(){
        saab.setTurboOff();
        assertEquals(saab.turboOn, false);
    }


    @Test
    public void testGasWithTurboOnSetsCorrectSpeed(){
        saab.startEngine();
        saab.setTurboOn();
        saab.gas(0.5);
        assertEquals(saab.getCurrentSpeed(), 0.9125, 0.00001);
    }


    @Test
    public void testGasWithTurboOffSetsCorrectSpeed(){
        saab.startEngine();
        saab.setTurboOff();
        saab.gas(0.5);
        assertEquals(saab.getCurrentSpeed(), 0.725, 0.00001);
    }


    @Test
    public void testBrakeWithTurboOnSetsCorrectSpeed(){
        saab.startEngine();
        saab.setTurboOn();
        saab.gas(0.75);
        saab.brake(0.5);
        assertEquals(saab.getCurrentSpeed(), 0.50625, 0.00001);
    }

    @Test
    public void testBrakeWithTurboOffSetsCorrectSpeed(){
        saab.startEngine();
        saab.gas(0.75);
        saab.brake(0.5);
        assertEquals(saab.getCurrentSpeed(), 0.4125, 0.00001);
    }

}
