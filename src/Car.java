package src;

import java.awt.*;

public abstract class Car implements Movable {

    private final int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    public String modelName; // The car model name

    private Direction direction;
    private Position position;

    public Car(int nrDoors, Color color, int enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.position = Position.zero();
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

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    // TODO fix this method according to lab pm
    public void gas(double amount){
        incrementSpeed(amount);
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        decrementSpeed(amount);
    }


    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    public abstract double speedFactor();


    public void move() {
        this.position = this.position.add(direction.getVector().multiply(new Position(currentSpeed, currentSpeed)));
    }



    public void turnRight() {
        int index = (this.direction.getIndex() + 1) % 4;
        this.direction = Direction.fromIndex(index);
    }

    public void turnLeft() {
        int index = (this.direction.getIndex() - 1) % 4;
        this.direction = Direction.fromIndex(index);
    }

    public Direction getDirection() {
        return direction;
    }

    public Position getPosition() {
        return position;
    }

}
