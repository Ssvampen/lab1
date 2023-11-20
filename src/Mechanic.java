package src;

/**
 * Represents a mechanic that can perform work on cars.
 * @param <T> Type of car that this mechanic can work on.
 */
public class Mechanic<T extends Car> {

    private final ObjectStorage<T> cars;

    /**
     * Constructs a Mechanic.
     * @param capacity Maximum capacity of cars that can be admitted to this mechanic.
     */
    public Mechanic(int capacity){
        this.cars = new ObjectStorage<>(capacity);
    }

    /**
     * Admits a car to be worked on by this mechanic.
     * @param car Car to admit.
     */
    public void admitCar(T car) {
        this.cars.addObject(car);
    }

    /**
     * Returns a car to the owner.
     * @param car Car to return.
     * @return Car that was returned
     */
    public T returnCar(T car) {
        this.cars.removeObject(car);
        return car;
    }

    /**
     * Gets the amount of cars that this mechanic currently is working on.
     * @return Amount of cars.
     */
    public int getCarCount(){
        return this.cars.getSize();
    }

}
