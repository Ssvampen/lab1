package Test;

import org.junit.Before;
import org.junit.Test;
import src.util.Vector;
import src.Volvo240;


import static org.junit.Assert.*;

public class VolvoTest {

    Volvo240 volvo;

    @Before
    public void CarInitzilizer() {
        volvo = new Volvo240();
    }


    @Test
    public void testModelName() {
        assertEquals(volvo.getModelName(), "Volvo240");
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
    public void testLongDrive(){
        volvo.startEngine(); // Current speed: 0.1
        volvo.gas(1.0); // Current speed: 0.1 + 1.25
        volvo.move(); // Position: ( 0 , 1.35 )
        volvo.move(); // Position: ( 0 , 2.7 )
        volvo.move(); // Position: ( 0 , 4.05 )
        volvo.turnLeft(); // Direction: West
        volvo.move(); // Position: ( -1.35 , 4.05 )
        volvo.brake(0.3); // Current speed: 0.1+1.25 - (1.25*0.3) = 0.975
        volvo.move(); // Position: ( -2.325 , 4.05 )
        volvo.turnLeft(); // Direction: South
        volvo.move(); // Position: ( -2.325 , 3.075 )
        volvo.move(); // Position: ( -2.325 , 2.1 )

        assertEquals(volvo.getPosition(), new Vector(-2.325, 2.1));
    }

    @Test
    public void testBrakeSetsCorrectSpeed(){
        volvo.startEngine();
        volvo.gas(0.75);
        volvo.brake(0.5);
        assertEquals(volvo.getCurrentSpeed(), 0.4125, 0.00001);
    }


}
