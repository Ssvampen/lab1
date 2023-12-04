package src;

import src.util.Vector;
import src.util.WeightClass;

public interface Entity {
    Vector getPosition();
    void setPosition(Vector position);
    Vector getDirection();
    void setDirection(Vector direction);
    WeightClass getWeightClass();
}
