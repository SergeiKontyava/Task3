import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExceptionUnitTest {
    private Mathematics TestClass;

    @BeforeTest
    public void initialize(){
        TestClass = new Mathematics();
        TestClass.setResult(0);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void divisionByZeroException(){
        TestClass.divide(50, 0);
    }
}
