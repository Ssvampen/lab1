package src;

import src.util.Direction;
import src.util.Vector;

import java.awt.*;

/**
 * Car is a class with the basic car attributes and methods that apply to most modern cars.
 * Car has attributes within the likes of number of doors, engine power, model name, and so on.
 * The Car class also implements the basic car features (methods) such as start car, stop car, accelerate and decelerate.
 * The interface "Moveble" is also implemented which requires the implementation of various methods to move the car.
 */
public abstract class Car implements Movable {

    private final int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private final String modelName; // The car model name

    private Direction direction; // The current direction of the
    private Vector position;

    public Car(int nrDoors, Color color, int enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.position = Vector.zero();
        this.direction = Direction.NORTH;

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
     * Sets the cars current speed to 0.1.
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * Sets the cars current speed to 0.
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
     * Decreases the cars speed using the formula in the decrementSpeed() method.
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
     * Increments the speed of the car with the specified amount.
     * This can never exceed the engine power of the car.
     * @param amount Amount to increment.
     */
    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    /**
     * Decrements the speed of the car with the specified amount.
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
    protected boolean vehicleIsStationary() {
        if (currentSpeed == 0) {
            return true;
        }
        return false;
    }

    /**
     * Speed factor of this car, how much it should accelerate.
     */
    public abstract double speedFactor();

    /**
     * Moves the car in the current direction with the current speed.
     */
    public void move() {
        this.position = this.position.add(direction.getVector().multiply(new Vector(currentSpeed, currentSpeed)));
    }

    /**
     * Turns the car 90 degrees right.
     */
    public void turnRight() {
        int index = (this.direction.getIndex() - 1);
        if(index < 0){index = 3;}
        this.direction = Direction.fromIndex(index);
    }

    /**
     * Turns the car 90 degrees left.
     */
    public void turnLeft() {
        int index = (this.direction.getIndex() + 1);
        if(index > 3){index = 0;}
        this.direction = Direction.fromIndex(index);
    }

    public Direction getDirection() {
        return direction;
    }

    public Vector getPosition() {
        return position;
    }

    public String getModelName() {
        return modelName;
    }
}
