package src;

/**
 * Represents an entity that can move and turn in both directions.
 */
public interface Movable {

    /**
     * Moves the entity forward.
     */
    void move();

    /**
     * Turns the entity right.
     */
    void turnRight();

    /**
     * Turns the entity left.
     */
    void turnLeft();

}