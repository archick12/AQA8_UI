import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.NewIssuePage;

public class JIRATest {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().version("77.0.3865.40").setup();
        // Create a new instance of the Firefox driver
        this.driver = new ChromeDriver();
    }


    @Test
    public void loginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.loginToJira("webinar5", "webinar5");
        Assert.assertEquals(driver.getCurrentUrl(), "https://jira.hillel.it/secure/Dashboard.jspa");
    }

    @Test
    public void createIssue() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.loginToJira("webinar5", "webinar5");
        Assert.assertEquals(driver.getCurrentUrl(), "https://jira.hillel.it/secure/Dashboard.jspa");

        NewIssuePage newIssuePage = new NewIssuePage(driver);
        newIssuePage.clickCreateNewIssueButton();
        newIssuePage.enterProjectName("QAAUT-8");
        newIssuePage.enterIssueType("Test");
        newIssuePage.enterIssueSummary("Some Summary");
        newIssuePage.enterIssueDescription("Some Desc");
        newIssuePage.clickCreateIssue();

    }

    @AfterTest
    public void tearDown() {
        // Close the driver
        this.driver.quit();
    }


}