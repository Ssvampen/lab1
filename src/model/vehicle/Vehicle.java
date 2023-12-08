package src.model.vehicle;

import src.model.Movable;
import src.model.util.Vector;
import src.model.util.WeightClass;

import java.awt.*;

/**
 * Car is a class with the basic vehicle attributes and methods that apply to most modern vehicles.
 * Car has attributes within the likes of number of doors, engine power, model name, and so on.
 * The Car class also implements the basic vehicle features (methods) such as start vehicle, stop vehicle, accelerate and decelerate.
 * The interface "Movable" is also implemented which requires the implementation of various methods to move the vehicle.
 */
public abstract class Vehicle implements Movable {

    private final int nrDoors; // Number of doors on the vehicle
    private Color color; // Color of the vehicle
    private final String modelName; // The vehicle model name
    protected WeightClass weightClass; // The weight class of the vehicle. Is defined by an enum

    private final Engine engine;

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
        this.modelName = modelName;
        this.position = Vector.zero();
        this.direction = Vector.NORTH;
        this.weightClass = weightClass;

        this.engine = new Engine(enginePower);
        stopEngine();
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return engine.getPower();
    }

    public double getCurrentSpeed(){
        return engine.currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }


    public void startEngine(){
        engine.start();
    }

    public void stopEngine() {
        engine.stop();
    }

    public void gas(double amount){
        engine.gas(amount, speedFactor());
    }

    public void brake(double amount){
        engine.brake(amount, speedFactor());
    }

    /**
     * Speed factor of this vehicle, how much it should accelerate.
     */
    public abstract double speedFactor();

    /**
     * Moves the vehicle in the current direction with the current speed.
     */
    public void move() {
        Vector speedVector = new Vector(engine.getCurrentSpeed(), engine.getCurrentSpeed());
        Vector displacement = direction.multiply(speedVector);
        setPosition(this.position.add(displacement));
    }

    /**
     * Test if speed is 0
     * @return true if vehicle is stationary, else false
     */
    public boolean vehicleIsStationary() {
        return engine.isStationary();
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
