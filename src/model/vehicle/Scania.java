package src.model.vehicle;

import src.model.hingeable.LoadingPlatform;
import src.model.hingeable.Platformable;

import java.awt.*;

/**
 * Represents a Scania model 1337 with a loading platform.
 */
public class Scania extends Truck implements Platformable {

    private final LoadingPlatform loadingPlatform;

    /**
     * Loading platform that this Scania will have.
     * @param loadingPlatform Loading platform to put on this Scania.
     */
    public Scania(LoadingPlatform loadingPlatform){
        super(2, Color.BLUE, 50, "Scania_1337", loadingPlatform);
        this.loadingPlatform = loadingPlatform;
    }

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01;
    }


    @Override
    public void increasePlatformAngle(double amount) {
        if(!isStationary())
            return;

        loadingPlatform.increasePlatformAngle(amount);
    }

    @Override
    public void decreasePlatformAngle(double amount) {
        // The vehicle should not be able to move when "loading platform" is up. Therefor it is not necessary to check whether the vehicle is moving or not
        if(!isStationary())
            return;

        loadingPlatform.decreasePlatformAngle(amount);
    }
}