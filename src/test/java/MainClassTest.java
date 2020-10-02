import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MainClassTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://credit-test.herokuapp.com/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void firstScenario() {
        mainPage.clickNewLineOfCredit();
        String heading = mainPage.getHeadText();
        Assert.assertEquals("New Line Of Credit", heading);
        mainPage.typeInterestRate("35");
        mainPage.typeCreditLimit("1000");
        mainPage.clickOnSubmitButton();
        Assert.assertEquals("Line of credit was successfully created.", mainPage.getHeadLabel());
        mainPage.selectDrawSelector();
        mainPage.typeAmountInField("500");
        mainPage.selectDay1();
        mainPage.clickSubmitButton();
        mainPage.waitUntilPaymentResult1Appear();
        Assert.assertEquals("Actual interest result are not matching","Interest at 30 days: $14.38", mainPage.getInterestResult());
        Assert.assertEquals("Actual total payoff result are not matching","Total Payoff at 30 days: $514.38", mainPage.getTotalPayoffResult());
    }

    @Test
    public void secondScenario() {
        mainPage.clickNewLineOfCredit();
        String heading = mainPage.getHeadText();
        Assert.assertEquals("New Line Of Credit", heading);
        mainPage.typeInterestRate("35");
        mainPage.typeCreditLimit("1000");
        mainPage.clickOnSubmitButton();
        Assert.assertEquals("Line of credit was successfully created.", mainPage.getHeadLabel());
        mainPage.selectDrawSelector();
        mainPage.typeAmountInField("500");
        mainPage.selectDay1();
        mainPage.clickSubmitButton();
        mainPage.waitUntilPaymentResult1Appear();
        mainPage.selectPaymentSelector();
        mainPage.typeAmountInField("200");
        mainPage.selectDay15();
        mainPage.clickSubmitButton();
        mainPage.waitUntilPaymentResult2Appear();
        mainPage.selectDrawSelector();
        mainPage.typeAmountInField("100");
        mainPage.selectDay25();
        mainPage.clickSubmitButton();
        mainPage.waitUntilPaymentResult3Appear();
        Assert.assertEquals("Actual interest result are not matching","Interest at 30 days: $11.99", mainPage.getInterestResult());
        Assert.assertEquals("Actual total payoff result are not matching","Total Payoff at 30 days: $411.99", mainPage.getTotalPayoffResult());
    }

    @Test
    public void scenarioNegative() {
        mainPage.clickNewLineOfCredit();
        String heading = mainPage.getHeadText();
        Assert.assertEquals("New Line Of Credit", heading);
        mainPage.typeInterestRate("text");
        mainPage.typeCreditLimit("1000");
        mainPage.clickOnSubmitButton();
        Assert.assertTrue("Expected error message",mainPage.errorMessageAppears());
    }

    @After
    public void close() {
        driver.quit();
    }
}
