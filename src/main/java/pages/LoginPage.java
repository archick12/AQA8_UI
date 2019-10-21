package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WebDriverFactory;

import static pages.PagesURLs.loginPage;

public class LoginPage {

  WebDriver driver = null;

  // Login
  private By userNameInput = By.xpath("//input[@name='os_username']");
  private By passwordInput = By.xpath("//input[@name='os_password']");
  private By loginButton = By.xpath("//input[@name='login']");
  private By wrongPasswordLabel = By.xpath("//div[@class='aui-message aui-message-error']");

  public LoginPage() {
    this.driver = WebDriverFactory.getDriver();
  }

  public void navigate() {
    driver.get(loginPage);
  }

  public void enterUserName(String name) {
    driver.findElement(userNameInput).sendKeys(name);
  }

  public void enterPassword(String password) {
    driver.findElement(passwordInput).sendKeys(password);
  }

  public void clickLogin() {
    driver.findElement(loginButton).click();
  }

  public void loginToJira(String name, String password) {
    enterUserName(name);
    enterPassword(password);
    clickLogin();
  }
}
