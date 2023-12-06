package src;

import src.hingeable.Platformable;
import src.util.Vector;
import src.vehicle.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class VehicleController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    VehicleView frame;
    // A list of vehicles, modify if needed
    private final java.util.List<Vehicle> vehicles = new ArrayList<>();
    // A map of vehicles mapped to their renderObjects
    private final Map<Vehicle, RenderObject> renderObjects = new HashMap<>();

    private String[] supportedModels;


    public VehicleController(String[] supportedModels){
        this.supportedModels = supportedModels;
    }

    public void loadImages(){
        // Load images here
        for(String model : supportedModels) {
            frame.loadImage(model); // TODO: law of demeter!!!!!!!!!!!!!!
        }
    }

    //methods:
    public void addVehicle(Vehicle vehicle){
        this.vehicles.add(vehicle);
        RenderObject object = this.frame.addRenderObject(vehicle.getModelName(),
                new Point((int) Math.round(vehicle.getPosition().getX()),
                        (int) Math.round(vehicle.getPosition().getY())));

        this.renderObjects.put(vehicle, object);
    }

    /* Each step the TimerListener moves all the vehicles in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicles) {

                // check vehicle not outside bounds
                RenderObject renderObject = renderObjects.get(vehicle);
                if(renderObject == null)
                    continue;

                bounceVehicleIfOutOfBounds(vehicle);

                // Update render object position
                Point point = new Point((int) Math.round(vehicle.getPosition().getX()),
                        (int) Math.round(vehicle.getPosition().getY()));
                renderObject.setPosition(point);

                vehicle.move();
            }
            frame.repaint();
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
        boolean outsideX = x < 0 || x > frame.getWidth() - errorFixWidth;
        boolean outsideY =  y < 0 || y > frame.getHeight() - errorFixHeight;
        // Make vehicle turn if it is outside bounds
        // The true panel size is not used, hence the errorFix integer. TODO: Search for better solution
        if(outsideX || outsideY) {
            vehicle.stopEngine();
            vehicle.turnRight();
            vehicle.turnRight();
            vehicle.startEngine();
            x = Math.min(Math.max(0,x),frame.getWidth()-errorFixWidth);
            y = Math.min(Math.max(0,y),frame.getHeight()-errorFixHeight);
        }

        vehicle.setPosition(new Vector(x, y));
    }


    public void startTimer() {
        timer.start();
    }

    // Calls the gas method for each vehicle once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        vehicles.forEach(v -> v.gas(gas));
    }

    void brake(int amount){
        double brake = ((double) amount) / 100;
        vehicles.forEach(v -> v.brake(brake));
    }

    void liftBed(int amount){
        getPlatformableVehicles().forEach(p -> p.decreasePlatformAngle(amount));
    }

    void lowerBed(int amount){
        getPlatformableVehicles().forEach(p -> p.increasePlatformAngle(amount));
    }

    private Stream<Platformable> getPlatformableVehicles() {
        return vehicles.stream()
                .filter(v -> v instanceof Platformable)
                .map(v -> (Platformable) v);
    }

    private Stream<Turboable> getTurboableVehicles() {
        return vehicles.stream()
                .filter(v -> v instanceof Turboable)
                .map(v -> (Turboable) v);
    }

    void turboOn(){
        getTurboableVehicles().forEach(Turboable::setTurboOn);
    }

    void turboOff(){
        getTurboableVehicles().forEach(Turboable::setTurboOff);
    }

    void stop(){
        vehicles.forEach(Vehicle::stopEngine);
    }

    void start(){
        vehicles.forEach(Vehicle::startEngine);
    }

    private Vehicle getRandomVehicle(){
        return vehicles.get(new Random().nextInt(vehicles.size()));
    }

    void turnRight(){
        getRandomVehicle().turnRight();
    }

    void turnLeft(){
        getRandomVehicle().turnLeft();
    }

}
