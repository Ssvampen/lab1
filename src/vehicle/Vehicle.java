package src.vehicle;

import src.Movable;
import src.util.Vector;
import src.util.WeightClass;

import java.awt.*;

/**
 * Car is a class with the basic vehicle attributes and methods that apply to most modern vehicles.
 * Car has attributes within the likes of number of doors, engine power, model name, and so on.
 * The Car class also implements the basic vehicle features (methods) such as start vehicle, stop vehicle, accelerate and decelerate.
 * The interface "Movable" is also implemented which requires the implementation of various methods to move the vehicle.
 */
public abstract class Vehicle implements Movable {

    private final int nrDoors; // Number of doors on the vehicle
    protected double enginePower; // Engine power of the vehicle
    protected double currentSpeed; // The current speed of the vehicle
    private Color color; // Color of the vehicle
    private final String modelName; // The vehicle model name
    protected WeightClass weightClass; // The weight class of the vehicle. Is defined by an enum

    private Vector direction; // The current direction of the vehicle
    private Vector position; // The current position of the vehicle

    /**
     * Constructor
     * @param nrDoors Number of doors.
     * @param color Color of the vehicle.
     * @param enginePower The engine power.
     * @param modelName The vehicles model name.
     * @param weightClass The vehicles weight class
     */
    public Vehicle(int nrDoors, Color color, int enginePower, String modelName, WeightClass weightClass) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.position = Vector.zero();
        this.direction = Vector.NORTH;
        this.weightClass = weightClass;

        stopEngine();
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    /**
     * Sets the vehicles current speed to 0.1.
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * Sets the vehicles current speed to 0.
     */
    public void stopEngine() {
        currentSpeed = 0;
    }

    /**
     * @param amount Inputted acceleration.
     * @return True if the speed is not within the range [0,1].
     */
    private static boolean isSpeedOutsideMovementRange(double amount){
        return amount < 0.0 || amount > 1.0;
    }

    /**
     * Increments the speed using the formula used in the incrementSpeed() method.
     * Should be within range [0, 1].
     * @param amount Inputted acceleration.
     */
    public void gas(double amount){
        if(isSpeedOutsideMovementRange(amount)) {
            return;
        }

        incrementSpeed(amount);
    }

    /**
     * Decreases the vehicles speed using the formula in the decrementSpeed() method.
     * Should be within range [0, 1].
     * @param amount Inputted breakage.
     */
    public void brake(double amount){
        if(isSpeedOutsideMovementRange(amount)) {
            return;
        }

        decrementSpeed(amount);
    }

    /**
     * Increments the speed of the vehicle with the specified amount.
     * This can never exceed the engine power of the vehicle.
     * @param amount Amount to increment.
     */
    private void incrementSpeed(double amount){
        if(currentSpeed == 0)
            return;

        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    /**
     * Decrements the speed of the vehicle with the specified amount.
     * This can never go below 0.
     * @param amount Amount to decrement.
     */
    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    /**
     * Test if speed is 0
     * @return true if vehicle is stationary, else false
     */
    public boolean vehicleIsStationary() {
        return currentSpeed == 0;
    }

    /**
     * Speed factor of this vehicle, how much it should accelerate.
     */
    public abstract double speedFactor();

    /**
     * Moves the vehicle in the current direction with the current speed.
     */
    public void move() {
        setPosition(this.position.add(direction.multiply(new Vector(currentSpeed, currentSpeed))));
    }

    /**
     * Turns the vehicle 90 degrees right.
     */
    public void turnRight() {
        setDirection(this.direction.rotate(Math.PI / 2));
    }

    /**
     * Turns the vehicle 90 degrees left.
     */
    public void turnLeft() {
        setDirection(this.direction.rotate(-Math.PI / 2));
    }

    public Vector getDirection() {
        return direction;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    @Override
    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public String getModelName() {
        return modelName;
    }

    public WeightClass getWeightClass() {
        return weightClass;
    }

}
