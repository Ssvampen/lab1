package src.model.observers;

import src.model.vehicle.Vehicle;

/**
 * An observer that listens to when vehicles are added or removed.
 */
public interface VehicleAddRemoveObserver {

    void onVehicleAdded(Vehicle vehicle);

    void onVehicleRemoved(Vehicle vehicle);

}
