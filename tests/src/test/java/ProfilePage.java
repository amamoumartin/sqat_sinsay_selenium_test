import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage{

    private final By InvoiceStreet = By.xpath("//input[@id='invoice-street0' and @name='invoice1[street][0]']");
    private final By InvoiceStreetNum = By.xpath("//input[@id='invoice-street1' and @name='invoice1[street][1]']");
    private final By SubmitButton = By.cssSelector("div.form-submit-section > button[type='submit']");

    private WebElement streetInput;
    private WebElement streetNumInput;

    public ProfilePage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.sinsay.com/hu/hu/sales/order/history/");
    }
    
    public void GotoBillingData(){
        WebElement changeBillingDataButton = waitVisibiiltyAndFindElement(By.xpath("//a[contains(@href, 'billing-data')]"));
        changeBillingDataButton.click();
    }

    public void ModifyData(){
        streetInput = waitVisibiiltyAndFindElement(InvoiceStreet);
        streetNumInput = waitVisibiiltyAndFindElement(InvoiceStreetNum);

        streetInput.sendKeys("Utca");
        streetNumInput.sendKeys("2");

        WebElement formContainer = waitVisibiiltyAndFindElement(By.id("customer-invoices-form"));

        WebElement submitButton = new WebDriverWait(driver, Duration.ofSeconds(20))
        .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(formContainer, By.cssSelector("div.form-submit-section > button[type='submit']")))
        .get(0);
        submitButton.click();
    }

    public String GetInvoiceStreetValue(){
        return streetInput.getAttribute("value");
    }

    public String GetInvoiceStreetNumValue(){
        return streetNumInput.getAttribute("value");
    }

}
