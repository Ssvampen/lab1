package Test;

import org.junit.Before;
import src.Mechanic;
import src.Ramp;
import src.Volvo240;

public class MechanicTest {

    private Mechanic<Volvo240> mechanic;
    
    @Before
    public void RampInitializer(){
        this.mechanic = new Mechanic<>(10);
    }

}
