package Test;

import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import src.Car;
import src.util.Direction;
import src.Volvo240;

import static org.junit.Assert.*;



public class CarTest {
    Car car;

    @Before
    public void CarInitzilizer() {
        car = new Volvo240();
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
        car.turnRight();
        assertEquals(car.getDirection(), Direction.EAST);
    }

    @Test
    public void testTurnLeft(){
        car.turnLeft();
        assertEquals(car.getDirection(), Direction.WEST);
    }

    @Test
    public void testSetColor(){
        car.setColor(Color.BLACK);
        assertEquals(car.getColor(), Color.BLACK);
    }

    @Test
    public void testSetGasWithAmountAbove1Fails() {
        car.startEngine();
        car.gas(2.0);
        assertEquals(car.getCurrentSpeed(), 0.1, 0.0001);
    }

    @Test
    public void testSetBrakeWithAmountAbove1Fails() {
        car.startEngine();
        car.brake(2.0);
        assertEquals(car.getCurrentSpeed(), 0.1, 0.0001);
    }

    @Test
    public void testCarIsStationary(){
        assertTrue(car.vehicleIsStationary());
    }

    @Test
    public void testAccelerateWithANegativeAmountOfBreak() {
        car.startEngine();
        car.gas(0.5);
        double speedAfterGas = car.getCurrentSpeed();
        car.brake(-0.9);
        assertTrue(car.getCurrentSpeed() <= speedAfterGas);
    }

    @Test
    public void testBreakWithANegativeAmountOfGas() {
        car.startEngine();
        car.gas(0.5);
        double speedAfterFirstGas = car.getCurrentSpeed();
        car.gas(-0.4);
        assertTrue(car.getCurrentSpeed() >= speedAfterFirstGas);
    }

    @Test
    public void testCurrentSpeedNeverExceedsEnginePower(){
        car.startEngine();
        car.gas(100000.0);
        assertTrue(car.getCurrentSpeed() <= car.getEnginePower());
    }

    @Test
    public void testCurrentSpeedNeverGoesBelowEnginePower(){
        car.startEngine();
        car.brake(100000.0);
        assertTrue(car.getCurrentSpeed() >= 0);
    }

}
