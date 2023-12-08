package src.model;

import src.model.hingeable.Platformable;
import src.model.observers.VehicleAddRemoveObserver;
import src.model.observers.VehicleMovementObserver;
import src.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * Represents the core of our model, a group of vehicles and the movement logic.
 */
public class VehicleGroup {

    private final List<Vehicle> vehicles;
    private final VehicleMover vehicleMover;

    private final List<VehicleAddRemoveObserver> observers;

    /**
     * Creates a new vehicle group.
     *
     * @param width Width of the world
     * @param height Height of the world.
     */
    public VehicleGroup(int width, int height){
        this.vehicles = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.vehicleMover = new VehicleMover(vehicles, width, height);
    }

    /**
     * Starts the animator
     */
    public void startAnimation(){
        this.vehicleMover.start();
    }

    /**
     * Attempts to add a new vehicle to this group.
     * Will not work if there are 10 vehicles in the group.
     * @param vehicle Vehicle to add
     */
    public void addVehicle(Vehicle vehicle){
        if(this.vehicles.size() >= 10)
            return;

        this.vehicles.add(vehicle);
        this.notifyAddListeners(vehicle);
    }

    /**
     * Removes a random vehicle from the group.
     */
    public void removeVehicle(){
        Vehicle vehicle = getRandomVehicle();
        if(vehicle == null)
            return;

        this.vehicles.remove(vehicle);
        notifyRemoveListeners(vehicle);
    }

    /**
     * Notifies the add observers that a vehicle has been added.
     * @param vehicle Vehicle that was added.
     */
    private void notifyAddListeners(Vehicle vehicle){
        for(VehicleAddRemoveObserver observer : observers)
            observer.onVehicleAdded(vehicle);
    }

    /**
     * Notifies the remove observers that a vehicle has been removed.
     * @param vehicle Vehicle that was removed.
     */
    private void notifyRemoveListeners(Vehicle vehicle){
        for(VehicleAddRemoveObserver observer : observers)
            observer.onVehicleRemoved(vehicle);
    }

    /**
     * Adds a new observer for vehicle addition/removal.
     * @param observer Observer.
     */
    public void addVehicleAddRemoveListener(VehicleAddRemoveObserver observer){
        this.observers.add(observer);
    }

    /**
     * Adds a new observer for vehicle movement.
     * @param observer Observer.
     */
    public void addVehicleMoveListener(VehicleMovementObserver observer){
        this.vehicleMover.addVehicleMoveListener(observer);
    }

    /**
     * Gasses all vehicle in this group with the specified amount.
     * @param amount Amount to gas.
     */
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        vehicles.forEach(v -> v.gas(gas));
    }

    /**
     * Brakes all vehicles in this group with the specified amount.
     * @param amount Amount to brake.
     */
    public void brake(int amount){
        double brake = ((double) amount) / 100;
        vehicles.forEach(v -> v.brake(brake));
    }

    /**
     * Lifts the bed of all platformable vehicles in this group, with the specified amount.
     * @param amount Angle to lift bed.
     */
    public void liftBed(int amount){
        System.out.println("lift " + amount);
        getPlatformableVehicles().forEach(p -> p.decreasePlatformAngle(amount));
    }

    /**
     * Lowers the bed of all platformable vehicles in this group, with the specified amount.
     * @param amount Angle to lower bed.
     */
    public void lowerBed(int amount){
        getPlatformableVehicles().forEach(p -> p.increasePlatformAngle(amount));
    }

    /**
     * Gets all platformable vehicles in this group.
     * @return Stream of platformable vehicles.
     */
    private Stream<Platformable> getPlatformableVehicles() {
        return vehicles.stream()
                .filter(v -> v instanceof Platformable)
                .map(v -> (Platformable) v);
    }

    /**
     * Gets all turboable vehicles in this group.
     * @return Stream of turboable vehicles.
     */
    private Stream<Turboable> getTurboableVehicles() {
        return vehicles.stream()
                .filter(v -> v instanceof Turboable)
                .map(v -> (Turboable) v);
    }

    /**
     * Sets turbo on for all turboable vehicles in this group.
     */
    public void turboOn(){
        getTurboableVehicles().forEach(Turboable::setTurboOn);
    }

    /**
     * Sets turbo off for all turboable vehicles in this group.
     */
    public void turboOff(){
        getTurboableVehicles().forEach(Turboable::setTurboOff);
    }

    /**
     * Stops all vehicles in this group.
     */
    public void stop(){
        vehicles.forEach(Vehicle::stopEngine);
    }

    /**
     * Starts all vehicles in this group.
     */
    public void start(){
        vehicles.forEach(Vehicle::startEngine);
    }

    /**
     * Gets a random vehicle in this group.
     * @return Random vehicle or null if there are no vehicles left.
     */
    private Vehicle getRandomVehicle(){
        if(this.vehicles.isEmpty())
            return null;

        if(this.vehicles.size() == 1)
            return this.vehicles.get(0);

        return this.vehicles.get(ThreadLocalRandom.current().nextInt(this.vehicles.size() - 1));
    }

    /**
     * Turns a random vehicle right.
     */
    public void turnRight(){
        Vehicle vehicle = getRandomVehicle();
        if(vehicle == null)
            return;

        vehicle.turnRight();
    }

    /**
     * Turns a random vehicle left.
     */
    public void turnLeft(){
        Vehicle vehicle = getRandomVehicle();
        if(vehicle == null)
            return;

        vehicle.turnLeft();
    }

}
