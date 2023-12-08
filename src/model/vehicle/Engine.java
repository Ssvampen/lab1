package src.model.vehicle;

/**
 * Represents an engine that can be added to a vehicle.
 */
public class Engine {
    private final double power;

    protected double currentSpeed; // The current speed of the vehicle

    public Engine(int power) {
        this.power = power;
    }

    /**
     * Sets the vehicles current speed to 0.1.
     */
    public void start(){
        currentSpeed = 0.1;
    }

    /**
     * Sets the vehicles current speed to 0.
     */
    public void stop() {
        currentSpeed = 0;
    }

    /**
     * @param amount Inputted acceleration.
     * @return True if the speed is not within the range [0,1].
     */
    private static boolean isSpeedOutsideMovementRange(double amount){
        return amount < 0.0 || amount > 1.0;
    }

    /**
     * Increments the speed using the formula used in the incrementSpeed() method.
     * Should be within range [0, 1].
     * @param amount Inputted acceleration.
     */
    public void gas(double amount, double speedFactor){
        if(isSpeedOutsideMovementRange(amount)) {
            return;
        }

        incrementSpeed(amount, speedFactor);
    }

    /**
     * Decreases the vehicles speed using the formula in the decrementSpeed() method.
     * Should be within range [0, 1].
     * @param amount Inputted breakage.
     */
    public void brake(double amount, double speedFactor){
        if(isSpeedOutsideMovementRange(amount)) {
            return;
        }

        decrementSpeed(amount, speedFactor);
    }

    /**
     * Increments the speed of the vehicle with the specified amount.
     * This can never exceed the engine power of the vehicle.
     * @param amount Amount to increment.
     */
    private void incrementSpeed(double amount, double speedFactor){
        if(currentSpeed == 0)
            return;

        currentSpeed = Math.min(currentSpeed + speedFactor * amount, power);
    }

    /**
     * Decrements the speed of the vehicle with the specified amount.
     * This can never go below 0.
     * @param amount Amount to decrement.
     */
    private void decrementSpeed(double amount, double speedFactor){
        currentSpeed = Math.max(currentSpeed - speedFactor * amount, 0);
    }

    /**
     * Test if speed is 0
     * @return true if vehicle is stationary, else false
     */
    public boolean isStationary() {
        return currentSpeed == 0;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getPower() {
        return power;
    }
}
