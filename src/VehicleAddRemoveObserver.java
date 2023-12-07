package src;

import src.vehicle.Vehicle;

public interface VehicleAddRemoveObserver {

    void onVehicleAdded(Vehicle vehicle);

    void onVehicleRemoved(Vehicle vehicle);

}
