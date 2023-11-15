package src;

public class LoadingPlatform implements Hinged {

    private double angle = 0;

    public void raise(float amount){
        angle = Math.max(angle + Math.max(amount,0), 70);
    }

    public void lower(float amount){
        angle = Math.min(angle - Math.max(amount,0), 0);
    }

    @Override
    public boolean attacheableIsUp(){
        return angle > 0;
    }

    public double getAngle() {
        return angle;
    }

}