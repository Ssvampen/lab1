package Test;

import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import src.model.vehicle.Vehicle;
import src.model.vehicle.Volvo240;
import src.model.util.Vector;

import static org.junit.Assert.*;



public class VehicleTest {
    Vehicle vehicle;

    @Before
    public void VehicleInitzilizer() {
        vehicle = new Volvo240();
    }

    @Test
    public void testThatVolvoHasTwoDoors() {
        assertEquals(vehicle.getNrDoors(), 4);
    }

    @Test
    public void testInitialDirection(){
        assertEquals(vehicle.getDirection(), Vector.NORTH);
    }

    @Test
    public void testThatVehicleDrivesForwardWithTheCorrectSpeed() {
        Volvo240 testVehicle = new Volvo240();
        testVehicle.move();
        assertTrue(testVehicle.getPosition().getY() == testVehicle.getCurrentSpeed());
    }

    @Test
    public void testThatEngineIsTurnedOffWhenVehicleIsCreated() {
        Volvo240 testVehicle = new Volvo240();
        assertEquals(testVehicle.getCurrentSpeed(), 0, 0.00001);
    }

    @Test
    public void testStopEngineSetsSpeedTo0(){
        vehicle.stopEngine();
        assertEquals(vehicle.getCurrentSpeed(), 0, 0.00001);
    }

    @Test
    public void testStartEngineSetsCorrectSpeed(){
        vehicle.startEngine();
        assertEquals(vehicle.getCurrentSpeed(), 0.1, 0.00001);
    }

    @Test
    public void testTurnRight(){
        vehicle.turnRight();
        assertEquals(vehicle.getDirection(), Vector.EAST);

    }

    @Test
    public void testTurnLeft(){
        vehicle.turnLeft();
        assertEquals(vehicle.getDirection(), Vector.WEST);
    }

    @Test
    public void testSetColor(){
        vehicle.setColor(Color.BLACK);
        assertEquals(vehicle.getColor(), Color.BLACK);
    }

    @Test
    public void testSetGasWithAmountAbove1Fails() {
        vehicle.startEngine();
        vehicle.gas(2.0);
        assertEquals(vehicle.getCurrentSpeed(), 0.1, 0.0001);
    }

    @Test
    public void testSetBrakeWithAmountAbove1Fails() {
        vehicle.startEngine();
        vehicle.brake(2.0);
        assertEquals(vehicle.getCurrentSpeed(), 0.1, 0.0001);
    }

    @Test
    public void testVehicleIsStationary(){
        assertTrue(vehicle.isStationary());
    }

    @Test
    public void testAccelerateWithANegativeAmountOfBreak() {
        vehicle.startEngine();
        vehicle.gas(0.5);
        double speedAfterGas = vehicle.getCurrentSpeed();
        vehicle.brake(-0.9);
        assertTrue(vehicle.getCurrentSpeed() <= speedAfterGas);
    }

    @Test
    public void testBreakWithANegativeAmountOfGas() {
        vehicle.startEngine();
        vehicle.gas(0.5);
        double speedAfterFirstGas = vehicle.getCurrentSpeed();
        vehicle.gas(-0.4);
        assertTrue(vehicle.getCurrentSpeed() >= speedAfterFirstGas);
    }

    @Test
    public void testCurrentSpeedNeverExceedsEnginePower(){
        vehicle.startEngine();
        vehicle.gas(100000.0);
        assertTrue(vehicle.getCurrentSpeed() <= vehicle.getEnginePower());
    }

    @Test
    public void testCurrentSpeedNeverGoesBelowEnginePower(){
        vehicle.startEngine();
        vehicle.brake(100000.0);
        assertTrue(vehicle.getCurrentSpeed() >= 0);
    }

}
