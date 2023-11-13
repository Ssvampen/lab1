package src;

public class LoadingPlatform {
    private float angle;

    public void raise(float amount){
        angle = Math.max(angle + Math.max(amount,0), 70);
    }

    public void lower(float amount){
        angle = Math.min(angle - Math.max(amount,0), 0);
    }

    public boolean isUp(){
        return angle > 0;
    }
}
