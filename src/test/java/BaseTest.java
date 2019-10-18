import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    WebDriver driver;

    @BeforeTest(groups = "Regression")
    public void setUp() {
        WebDriverManager.chromedriver().version("77.0.3865.40").setup();
        // Create a new instance of the Firefox driver
        this.driver = new ChromeDriver();
    }

    @AfterTest(groups = "Regression")
    public void tearDown() {
        // Close the driver
        this.driver.quit();
    }

}
