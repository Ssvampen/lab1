package Test;

import org.junit.Before;
import org.junit.Test;
import src.Mechanic;
import src.Volvo240;

import static org.junit.Assert.assertEquals;

public class MechanicTest {

    private Mechanic<Volvo240> mechanic;
    
    @Before
    public void RampInitializer(){
        this.mechanic = new Mechanic<>(10);
    }

    @Test
    public void testAdmitCarIncreasesCount(){
       mechanic.admitCar(new Volvo240());
       assertEquals(mechanic.getCarCount(), 1);
    }


    @Test
    public void testReturnCarDecreasesCount(){
        Volvo240 car = new Volvo240();
        mechanic.admitCar(car);
        mechanic.returnCar(car);
        assertEquals(mechanic.getCarCount(), 0);
    }

    @Test
    public void testReturnInvalidCarNotIncreaseCount(){
        Volvo240 car = new Volvo240();
        mechanic.admitCar(car);
        mechanic.returnCar(new Volvo240());
        assertEquals(mechanic.getCarCount(), 1);
    }

}
