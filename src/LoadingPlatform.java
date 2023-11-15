package src;

public class LoadingPlatform implements Hinged {

    private double angle = 0;

    public void raise(double amount){
        angle = Math.max(angle + Math.max(amount, 0), 0);
    }

    public void lower(double amount){
        angle = Math.min(angle - Math.max(amount, 0), 70);
    }

    @Override
    public boolean attacheableIsUp(){
        return angle > 0;
    }

    public double getAngle() {
        return angle;
    }

}