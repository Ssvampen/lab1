package src.model.hingeable;

/**
 * A loading platform is a carrige that can lower and raise itself. It has a "floating" sate; it can be raised to an angle. The loading platform does not have any loading capabilities.
 */
public class LoadingPlatform implements Hingeable {

    private double angle = 0;

    /**
     * Restricts the angle to be at least 0, or higher. Negative angles is not allowed.
     * @param amount Amount is input angle which is modified.
     * @return Returns a positive double.
     */
    private double restrictAngleAmount(double amount){
        return Math.max(amount, 0);
    }

    /**
     * Increases the attribute angle.
     * @param amount The amount which the loading platform should be raised.
     */
    public void increasePlatformAngle(double amount){
        angle = Math.min(angle + restrictAngleAmount(amount), 70);
    }

    /**
     * Decreases the attribute angle.
     * @param amount The amount which the loading platform should be lowered.
     */
    public void decreasePlatformAngle(double amount){
        angle = Math.max(angle - restrictAngleAmount(amount), 0);
    }

    @Override
    public boolean attacheableIsDown(){
        return angle > 0;
    }

    /**
     * Reads the angle attribute.
     * @return The angle attribute.
     */
    public double getAngle() {
        return angle;
    }

}