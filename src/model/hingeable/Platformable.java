package src.model.hingeable;

/**
 * Represents an entity that can have a loading platform.
 */
public interface Platformable {

    /**
     * Increases the platform angle.
     * @param amount Degrees to increase angle.
     */
    void increasePlatformAngle(double amount);

    /**
     * Decreases the platform angle.
     * @param amount Degrees to decrease angle.
     */
    void decreasePlatformAngle(double amount);

}
