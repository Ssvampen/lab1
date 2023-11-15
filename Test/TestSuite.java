package Test;

import Test.CarTest;
import Test.SaabTest;
import Test.VolvoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({CarTest.class, VolvoTest.class, SaabTest.class})

public class TestSuite {

}
