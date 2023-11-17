package src;

import src.util.Direction;
import src.util.Vector;
import src.util.WeightClass;

/**
 * Represents a Ramp that can have cars on it.
 */
public class Ramp implements Hinged, Rampable {

    private boolean isRaised;

    // private final Stack<Car> cars;

    private final ObjectStorage<Car> cars;


    /**
     * Constructs a ramp.
     * @param maxCars Maximum amount of cars that this ramp can hold.
     */
    public Ramp(int maxCars){
        cars = new ObjectStorage<Car>(maxCars);
    }

    /**
     * Adds a car to the ramp.
     * The ramp must be down and the car to add cannot be humongous or have a ramp.
     *
     * @param car Car to add
     */
    public void addCar(Car car){
        if(attacheableIsUp())
            return;

        // Can't add HUMONGOUS cars or ramped cars
        if(car.getWeightClass() == WeightClass.HUMONGOUS || car instanceof Rampable)
            return;

        cars.addObject(car);
    }

    /**
     * Removes a car from the ramp.
     * @param parentDirection Direction of the truck with the ramp on it.
     */
    public void removeCar(Direction parentDirection){
        if(attacheableIsUp())
            return;

        Car car = cars.removeLastObject();
        car.setPosition(car.getPosition().add(
                parentDirection.getVector().multiply(new Vector(-2,-2))));
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

    @Override
    public void raiseRamp(){
        isRaised = true;
    }

    @Override
    public void lowerRamp(){
        isRaised = false;
    }

    @Override
    public int getCarCount(){
        return cars.getSize();
    }

    @Override
    public boolean attacheableIsUp() {
        return isRaised;
    }
}