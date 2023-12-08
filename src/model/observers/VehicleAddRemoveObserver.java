package src.model.observers;

import src.model.vehicle.Vehicle;

/**
 * An observer that listens to when vehicles are added or removed.
 */
public interface VehicleAddRemoveObserver {

    /**
     * Called when a new vehicle was added.
     * @param vehicle Vehicle that was added.
     */
    void onVehicleAdded(Vehicle vehicle);

    /**
     * Called when a vehicle was removed.
     * @param vehicle Vehicle that was removed.
     */
    void onVehicleRemoved(Vehicle vehicle);

}
