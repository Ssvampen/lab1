package src;

import src.util.Direction;
import src.util.Vector;
import src.util.WeightClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Ramp that can have cars on it.
 */
public class Ramp implements Hinged, Rampable {

    private boolean isRaised;

    // private final Stack<Car> cars;

    private final ObjectStorage<Car> cars;
    private List<Car> unloadedCars = new ArrayList<>();
    /** The list unloadedCars is used to prevent cars being placed on the
    * same spot when unloaded.
     * */


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
        if(!attacheableIsDown())
            return;

        // Can't add HUMONGOUS cars or ramped cars
        if(car.getWeightClass() == WeightClass.HUMONGOUS || car instanceof Rampable)
            return;

        cars.addObject(car);
    }

    /**
     * Removes a car from the ramp. Moves all cars in unloadedCars to prevent overlapping positions.
     * @param parentDirection Direction of the truck with the ramp on it.
     */
    public void removeCar(Direction parentDirection){
        if(!attacheableIsDown())
            return;

        Car car = cars.removeLastObject();
        unloadedCars.add(car);

        for(Car unloadedCar : unloadedCars) {
            switch (parentDirection){
                case NORTH: {
                    unloadedCar.setPosition(unloadedCar.getPosition().add(new Vector(0, -2)));
                    break;
                }
                case EAST: {
                    unloadedCar.setPosition(unloadedCar.getPosition().add(new Vector(-2, 0)));
                    break;
                }
                case WEST:{
                    unloadedCar.setPosition(unloadedCar.getPosition().add(new Vector(2, 0)));
                    break;
                }
                case SOUTH: {
                    unloadedCar.setPosition(unloadedCar.getPosition().add(new Vector(0, 2)));
                    break;
                }
            }
        }
    }

    private void deleteUnloadedCarsList(){
        unloadedCars = new ArrayList<Car>();
    }

    /**
     * Moves all cars (independent of every individual cars enginepower...) to the same position of the parent car
     * @param position Position of parent car
     */
    public void teleportCars(Vector position) {
        for(Car car: cars){
            car.setPosition(position);
        }
    }

    @Override
    public void raiseRamp(){
        isRaised = true;
        deleteUnloadedCarsList();
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
    public boolean attacheableIsDown() {
        return !isRaised;
    }
}