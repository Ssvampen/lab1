package src;

import java.awt.*;

// TODO: Check if Scania can break to currentSpeed 0, lower "flak" and that accelerate to speed
// TODO: Make code Niklas-approved
public class Scania extends Truck{

    private LoadingPlatform loadingPlatform;

    public Scania(){
        super(2, Color.BLUE, 200, "Scania 1337");
    }

    public void increaseLoadingPlatformAngle(float amount) {
        if(vehicleIsStationary()) {
            loadingPlatform.raise(amount);
        }
    }

    public void decreaseLoadingPlatformAngle(float amount) {
        // The vehicle should not be able to move when "loading platform" is up. Therefor it is not necessary to check whether the vehicle is moving or not.
        loadingPlatform.lower(amount);
    }

    @Override
    public double speedFactor() {
        return enginePower * 0.01;
    }

    @Override
    public void startEngine() {
        if(loadingPlatform.isUp())
            return;

        super.startEngine();
    }
}