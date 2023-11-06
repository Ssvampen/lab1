package Test;

import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import src.Car;
import src.Direction;
import src.Volvo240;

import static org.junit.Assert.*;



public class CarTest {
    Car car;

    @Before
    public void CarInitizlizer() {
        volvo = new Volvo240();
    }

    @Test
    public void testThatVolvoHasTwoDoors() {
        assertEquals(car.getNrDoors(), 4);
    }

    @Test
    public void testInitialDirection(){
       assertEquals(car.getDirection(), Direction.NORTH);
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
        assertEquals(testCar.getCurrentSpeed(), 0, 0.00001);
    }

    @Test
    public void testStopEngineSetsSpeedTo0(){
        car.stopEngine();
        assertEquals(car.getCurrentSpeed(), 0, 0.00001);
    }

    @Test
    public void testStartEngineSetsCorrectSpeed(){
        car.startEngine();
        assertEquals(car.getCurrentSpeed(), 0.1, 0.00001);
    }

    @Test
    public void testTurnRight(){
        volvo.turnRight();
        assertTrue(volvo.getDirection() == Direction.EAST);
    }


}
