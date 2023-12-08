package src.model;

import src.model.observers.VehicleMovementObserver;
import src.model.util.Vector;
import src.model.vehicle.Vehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to move the vehicles, makes sure they don't move outside the world bounds.
 */
public class VehicleMover implements ActionListener {

    private final List<Vehicle> vehicles;
    private final List<VehicleMovementObserver> observers;

    private final Timer timer;

    private final int width;
    private final int height;

    public static int IMAGE_WIDTH = 100;
    public static int IMAGE_HEIGHT = 60;


    /**
     * Creates a new vehicle mover.
     * @param vehicles List of vehicles to move.
     * @param width Width of the world.
     * @param height Height of the world.
     */
    public VehicleMover(List<Vehicle> vehicles, int width, int height){
        this.vehicles = vehicles;
        this.observers = new ArrayList<>();
        this.timer = new Timer(50, this);
        this.width = width;
        this.height = height;
    }

    /**
     * Starts the animation.
     */
    public void start(){
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Vehicle vehicle : vehicles) {
            bounceVehicleIfOutOfBounds(vehicle);
            vehicle.move();
            notifyObservers(vehicle, vehicle.getPosition());
        }
    }


    /**
     * If the vehicle is out of bounds, turn the vehicle around and by changing the vector.
     * @param vehicle Vehicle is the vehicle that is being tested
     */
    private void bounceVehicleIfOutOfBounds(Vehicle vehicle) {
        double x = vehicle.getPosition().getX();
        double y = vehicle.getPosition().getY();

        boolean outsideX = x < 0 || x > width - IMAGE_WIDTH;
        boolean outsideY =  y < 0 || y > height - IMAGE_HEIGHT;
        // Make vehicle turn if it is outside bounds
        if(outsideX || outsideY) {
            vehicle.stopEngine();
            vehicle.setDirection(vehicle.getDirection().invert());
            vehicle.startEngine();

            x = Math.min(Math.max(0,x), width - IMAGE_WIDTH);
            y = Math.min(Math.max(0,y), height - IMAGE_HEIGHT);
            vehicle.setPosition(new Vector(x, y));
        }

    }

    /**
     * Adds a move listener
     * @param listener Observer to call when vehicles move.
     */
    public void addVehicleMoveListener(VehicleMovementObserver listener){
        this.observers.add(listener);
    }

    /**
     * Notify observers that a vehicle moved.
     * @param vehicle Vehicle that moved.
     * @param position Position that the vehicle moved to.
     */
    private void notifyObservers(Vehicle vehicle, Vector position){
        for(VehicleMovementObserver observer : observers)
            observer.onVehicleMoved(vehicle, position);
    }

}
