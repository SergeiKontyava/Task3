import org.testng.Assert;
import org.testng.annotations.Test;

public class DependencyTests {
    private Mathematics testClass;

    @Test
    public void firstTest(){
        testClass = new Mathematics();
        try {

            testClass.setResult(0);
            testClass.add(1,3);
            String message = String.format("Addition %d + %d should be %d", 1, 3, 4);
            Assert.assertEquals(testClass.getResult(), 4, message);
        }
        finally {
            testClass.setResult(0);
        }
    }

    @Test(dependsOnMethods = "firstTest")
    public void secondTest(){
        testClass.multiply(2,5);
        String message = String.format("Multiplication %d * %d should be %d", 2, 5, 10);
        Assert.assertEquals(testClass.getResult(), 10, message);
    }
}
