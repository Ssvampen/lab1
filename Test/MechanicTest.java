package Test;

import org.junit.Before;
import org.junit.Test;
import src.Mechanic;
import src.vehicle.Volvo240;

import static org.junit.Assert.assertEquals;

public class MechanicTest {

    private Mechanic<Volvo240> mechanic;

    @Before
    public void RampInitializer(){
        this.mechanic = new Mechanic<>(10);
    }

    @Test
    public void testAdmitVehicleIncreasesCount(){
        mechanic.admitVehicle(new Volvo240());
        assertEquals(mechanic.getVehicleCount(), 1);
    }


    @Test
    public void testReturnVehicleDecreasesCount(){
        Volvo240 vehicle = new Volvo240();
        mechanic.admitVehicle(vehicle);
        mechanic.returnVehicle(vehicle);
        assertEquals(mechanic.getVehicleCount(), 0);
    }

    @Test
    public void testReturnInvalidVehicleNotIncreaseCount(){
        Volvo240 vehicle = new Volvo240();
        mechanic.admitVehicle(vehicle);
        mechanic.returnVehicle(new Volvo240());
        assertEquals(mechanic.getVehicleCount(), 1);
    }


}
