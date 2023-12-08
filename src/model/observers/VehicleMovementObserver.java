package src.model.observers;

import src.model.util.Vector;
import src.model.vehicle.Vehicle;

/**
 * An observer that listens for vehicle movement.
 */
public interface VehicleMovementObserver {
    void onVehicleMoved(Vehicle vehicle, Vector position);
}
