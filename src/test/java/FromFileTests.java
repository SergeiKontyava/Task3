import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

public class FromFileTests {

    private Mathematics TestClass = new Mathematics();

    @Parameters
    @Test(dataProvider = "additionData")
    public void doStuff(String fileWithTests, int poolsize){
        Properties properties = new Properties();
        InputStream inputStream = null;

        try{
            inputStream = getClass().getClassLoader().getResourceAsStream(fileWithTests);

            properties.load(inputStream);
            Object dataForTest[][] = setDDTValuesForTest(parseInputParam(properties.getProperty("test.firstValue")),
                    parseInputParam(properties.getProperty("test.secondValue")),
                    parseInputParam(properties.getProperty("test.Result")));

            TestClass.add(dataForTest[0][0], dataForTest[1][0]);
            assertEquals(TestClass.getResult(), dataForTest[2][0]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> parseInputParam(String inputString){
        return Arrays.asList(inputString.split(","));
    }

    public Object[] setObject(List<String> listName){
        int size = listName.size();
        int i = 0;
        Object[] object = new Object[size];
        for (String element : listName){
            object[i] = Integer.parseInt(element);
            i++;
        }
        return object;
    }

    public Object[][] setRows(Object[] firstValue, Object[] secondValue, Object[] result){
        Object[][] resultObject = new Object[3][firstValue.length];
        int i = 0;
        for (Object value : firstValue
             ) {
            resultObject[0][i] = value;
        }
        int j = 0;
        for (Object value : secondValue
        ) {
            resultObject[1][j] = value;
        }
        int k = 0;
        for (Object value : result
        ) {
            resultObject[2][k] = value;
        }

        return resultObject;
    }

    @DataProvider
    public Object[][] setDDTValuesForTest(List<String> firstValue, List<String> secondValue, List<String> result){
        Object[] firstValueObject = setObject(firstValue);
        Object[] secondValueObject = setObject(secondValue);
        Object[] resultObject = setObject(result);

        return setRows(firstValueObject, secondValueObject, resultObject);
    }
}
