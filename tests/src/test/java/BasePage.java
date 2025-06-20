import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private final By acceptCookiesButtonLocator = By.id("cookiebotDialogOkButton");
    private final By loggedInUsernameLink = By.cssSelector("div[data-testid='account-info-logged-true'] a");
    private final By loggedOutUsernameLink = By.cssSelector("div[data-testid='account-info-logged-false'] a");
    private final By logoutButton = By.xpath("//li[@data-testid='logout']/a");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    protected List<WebElement> findAll(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected WebElement waitVisibiiltyAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    protected void acceptCookiesByClick(){
        WebElement acceptButton = this.waitVisibiiltyAndFindElement(acceptCookiesButtonLocator);
        if(acceptButton != null){
            acceptButton.click();
        } 
    }

    public String getLoggedInUsername() {
        hoveronUserButton();
        WebElement usernameElement = waitVisibiiltyAndFindElement(loggedInUsernameLink);
        return usernameElement.getText().trim();
    }

    public String getLoggedOutUserName(){
        hoveronUserButton();
        WebElement usernameElement = waitVisibiiltyAndFindElement(loggedOutUsernameLink);
        return usernameElement.getText().trim();
    }

    public ProfilePage goToProfilePage() {
        return new ProfilePage(driver);
    }

    public void logout(){
        WebElement logoutBtn = waitVisibiiltyAndFindElement(logoutButton);
        logoutBtn.click();
    }

    private void hoveronUserButton(){
        WebElement btn = waitVisibiiltyAndFindElement(By.id("accountRoot"));
        Actions actions = new Actions(driver);
        actions.moveToElement(btn).perform();
    }
}