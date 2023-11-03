import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {
    Volvo240 volvo;

    @Before
    public void CarInitizlizer() {
        volvo = new Volvo240();
    }

    @Test
    public void testThatVolvoHasTwoDoors() {
        assertEquals(volvo.getNrDoors(), 4);
    }

    @Test
    public void testInitialDirection(){
       assertEquals(volvo.getDirection(), Direction.NORTH);
    }

    @Test
    public void testThatCarDrivesForwardWithTheCorrectSpeed() {
        Volvo240 testCar = new Volvo240();
        testCar.move();
        assertTrue(testCar.getPosition().getY() == testCar.getCurrentSpeed());
    }

    @Test
    public void testThatEngineIsTurnedOffWhenCarIsCreated() {
        Volvo240 testCar = new Volvo240();
        assertEquals(testCar.getCurrentSpeed(), 0);
    }

    @Test
    public void testStopEngineSetsSpeedTo0(){
        volvo.stopEngine();
        assertEquals(volvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testStartEngineSetsCorrectSpeed(){
        volvo.startEngine();
        assertEquals(volvo.getCurrentSpeed(), 0.1);
    }

    @Test
    public void testTurnRight(){
        volvo.turnRight();
        assertTrue(volvo.getDirection() == Direction.EAST);
    }


}
