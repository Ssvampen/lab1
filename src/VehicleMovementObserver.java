package src;

import src.util.Vector;
import src.vehicle.Vehicle;

public interface VehicleMovementObserver {
    void onVehicleMoved(Vehicle vehicle, Vector position);
}
