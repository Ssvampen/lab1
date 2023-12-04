package src;

import src.vehicle.Vehicle;

/**
 * Represents a mechanic that can perform work on vehicles.
 * @param <T> Type of vehicle that this mechanic can work on.
 */
public class Mechanic<T extends Vehicle> {

    private final ObjectStorage<T> vehicles;

    /**
     * Constructs a Mechanic.
     * @param capacity Maximum capacity of vehicles that can be admitted to this mechanic.
     */
    public Mechanic(int capacity){
        this.vehicles = new ObjectStorage<>(capacity);
    }

    /**
     * Admits a vehicle to be worked on by this mechanic.
     * @param vehicle Vehicle to admit.
     */
    public void admitVehicle(T vehicle) {
        this.vehicles.addObject(vehicle);
    }

    /**
     * Returns a vehicle to the owner.
     * @param vehicle Vehicle to return.
     * @return Vehicle that was returned
     */
    public T returnVehicle(T vehicle) {
        this.vehicles.removeObject(vehicle);
        return vehicle;
    }

    /**
     * Gets the amount of vehicles that this mechanic currently is working on.
     * @return Amount of vehicles.
     */
    public int getVehicleCount(){
        return this.vehicles.getSize();
    }

}
