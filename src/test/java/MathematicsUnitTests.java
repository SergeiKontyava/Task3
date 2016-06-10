import org.testng.Assert;
import org.testng.annotations.*;

@Test(groups = "fast")
public class MathematicsUnitTests {

    private Mathematics TestClass;

    @BeforeTest
    public void initialize(){
        TestClass = new Mathematics();
        TestClass.setResult(0);
    }

    @BeforeMethod
    public void methodInit(){
        TestClass.setResult(0);
    }

    @Test(priority = 4, groups = {"fast", "smoke"})
    public void additionShouldBeCorrect(){
        TestClass.add(1, 5);
        Assert.assertEquals(TestClass.getResult(), 6, "Addition 1 + 5 should be 6");
    }

    @Test(priority = 3)
    public void subtractionShouldBeCorrect(){
        TestClass.setResult(1);
        TestClass.deduct(5, 1);
        Assert.assertEquals(TestClass.getResult(), 4, "Subtraction 5 - 1 should be 4");
    }

    @Test(priority = 2)
    public void multiplicationShouldBeCorrect(){
        TestClass.multiply(5, 20);
        Assert.assertEquals(TestClass.getResult(), 100, "Multiplication 5 * 20 should be 100");
    }

    @Test(priority = 1)
    public void divisionShouldBeCorrect(){
        TestClass.divide(16, 4);
        Assert.assertEquals(TestClass.getResult(), 4, "Division 16 / 4 should be 4");
    }

    @AfterTest
    public void cleanup(){
        TestClass.setResult(0);
        System.out.println("Test Finished");
    }

    @AfterMethod
    public void methodCleanup(){
        System.out.println("Test Method Finished");
    }

    @AfterClass
    public void classCleanup(){
        System.out.println("Test class completed");
    }
}
