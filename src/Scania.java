package src;

import java.awt.*;

// TODO: Check if Scania can break to currentSpeed 0, lower "flak" and that accelerate to speed
// TODO: Make code Niklas-approved
public class Scania extends Truck {

    private final LoadingPlatform loadingPlatform;

    public Scania(LoadingPlatform loadingPlatform){
        super(2, Color.BLUE, 200, "Scania 1337", loadingPlatform);
        this.loadingPlatform = loadingPlatform;
    }

    public void increaseLoadingPlatformAngle(float amount) {
        loadingPlatform.raise(amount);
    }

    public void decreaseLoadingPlatformAngle(float amount) {
        // The vehicle should not be able to move when "loading platform" is up. Therefor it is not necessary to check whether the vehicle is moving or not
        if(vehicleIsStationary()){
            loadingPlatform.lower(amount);
        }
    }

    @Override
    public double speedFactor() {
        return enginePower * 0.01;
    }


}