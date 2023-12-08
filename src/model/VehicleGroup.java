package src.model;

import src.model.hingeable.Platformable;
import src.model.observers.VehicleAddRemoveObserver;
import src.model.observers.VehicleMovementObserver;
import src.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class VehicleGroup {

    private final List<Vehicle> vehicles;
    private final VehicleMover vehicleMover;

    private final List<VehicleAddRemoveObserver> observers;

    public VehicleGroup(int width, int height){
        this.vehicles = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.vehicleMover = new VehicleMover(vehicles,width,height);
    }

    public void startAnimation(){
        this.vehicleMover.start();
    }

    public void addVehicle(Vehicle vehicle){
        if(this.vehicles.size() >= 10)
            return;

        this.vehicles.add(vehicle);
        this.notifyAddListeners(vehicle);
    }

    public void removeVehicle(){
        Vehicle vehicle = getRandomVehicle();
        if(vehicle == null)
            return;
        this.vehicles.remove(vehicle);
        notifyRemoveListeners(vehicle);
    }

    private void notifyAddListeners(Vehicle vehicle){
        for(VehicleAddRemoveObserver observer : observers)
            observer.onVehicleAdded(vehicle);
    }

    private void notifyRemoveListeners(Vehicle vehicle){
        for(VehicleAddRemoveObserver observer : observers)
            observer.onVehicleRemoved(vehicle);
    }

    public void addVehicleAddRemoveListener(VehicleAddRemoveObserver observer){
        this.observers.add(observer);
    }

    public void addVehicleMoveListener(VehicleMovementObserver observer){
        this.vehicleMover.addVehicleMoveListener(observer);
    }

    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        vehicles.forEach(v -> v.gas(gas));
    }

    public void brake(int amount){
        double brake = ((double) amount) / 100;
        vehicles.forEach(v -> v.brake(brake));
    }

    public void liftBed(int amount){
        getPlatformableVehicles().forEach(p -> p.decreasePlatformAngle(amount));
    }

    public void lowerBed(int amount){
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

    public void turboOn(){
        getTurboableVehicles().forEach(Turboable::setTurboOn);
    }

    public void turboOff(){
        getTurboableVehicles().forEach(Turboable::setTurboOff);
    }

    public void stop(){
        vehicles.forEach(Vehicle::stopEngine);
    }

    public void start(){
        vehicles.forEach(Vehicle::startEngine);
    }

    private Vehicle getRandomVehicle(){
        if(this.vehicles.isEmpty())
            return null;

        if(this.vehicles.size() == 1)
            return this.vehicles.get(0);

        return this.vehicles.get(ThreadLocalRandom.current().nextInt(this.vehicles.size() - 1));
    }

    public void turnRight(){
        getRandomVehicle().turnRight();
    }

    public void turnLeft(){
        getRandomVehicle().turnLeft();
    }

}
