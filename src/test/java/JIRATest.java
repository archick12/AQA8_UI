import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.NewIssuePage;
import utils.WebDriverFactory;

public class JIRATest extends BaseTest {

    @Feature("Login")
    @Test(groups = {"Regression"})
    public void loginTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.navigate();
        loginPage.loginToJira("webinar5", "webinar5");
        Assert.assertEquals(WebDriverFactory.getDriver().getCurrentUrl(), "https://jira.hillel.it/secure/Dashboard.jspa");
        Assert.assertTrue(1 == 1);
    }

    @Feature("Issue")
    @Test(groups = {"Regression", "SKIP"})
    public void createIssue() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        loginPage.navigate();
        loginPage.loginToJira("webinar5", "webinar5");
        Assert.assertEquals(WebDriverFactory.getDriver().getCurrentUrl(), "https://jira.hillel.it/secure/Dashboard.jspa");

        NewIssuePage newIssuePage = new NewIssuePage();
        newIssuePage.clickCreateNewIssueButton();
        newIssuePage.enterProjectName("QAAUT-8");
        newIssuePage.enterIssueType("Test");
        newIssuePage.enterIssueSummary("Some Summary");
        newIssuePage.enterIssueDescription("Some Desc");
        newIssuePage.clickCreateIssue();

    }

    @Feature("Issue")
    @Test(groups = {"Regression", "SKIP"})
    public void testToBeSkipped() throws InterruptedException {

    }

    @Feature("Login")
    @Test(groups = {"Regression"}, dataProvider = "data-provider")
    public void unsuccessfulLogin(String userName, String password, String msg) {
        System.out.println(msg);
        LoginPage loginPage = new LoginPage();
        loginPage.navigate();
        loginPage.loginToJira(userName, password);
        Assert.assertEquals(loginPage.isErrorMessagePresent(msg), true);
    }

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderData() {
        return new Object[][]{
                {"webinar5", "wrongPassword", "Sorry, your username and password are incorrect - please try again."},
                {"wrongUserName", "webinar5", "Sorry, your username and password are incorrect - please try again."}
        };
    }
}

