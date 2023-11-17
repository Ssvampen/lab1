package src;

import java.awt.*;

/**
 * Represents a Scania model 1337 with a loading platform.
 */
public class Scania extends Truck {

    private final LoadingPlatform loadingPlatform;

    /**
     * Loading platform that this Scania will have.
     * @param loadingPlatform Loading platform to put on this Scania.
     */
    public Scania(LoadingPlatform loadingPlatform){
        super(2, Color.BLUE, 200, "Scania 1337", loadingPlatform);
        this.loadingPlatform = loadingPlatform;
    }

    /**
     * Increases the loading platform angle. The vehicle must be stationary.
     * @param amount The amount which the loading platform should be raised.
     */
    public void increaseLoadingPlatformAngle(float amount) {
        if(!vehicleIsStationary())
            return;

        loadingPlatform.raise(amount);
    }

    /**
     * Increases the loading platform angle. The vehicle must be stationary.
     * @param amount The amount which the loading platform should be raised.
     */
    public void decreaseLoadingPlatformAngle(float amount) {
        // The vehicle should not be able to move when "loading platform" is up. Therefor it is not necessary to check whether the vehicle is moving or not
        if(!vehicleIsStationary())
            return;

        loadingPlatform.lower(amount);
    }

    @Override
    public double speedFactor() {
        return enginePower * 0.01;
    }


}