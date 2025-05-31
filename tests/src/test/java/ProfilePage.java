import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage extends BasePage{

    private final By InvoiceStreet = By.xpath("//input[@id='invoice-street0' and @name='invoice1[street][0]']");
    private final By InvoiceStreetNum = By.xpath("//input[@id='invoice-street1' and @name='invoice1[street][1]']");
    private final By SubmitButton = By.xpath("//button[contains(@class, 'btn-imp') and contains(text(), 'ment')]");

    private WebElement streetInput;
    private WebElement streetNumInput;

    public ProfilePage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.sinsay.com/hu/hu/sales/order/history/");
    }
    
    public void GotoBillingData(){
        WebElement changeBillingDataButton = waitVisibiiltyAndFindElement(By.id("customer-invoices-link"));
        changeBillingDataButton.click();
    }

    public void ModifyData(){
        streetInput = waitVisibiiltyAndFindElement(InvoiceStreet);
        streetNumInput = waitVisibiiltyAndFindElement(InvoiceStreetNum);

        streetInput.sendKeys("TesztUtca");
        streetNumInput.sendKeys("2");

        WebElement submitBtn = waitVisibiiltyAndFindElement(SubmitButton);
        submitBtn.click();
    }

    public String GetInvoiceStreetValue(){
        return streetInput.getAttribute("value");
    }

    public String GetInvoiceStreetNumValue(){
        return streetNumInput.getAttribute("value");
    }

}
