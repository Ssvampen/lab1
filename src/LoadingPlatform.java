package src;

public class LoadingPlatform implements Hinged {

    private double angle = 0;

    private double restrictAngleAmount(double amount){
        return Math.max(amount, 0);
    }

    public void raise(double amount){
        angle = Math.min(angle + restrictAngleAmount(amount), 70);
    }

    public void lower(double amount){
        angle = Math.max(angle - restrictAngleAmount(amount), 0);
    }

    @Override
    public boolean attacheableIsUp(){
        return angle > 0;
    }

    public double getAngle() {
        return angle;
    }

}