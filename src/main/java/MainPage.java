import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By newLineOfCredit = By.xpath("//a[contains(text(),'New Line of credit')]");
    private By paymentResult1 = By.xpath("//tbody/tr[1]");
    private By paymentResult2 = By.xpath("//tbody/tr[2]");
    private By paymentResult3 = By.xpath("//tbody/tr[3]");
    private By headLabel = By.xpath("//p[@id='notice']");
    private By interestResult = By.xpath("//p[5]");
    private By totalPayoffResult = By.xpath("//p[6]");
    private By drawSelector = By.xpath("//option[contains(text(),'Draw')]");
    private By paymentSelector = By.xpath("//option[contains(text(),'Payment')]");
    private By amountField = By.xpath("//input[@id='transaction_amount']");
    private By day1 = By.xpath("//option[contains(text(),'1')]");
    private By day15 = By.xpath("//option[contains(text(),'15')]");
    private By day25 = By.xpath("//option[contains(text(),'25')]");
    private By buttonSubmit = By.xpath("//input[@name='commit']");
    private By interestField = By.xpath("//input[@id='line_of_credit_apr']");
    private By creditLimitField = By.xpath("//input[@id='line_of_credit_credit_limit']");
    private By button = By.xpath("//input[@name='commit']");
    private By newLineHead = By.xpath("//h1[contains(text(),'New Line Of Credit')]");
    private By errorMessage = By.xpath("//div[@id='error_explanation']");

    public void clickNewLineOfCredit() {
        driver.findElement(newLineOfCredit).click();
    }

    public String getHeadLabel() {
        return driver.findElement(headLabel).getText();
    }

    public String getInterestResult() {
        return driver.findElement(interestResult).getText();
    }

    public String getTotalPayoffResult()
    {
        return driver.findElement(totalPayoffResult).getText();
    }

    public void waitUntilPaymentResult1Appear() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentResult1));
    }

    public void waitUntilPaymentResult2Appear() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentResult2));
    }

    public void waitUntilPaymentResult3Appear() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentResult3));
    }

    public void selectDrawSelector() {
        driver.findElement(drawSelector).click();
    }

    public void selectPaymentSelector() {
        driver.findElement(paymentSelector).click();
    }

    public void typeAmountInField(String amount) {
        driver.findElement(amountField).sendKeys(amount);
    }

    public void selectDay1() {
        driver.findElement(day1).click();

    }

    public void selectDay15() {
        driver.findElement(day15).click();
    }

    public void selectDay25() {
        driver.findElement(day25).click();
    }

    public void clickSubmitButton() {
        driver.findElement(buttonSubmit).click();
    }

    public void typeInterestRate(String APR) {
        driver.findElement(interestField).sendKeys(APR);
    }

    public void typeCreditLimit(String limit) {
        driver.findElement(creditLimitField).sendKeys(limit);
    }

    public void clickOnSubmitButton() {
        driver.findElement(button).click();
    }

    public String getHeadText() {
        return driver.findElement(newLineHead).getText();
    }

    public boolean errorMessageAppears() {
        return driver.findElement(errorMessage).isDisplayed();
    }
}
