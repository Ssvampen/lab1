package src;

public class Mechanic<T extends Car> {

    private final ObjectStorage<T> cars;

    public Mechanic(int capacity){
        this.cars = new ObjectStorage<>(capacity);
    }

    public void admitCar(T car) {
        cars.addObject(car);
    }

    public T returnCar(T car) {
        cars.removeObject(car);
        return car;
    }

}
