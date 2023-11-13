package src;

import java.util.Stack;

public class Ramp {

    private boolean isRaised;

    private Stack<Car> cars;

    //TODO add limit to number of cars
    public void addCar(Car car){
        cars.push(car);
    }

    public void removeCar(){
        cars.pop();
    }

    public void raise(){
        isRaised = true;
    }

    public void lower(){
        isRaised = false;
    }

    public boolean isUp() {
        return isRaised;
    }
}