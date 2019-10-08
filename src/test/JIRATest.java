import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class JIRATest {

  WebDriver driver;

  @BeforeTest
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    // Create a new instance of the Firefox driver
    this.driver = new ChromeDriver();
  }

  // Login
  private By userNameInput = By.xpath("//input[@name='os_username']");
  private By passwordInput = By.xpath("//input[@name='os_password']");
  private By loginButton = By.xpath("//input[@name='login']");
  private By wrongPasswordLabel = By.xpath("//div[@class='aui-message aui-message-error']");

  // Create Issue
  private By createIssueButton = By.id("create_link");
  private By projectInput = By.cssSelector("#project-field");
  private By issueTypeInput = By.id("issuetype-field");
  private By summaryInput = By.id("summary");
  private By dataModeSource = By.xpath("//li[@data-mode='source']");
  private By descriptionInput = By.xpath("//textarea[@name='description']");
  private By createButton = By.cssSelector("#create-issue-submit");
  private By issueSuccessfullyCreated = By.xpath("//div[@class='aui-message closeable aui-message-success aui-will-close']");


  @Test
  public void loginTest() {
    this.driver.get("https://jira.hillel.it/login.jsp");
    driver.findElement(userNameInput).sendKeys("webinar5");
    driver.findElement(passwordInput).sendKeys("webinar5");
    driver.findElement(loginButton).click();
    Assert.assertEquals(driver.getCurrentUrl(), "https://jira.hillel.it/secure/Dashboard.jspa");
  }

  @Test
  public void createIssue() throws InterruptedException {
    this.driver.get("https://jira.hillel.it/login.jsp");
    enterText(userNameInput, "webinar5", 3, 10);
    enterText(passwordInput, "webinar5", 3, 10);
    click(loginButton, 3, 10);
    Assert.assertEquals(driver.getCurrentUrl(), "https://jira.hillel.it/secure/Dashboard.jspa");

    click(createIssueButton, 3, 10);

    enterText(projectInput, "QAAUTO-8", 3, 10);
    enterText(issueTypeInput, "Test", 3, 10);
    enterText(summaryInput, "This is an automatic test.", 3, 10);

    driver.findElement(dataModeSource);
    enterText(descriptionInput, "This is an automatic test.", 3, 10);
    driver.findElement(createButton).click();

    Assert.assertEquals(waitFor(issueSuccessfullyCreated, 3, 10).isDisplayed(), true);
  }

  @AfterTest
  public void tearDown() {
    // Close the driver
    this.driver.quit();
  }

  /*
   * Такой подход лучше чем ничего, но все равно приведет к исключительным ситуациям.
   * Пробема тут, что избежав исключительную ситуцию при поиске элемента, мы вовсе не обезопасили себя
   * от исключения при попытке взаимодействовать с элементом.
   * Для решения этой проблемы ознакомьтесь со следующим после этого метода методом.
   * */
  private WebElement waitFor(By element, int retry, int timeoutSeconds) {
    for (int i = retry; i > 0; i--) {
      try {
        driver.findElement(element);
      } catch (Exception ex) {
        try {
          Thread.sleep(TimeUnit.SECONDS.toMillis(timeoutSeconds));
          driver.findElement(element);
        } catch (Exception ex2) {
          System.out.println("Searching element" + element.toString() + ". Retry - " + (retry - i));
          continue;
        }
      }
      return driver.findElement(element);
    }
    return driver.findElement(element);
  }

  private void click(By element, int retry, int timeoutSeconds) {
    for (int i = retry; i > 0; i--) {
      try {
        System.out.println("Searching element" + element.toString() + ". Retry - " + (retry - i));
        driver.findElement(element).click();
        break;
      } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException ex) {
        try {
          System.out.println("Searching element" + element.toString() + ". Retry - " + (retry - i));
          Thread.sleep(TimeUnit.SECONDS.toMillis(timeoutSeconds));
          driver.findElement(element).click();
          break;
        } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException | InterruptedException ex2) {
          continue;
        }
      }
    }
  }

  private void enterText(By element, String text, int retry, int timeoutSeconds) {
    for (int i = retry; i > 0; i--) {
      try {
        System.out.println("Searching element" + element.toString() + ". Retry - " + (retry - i));
        driver.findElement(element).sendKeys(text);
        break;
      } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException ex) {
        try {
          System.out.println("Searching element" + element.toString() + ". Retry - " + (retry - i));
          Thread.sleep(TimeUnit.SECONDS.toMillis(timeoutSeconds));
          driver.findElement(element).sendKeys(text);
          break;
        } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException | InterruptedException ex2) {
          continue;
        }
      }
    }
  }

}