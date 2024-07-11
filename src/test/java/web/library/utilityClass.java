package web.library;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class utilityClass extends baseClass{
    public static String readPFData(String key) throws IOException {
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//TestData//config.properties");
        Properties prop =new Properties();
        prop.load(file);
        String value = prop.getProperty(key);
        return value;
    }

    public static void captureSS(int TCID) throws IOException {
        File dest = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File src = new File(System.getProperty("user.dir")+"//src//FailedTestCaseScreenshot//FailedTestCase_"+TCID+".jpg");
        FileHandler.copy(dest,src);
    }

    @DataProvider(name = "credentials")
    public static Object[][] getData() throws IOException {
        XLUtility excel = new XLUtility(System.getProperty("user.dir")+"//src//TestData//TestData.xlsx");
        Object data[][] = excel.twoDArray("loginCre");

        return data;
    }
}
