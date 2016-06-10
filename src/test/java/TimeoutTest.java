import org.testng.annotations.Test;

public class TimeoutTest {

    @Test(timeOut = 5000)
    public void timeoutTest() throws InterruptedException {
        Mathematics testClass = new Mathematics();
        testClass.setResult(0);
        testClass.add(1, 10000);
        wait(7000);
    }
}
