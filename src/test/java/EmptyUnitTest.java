import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class EmptyUnitTest  {

    @Test(enabled = false)
    public void ignoreTest(){

    }
    @AfterMethod
    public void clenup(){
        System.out.println("Ignored");
    }
}
