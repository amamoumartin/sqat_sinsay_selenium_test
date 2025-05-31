import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.MouseAction.Button;

class MainPage extends BasePage{

    private By logoLocator = By.cssSelector("a[data-testid='brand-logo-button']");
    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.sinsay.com/hu/hu");
        acceptCookiesByClick();       
    }

    public String getPageTitle(){
        return this.waitVisibiiltyAndFindElement(logoLocator).getAttribute("aria-label");
    }   

    
}
