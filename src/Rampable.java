package src;

/**
 * Represents an entity which has a car ramp on it.
 */
public interface Rampable {
    /**
     * Raises the ramp.
     */
    void raiseRamp();

    /**
     * Lowers the ramp.
     */
    void lowerRamp();

    /**
     * Gets the amount of cars on this ramp.
     * @return Amount of cars
     */

    int getCarCount();
}
