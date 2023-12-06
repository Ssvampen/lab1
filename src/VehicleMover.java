package src;

import src.util.Vector;
import src.vehicle.Vehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VehicleMover implements ActionListener {

    private final List<Vehicle> vehicles;
    private final List<VehicleMovementObserver> observers;

    private final Timer timer;

    private final int width;
    private final int height;

    public VehicleMover(List<Vehicle> vehicles, int width, int height){
        this.vehicles = vehicles;
        this.observers = new ArrayList<>();
        this.timer = new Timer(50, this);
        this.width = width;
        this.height = height;
    }

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
        int errorFixHeight = 55;
        int errorFixWidth = 100;
        boolean outsideX = x < 0 || x > width - errorFixWidth;
        boolean outsideY =  y < 0 || y > height - errorFixHeight;
        // Make vehicle turn if it is outside bounds
        // The true panel size is not used, hence the errorFix integer. TODO: Search for better solution
        if(outsideX || outsideY) {
            vehicle.stopEngine();
            vehicle.turnRight();
            vehicle.turnRight();
            vehicle.startEngine();
            x = Math.min(Math.max(0,x),width-errorFixWidth);
            y = Math.min(Math.max(0,y),height-errorFixHeight);
        }

        vehicle.setPosition(new Vector(x, y));
    }

    public void addVehicleMoveListener(VehicleMovementObserver listener){
        this.observers.add(listener);
    }

    private void notifyObservers(Vehicle vehicle, Vector position){
        for(VehicleMovementObserver observer : observers)
            observer.onVehicleMoved(vehicle, position);
    }

}