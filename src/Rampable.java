package src;

/**
 * Represents an entity which has a car ramp on it.
 */
public interface Rampable {
    /**
     * Raises the ramp (the truck can drive when ramp is raised).
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
