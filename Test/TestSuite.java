package Test;

import Test.CarTest;
import Test.SaabTest;
import Test.ScaniaTest;
import Test.VolvoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import src.ObjectStorage;
import src.Scania;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CarTest.class,
        LoadingPlatformTest.class,
        LorryTest.class,
        MechanicTest.class,
        ObjectStorageTest.class,
        RampTest.class,
        SaabTest.class,
        VolvoTest.class,
        ScaniaTest.class
})

public class TestSuite {

}
