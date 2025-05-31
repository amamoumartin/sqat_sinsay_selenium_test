import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    private final By emailField = By.xpath("//input[@data-selen='login-email']");
    private final By passwordField = By.xpath("//input[@data-selen='login-password']");
    private final By submitButton = By.xpath("//button[@data-selen='login-submit']");

    private String email;
    private String password;

    public LoginPage(WebDriver driver) {
        super(driver);
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new FileNotFoundException("Property file not found in the classpath");
            }
            properties.load(input);
            email = properties.getProperty("email");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.driver.get("https://www.sinsay.com/hu/hu/customer/account/login/");
        acceptCookiesByClick();
    }
    
    public void login(){
        WebElement emailInput = waitVisibiiltyAndFindElement(emailField);
        WebElement passwordInput = waitVisibiiltyAndFindElement(passwordField);

        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);

        WebElement loginButton = waitVisibiiltyAndFindElement(submitButton);
        loginButton.click();
    }

    

    

}
