package src.model;

import src.model.util.Vector;
import src.model.util.WeightClass;

/**
 * Represents a generic entity in the world.
 */
public interface Entity {
    Vector getPosition();
    void setPosition(Vector position);
    Vector getDirection();
    void setDirection(Vector direction);
    WeightClass getWeightClass();
}
