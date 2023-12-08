package src.model.observers;

import src.model.util.Vector;
import src.model.vehicle.Vehicle;

/**
 * An observer that listens for vehicle movement.
 */
public interface VehicleMovementObserver {
    /**
     * Called when a vehicle was moved.
     * @param vehicle Vehicle that moved.
     * @param position New position of the vehicle.
     */
    void onVehicleMoved(Vehicle vehicle, Vector position);
}
