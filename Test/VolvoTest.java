package Test;

import org.junit.Before;
import org.junit.Test;
import src.Volvo240;


import static org.junit.Assert.*;

public class VolvoTest {

    Volvo240 volvo;

    @Before
    public void CarInitzilizer() {
        volvo = new Volvo240();
    }


    @Test
    public void testEnginePower(){
        assertEquals(volvo.getEnginePower(), 100, 0.000001);
    }

    @Test
    public void testTrimFactor(){
        assertEquals(Volvo240.trimFactor, 1.25, 0.00001);
    }

    @Test
    public void testSpeedFactor(){
        assertEquals(volvo.speedFactor(), 1.25, 0.00001);
    }

    @Test
    public void testGasSetsCorrectSpeed(){
        volvo.startEngine();
        volvo.gas(0.5);
        assertEquals(volvo.getCurrentSpeed(), 0.725, 0.00001);
    }


    @Test
    public void testBrakeSetsCorrectSpeed(){
        volvo.startEngine();
        volvo.gas(0.75);
        volvo.brake(0.5);
        assertEquals(volvo.getCurrentSpeed(), 0.4125, 0.00001);
    }


}
