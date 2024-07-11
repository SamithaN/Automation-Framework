package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web.library.baseClass;

public class LoginPage extends baseClass {
    @FindBy(xpath = "//div[@class=\"login_logo\"]")private  WebElement logo;
    @FindBy(id="user-name") private WebElement UN;
    @FindBy(id="password") private WebElement PWD;
    @FindBy(id="login-button") private WebElement loginBtn;
    @FindBy(xpath = "//div[@class='error-message-container error']")private WebElement errorMsg;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public String verifyurl(){
        String url = driver.getCurrentUrl();
        return url;
    }

    public boolean verifyLogo(){
        boolean lg = logo.isDisplayed();
        return lg;
    }

    public String verifyPageTitle(){
        return driver.getTitle();
    }

    public void enterCredentials(String username, String password){
        UN.sendKeys(username);
        PWD.sendKeys(password);
        loginBtn.click();
    }

    public String getErrorMsg(){
        return errorMsg.getText();
    }

}
