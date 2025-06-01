import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.net.MalformedURLException;

public class SinsayPageTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }  
    
    @Test
    public void testTitle() {        
        MainPage mainPage = new MainPage(driver);
        String logoLabel = mainPage.getPageTitle();
        Assert.assertEquals("sinsay", logoLabel);
    }
    
    @Test
    public void testLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        String loggedInUserName = loginPage.getLoggedInUsername();
        Assert.assertEquals("Teszt", loggedInUserName);
    }
    
    @Test
    public void testLogout(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        ProfilePage profilePage = loginPage.goToProfilePage();
        profilePage.logout();
        ByteBuffer userName = StandardCharsets.UTF_8.encode(profilePage.getLoggedOutUserName());
        Assert.assertTrue(userName.equals(StandardCharsets.UTF_8.encode("Fiók")));
    }
    
    @Test
    public void formSendingTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        ProfilePage profilePage = loginPage.goToProfilePage();
        profilePage.GotoBillingData();
        profilePage.ModifyData();

        Assert.assertTrue(profilePage.GetInvoiceStreetValue().contains("Utca"));
        Assert.assertTrue(profilePage.GetInvoiceStreetNumValue().contains("2"));
    }

    @Test
    public void staticPageTest(){
        CookiesPage cookiesPage = new CookiesPage(driver);
        Assert.assertTrue(cookiesPage.getCookiesPageTitle()
        .contains("Cookies"));
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}

