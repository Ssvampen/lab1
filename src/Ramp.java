package src;

import java.util.Stack;

import src.util.Direction;
import src.util.Vector;
import src.util.WeightClass;

public class Ramp implements Hinged {

    private boolean isRaised;

    // private final Stack<Car> cars;

    private final ObjectStorage<Car> cars;


    public Ramp(int maxCars){
        cars = new ObjectStorage<Car>(maxCars);
    }

    public void addCar(Car car){
        if(attacheableIsUp())
            return;

        // Can't add HUMONGOUS cars or ramped cars
        if(car.getWeightClass() == WeightClass.HUMONGOUS || car instanceof Rampable)
            return;

        cars.addObject(car);
    }

    /**
     * TODO: FIX THE NULL RETURN STATEMENT. SHOULDN'T BE THERE!!
     * @param parentDirection
     * @return
     */
    public Car removeCar(Direction parentDirection){
        if(!attacheableIsUp()) {
            Car car = cars.removeLastObject();
            car.setPosition(car.getPosition().add(
                    parentDirection.getVector().multiply(new Vector(-2,-2))));

            return car;
        }
        return null;
    }

    /**
     * Moves all cars (independent of every individual cars enginepower...) to the same position of the parent car
     * @param position Position of parent car
     */
    public void teleportCars(Vector position) {
        for(Car car : cars){
            car.setPosition(position);
        }
    }

    public void raise(){
        isRaised = true;
    }

    public void lower(){
        isRaised = false;
    }

    public int getCarCount(){
        return cars.getSize();
    }

    @Override
    public boolean attacheableIsUp() {
        return isRaised;
    }
}