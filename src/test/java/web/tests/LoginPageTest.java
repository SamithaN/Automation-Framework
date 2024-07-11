package web.tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.library.baseClass;
import web.library.utilityClass;
import web.pages.LoginPage;

import java.io.IOException;
import java.net.URL;

public class LoginPageTest extends baseClass {
    LoginPage login;
    int TCID;

    @BeforeMethod
    public void setup(){
        login = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(result.getStatus()==ITestResult.FAILURE){
            utilityClass.captureSS(TCID);
        }
        driver.quit();
    }
    @Test(enabled = true)
    public void verifyURL() throws IOException {
        String actURL = login.verifyurl();
        String expURL = utilityClass.readPFData("URL");
        Assert.assertEquals(actURL,expURL);
    }

    @Test(enabled = true)
    public void verifyLogo(){
        boolean actLogo = login.verifyLogo();
        boolean expLogo = true;
        Assert.assertEquals(actLogo,expLogo);
    }

    @Test(enabled = true)
    public void verifyPageTitle(){
        login.verifyPageTitle();
    }

    @DataProvider(name = "credential")
    public Object[][] getData(){
        return new Object[][]
                {
                        {"valid","standard_user","secret_sauce"},
                        {"invalid","abc","123abc"},
                        {"invalidUN","abc","secret_sauce"},
                        {"invalidPWD","standard_user","123abc"},
                        {"blank","",""}
                };
    }

    @Test(enabled = true,dataProvider = "credential")
    public void verifyLoginFunctionality(String scenario, String username, String password) throws IOException {
        login.enterCredentials(username, password);
        if(scenario=="valid"){
            String actResult = login.verifyurl();
            String expResult = utilityClass.readPFData("ProductPageURL");
            Assert.assertEquals(actResult,expResult);
        } else if (scenario.equals("invalid")) {
            String actResult = login.getErrorMsg();
            String expResult = "Epic sadface: Username and password do not match any user in this service";
            Assert.assertEquals(actResult,expResult);
        } else if (scenario.equals("invalidUN")) {
            String actResult = login.getErrorMsg();
            String expResult = "Epic sadface: Username and password do not match any user in this service";
            Assert.assertEquals(actResult,expResult);
        } else if (scenario.equals("invalidPWD")) {
            String actResult = login.getErrorMsg();
            String expResult = "Epic sadface: Username and password do not match any user in this service";
            Assert.assertEquals(actResult,expResult);
        }
        else if (scenario.equals("blank")) {
            String actResult = login.getErrorMsg();
            String expResult = "Epic sadface: Username is required";
            Assert.assertEquals(actResult,expResult);
        }
    }
}
