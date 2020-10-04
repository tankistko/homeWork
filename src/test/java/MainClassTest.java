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
    public void scenario1() {
        System.out.println("Step 1: Click on 'New credit line' button");
        mainPage.clickNewLineOfCredit();
        String heading = mainPage.getHeadText();
        Assert.assertEquals("New Line Of Credit", heading);
        mainPage.typeInterestRate("35");
        System.out.println("Step 2: Type 35% on APR input field");
        mainPage.typeCreditLimit("1000");
        System.out.println("Step 3: Type 1000 to 'Credit line' field");
        mainPage.clickOnSubmitButton();
        Assert.assertEquals("Line of credit was successfully created.", mainPage.getHeadLabel());
        System.out.println("Step 4: Click on submit button");
        mainPage.selectDrawSelector();
        System.out.println("Step 5: Select Draw options");
        mainPage.typeAmountInField("500");
        System.out.println("Step 6: Type 500 in amount field");
        mainPage.selectDay1();
        System.out.println("Step 7: Select first day from the list");
        mainPage.clickSubmitButton();
        System.out.println("Step 8: Click 'submit' button");
        mainPage.waitUntilPaymentResult1Appear();
        Assert.assertEquals("Actual interest result are not matching","Interest at 30 days: $14.38",
                mainPage.getInterestResult());
        Assert.assertEquals("Actual total payoff result are not matching","Total Payoff at 30 days: $514.38",
                mainPage.getTotalPayoffResult());
    }

    @Test
    public void scenario2() {
        System.out.println("Step 1: Click on 'New credit line' button");
        mainPage.clickNewLineOfCredit();
        String heading = mainPage.getHeadText();
        Assert.assertEquals("New Line Of Credit", heading);
        mainPage.typeInterestRate("35");
        System.out.println("Step 2: Type 35% on APR input field");
        mainPage.typeCreditLimit("1000");
        System.out.println("Step 3: Type 1000 to 'Credit line' field");
        mainPage.clickOnSubmitButton();
        Assert.assertEquals("Line of credit was successfully created.", mainPage.getHeadLabel());
        System.out.println("Step 4: Click on submit button");
        mainPage.selectDrawSelector();
        System.out.println("Step 5: Select Draw options");
        mainPage.typeAmountInField("500");
        System.out.println("Step 6: Type 500 in amount field");
        mainPage.selectDay1();
        System.out.println("Step 7: Select first day from the list");
        mainPage.clickSubmitButton();
        System.out.println("Step 8: Click 'submit' button");
        mainPage.waitUntilPaymentResult1Appear();
        Assert.assertEquals("Actual interest result are not matching","Interest at 30 days: $14.38", mainPage.getInterestResult());
        Assert.assertEquals("Actual total payoff result are not matching","Total Payoff at 30 days: $514.38", mainPage.getTotalPayoffResult());
        mainPage.waitUntilPaymentResult1Appear();
        mainPage.selectPaymentSelector();
        System.out.println("Step 9: Select 'Payment' from options");
        mainPage.typeAmountInField("200");
        System.out.println("Step 10: Type 200 in amount field");
        mainPage.selectDay15();
        System.out.println("Step 11: Select 15th day from the list");
        mainPage.clickSubmitButton();
        System.out.println("Step 12: Click 'Submit' button");
        mainPage.waitUntilPaymentResult2Appear();
        mainPage.selectDrawSelector();
        System.out.println("Step 13: Select Draw options");
        mainPage.typeAmountInField("100");
        System.out.println("Step 14: Type 100 in amount field");
        mainPage.selectDay25();
        System.out.println("Step 15: Select 25th day from the list");
        mainPage.clickSubmitButton();
        System.out.println("Step 16: Click 'Submit' button");
        mainPage.waitUntilPaymentResult3Appear();
        Assert.assertEquals("Actual interest result are not matching","Interest at 30 days: $11.99", mainPage.getInterestResult());
        Assert.assertEquals("Actual total payoff result are not matching","Total Payoff at 30 days: $411.99", mainPage.getTotalPayoffResult());
    }

    @Test
    public void negativeScenario() {
        mainPage.clickNewLineOfCredit();
        System.out.println("Step 1: Click on 'New credit line' button");
        String heading = mainPage.getHeadText();
        Assert.assertEquals("New Line Of Credit", heading);
        mainPage.typeInterestRate("text");
        System.out.println("Step 2: Type text in APR field");
        mainPage.typeCreditLimit("1000");
        System.out.println("Step 3: Type 1000 in amount field");
        mainPage.clickOnSubmitButton();
        System.out.println("Step 4: Click 'Submit' button");
        Assert.assertTrue("Expected error message",mainPage.errorMessageAppears());
    }

    @After
    public void close() {
        driver.quit();
        System.out.println("Driver close");
    }
}
