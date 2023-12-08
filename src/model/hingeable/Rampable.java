package src.model.hingeable;

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
     * Gets the amount of entities on this ramp.
     * @return Entity count
     */
    int getEntityCount();
}
