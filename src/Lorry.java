package src;

import java.awt.*;

public class Lorry extends Truck {
    private Ramp ramp;

    public Lorry(Ramp ramp) {
        super(2, Color.YELLOW, 200, "någon långtradarmodell");
        this.ramp = ramp;
    }

    @Override
    public double speedFactor() {
        return enginePower * 0.01;
    }
}
