import org.testng.annotations.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static org.testng.Assert.assertEquals;

public class DDTUnitTest {

    private Mathematics TestClass;

    @BeforeTest
    public void initialize() {
        TestClass = new Mathematics();
        TestClass.setResult(0);
    }
    @DataProvider
    public Object[][] additionData() {
        return new Object[][]{
                {1, 6, 7},
                {2, 9, 11},
                {10, 18, 28},
                {65, 103, 168},
                {264, -3, 261},
        };
    }

    @DataProvider
    public Object[][] subtractionData(){
        return new Object[][]{
                {1, 5, -4},
                {-100, 6, -106},
                {-1, 84, -85},
                {7, 7, 0},
                {100, 1, 99},
        };
    }

    @DataProvider
    public Object[][] divisionData(){
        return new Object[][]{
                {5, 2, 2},
                {-100, 4, -25},
                {-1, 8, 0},
                {7, 7, 1},
                {356, 8, 44},
        };
    }

    @DataProvider
    public Object[][] multiplicationData(){
        return new Object[][]{
                {8, 5, 40},
                {-100, 6, -600},
                {9, 84, 756},
                {7, -701, -4907},
                {385, 3, 1155},
        };
    }

    @Test(dataProvider = "additionData")
    public void additionDDT(int firstValue, int secondValue, int result) {
        TestClass.add(firstValue, secondValue);
        assertEquals(TestClass.getResult(), result);
    }

    @Test(dataProvider = "subtractionData")
    public void subtractionDDT(int firstValue, int secondValue, int result){
        TestClass.setResult(1);
        TestClass.deduct(firstValue, secondValue);
        assertEquals(TestClass.getResult(), result);
    }

    @Test(dataProvider = "divisionData")
    public void divisionDDT(int firstValue, int secondValue, int result){
        TestClass.divide(firstValue, secondValue);
        assertEquals(TestClass.getResult(), result);
    }

    @Test(dataProvider = "multiplicationData")
    public void multiplicationDDT(int firstValue, int secondValue, int result){
        TestClass.multiply(firstValue, secondValue);
        assertEquals(TestClass.getResult(), result);
    }

    @Test
    @Parameters({"firstValue", "secondValue", "result"})
    public void xmlDDTTest(String firstValue, String secondValue, String result){
        TestClass.add(Integer.parseInt(firstValue), Integer.parseInt(secondValue));
        assertEquals(TestClass.getResult(), Integer.parseInt(result));
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface XmlParameters {
        String[] value();
    }

    @Test(dataProvider = "getDataFromXmlFile")
    @XmlParameters({"FirstValue", "SecondValue", "Result"})
    public void testSomething(String firstValue, String secondValue, String result) {
        TestClass.multiply(Integer.parseInt(firstValue), Integer.parseInt(secondValue));
        assertEquals(TestClass.getResult(), Integer.parseInt(result));
    }

    @DataProvider(name = "fileToLoadWithData")
    public static Object[][] getDataFromXmlFile(final Method testMethod) {
        XmlParameters parameters = testMethod.getAnnotation(XmlParameters.class);
        String[] fields = parameters.value();
        //load just the fields you want
        return new Object[][] { { "FirstValue", "SecondValue", "Result" } };
    }

    @AfterMethod
    public void cleanup(){
        TestClass.setResult(0);
    }
}

