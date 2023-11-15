package Test;

import org.junit.Before;
import src.Lorry;
import src.Ramp;
import src.Volvo240;
import src.util.Vector;
import src.Car;

public class LorryTest {
    private Lorry lorry;

    @Before
    public void initializeLorry() {
        lorry = new Lorry(new Ramp(5));
    }

    public void testAddCarNearLorryToLorrysRamp() {
        Volvo240 car = new Volvo240();
        car.setPosition(new Vector(1,1));
        lorry.setPosition(new Vector(1,1));
        lorry.addCar(car);
        Car removedCar = lorry.removeCar();
    }
}
