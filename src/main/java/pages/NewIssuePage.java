package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WebDriverFactory;

public class NewIssuePage {

    WebDriver driver;
    CommonActions commonActions;

    // Create Issue
    private By createIssueButton = By.id("create_link");
    private By projectInput = By.cssSelector("#project-field");
    private By issueTypeInput = By.id("issuetype-field");
    private By summaryInput = By.id("summary");
    private By dataModeSource = By.xpath("//li[@data-mode='source']");
    private By descriptionInput = By.xpath("//textarea[@name='description']");
    private By createButton = By.cssSelector("#create-issue-submit");
    private By issueSuccessfullyCreated = By.xpath("//div[@class='aui-message closeable aui-message-success aui-will-close']");


    public NewIssuePage() {
        this.driver = WebDriverFactory.getDriver();
        commonActions = new CommonActions(driver);
    }

    public void clickCreateNewIssueButton() {
        commonActions.click(createIssueButton, 3, 3);
    }

    public void enterProjectName(String name) {
        commonActions.enterText(projectInput, "QAAUTO-8", 3, 3);
    }

    public void enterIssueType(String type) {
        commonActions.enterText(issueTypeInput, type, 3, 3);
    }

    public void enterIssueSummary(String text) {
        commonActions.enterText(summaryInput, text, 3, 3);
    }

    public void enterIssueDescription(String text) {
        driver.findElement(dataModeSource);
        commonActions.enterText(descriptionInput, text, 3, 3);
    }

    public void clickCreateIssue() {
        commonActions.click(createButton, 3, 3);
    }

    public boolean issueCreatedPopupIsPresent() {
        return commonActions.waitFor(issueSuccessfullyCreated, 3, 3).isDisplayed();
    }

}
