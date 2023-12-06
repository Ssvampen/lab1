package src;

import src.hingeable.Platformable;
import src.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Stream;

public class VehicleGroup {

    private final List<Vehicle> vehicles;
    private final VehicleMover vehicleMover;

    public VehicleGroup(int width, int height){
        this.vehicles = new ArrayList<>();
        this.vehicleMover = new VehicleMover(vehicles,width,height);
    }

    public void startAnimation(){
        this.vehicleMover.start();
    }

    public void addVehicle(Vehicle vehicle){
        this.vehicles.add(vehicle);
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
        return vehicles.get(new Random().nextInt(vehicles.size()));
    }

    public void turnRight(){
        getRandomVehicle().turnRight();
    }

    public void turnLeft(){
        getRandomVehicle().turnLeft();
    }

}
