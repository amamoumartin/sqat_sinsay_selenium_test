import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CookiesPage extends BasePage{

    private final By pageTitle = By.xpath("//div[@class='page-title']/h1");
    public CookiesPage(WebDriver driver) {
        super(driver);      
        this.driver.get("https://www.sinsay.com/hu/hu/cookies-list");
    }
    
    public String getCookiesPageTitle(){
        WebElement element = driver.findElement(pageTitle);
        return element.getText();
    }
}
