import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest {

    public ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public  void firstScenarioPositiveTesting() {
        driver.get("http://credit-test.herokuapp.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.xpath("//a[contains(text(),'New Line of credit')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='line_of_credit_apr']")));

        driver.findElement(By.xpath("//input[@id='line_of_credit_apr']")).sendKeys("35");
        driver.findElement(By.xpath("//input[@id='line_of_credit_credit_limit']")).sendKeys("1000");
        driver.findElement(By.xpath("//input[@name='commit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='notice']")));

        driver.findElement(By.xpath("//input[@id='transaction_amount']")).sendKeys("500");
        driver.findElement(By.xpath("//select[@id='transaction_applied_at']/option[1]")).click();
        driver.findElement(By.xpath("//input[@name='commit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'1')]")));

        String apr = driver.findElement(By.xpath("//p[5]")).getText();
        String total = driver.findElement(By.xpath("//p[6]")).getText();
        Assert.assertEquals("Interest at 30 days: $14.38", apr);
        Assert.assertEquals("Total Payoff at 30 days: $514.38", total);
    }

   @Test
    public  void secondScenarioPositiveTesting() {
        driver.get("http://credit-test.herokuapp.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.xpath("//a[contains(text(),'New Line of credit')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='line_of_credit_apr']")));

        driver.findElement(By.xpath("//input[@id='line_of_credit_apr']")).sendKeys("35");
        driver.findElement(By.xpath("//input[@id='line_of_credit_credit_limit']")).sendKeys("1000");
        driver.findElement(By.xpath("//input[@name='commit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='notice']")));

        driver.findElement(By.xpath("//input[@id='transaction_amount']")).sendKeys("500");
        driver.findElement(By.xpath("//select[@id='transaction_applied_at']/option[1]")).click();
        driver.findElement(By.xpath("//input[@name='commit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]")));

        driver.findElement(By.xpath("//option[contains(text(),'Payment')]")).click();
        driver.findElement(By.xpath("//input[@id='transaction_amount']")).sendKeys("200");
        driver.findElement(By.xpath("//option[contains(text(),'15')]")).click();
        driver.findElement(By.xpath("//input[@name='commit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[2]")));

        driver.findElement(By.xpath("//option[contains(text(),'Draw')]")).click();
        driver.findElement(By.xpath("//input[@id='transaction_amount']")).sendKeys("100");
        driver.findElement(By.xpath("//option[contains(text(),'25')]")).click();
        driver.findElement(By.xpath("//input[@name='commit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[3]")));

        String apr = driver.findElement(By.xpath("//p[5]")).getText();
        String total = driver.findElement(By.xpath("//p[6]")).getText();
        Assert.assertEquals("Interest at 30 days: $11.99", apr);
        Assert.assertEquals("Total Payoff at 30 days: $411.99", total);
    }

    @Test
    public void scenarioNegativeTesting() {
        driver.get("http://credit-test.herokuapp.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.xpath("//a[contains(text(),'New Line of credit')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='line_of_credit_apr']")));

        driver.findElement(By.xpath("//input[@id='line_of_credit_apr']")).sendKeys("text");
        driver.findElement(By.xpath("//input[@id='line_of_credit_credit_limit']")).sendKeys("1000");
        driver.findElement(By.xpath("//input[@name='commit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='error_explanation']")));
    }

    @After
    public void close() {
        driver.quit();
    }
}
