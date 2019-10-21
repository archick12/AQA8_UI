import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.WebDriverFactory;

public class BaseTest {

  @BeforeTest(groups = "Regression")
  public void setUp() {
    WebDriverFactory.createInstance("Chrome");
  }

  @AfterTest(groups = "Regression")
  public void tearDown() {
    // Close the driver
    WebDriverFactory.getDriver().quit();
  }

}
