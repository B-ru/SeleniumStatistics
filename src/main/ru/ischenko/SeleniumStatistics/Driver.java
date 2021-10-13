package main.ru.ischenko.SeleniumStatistics;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static main.ru.ischenko.SeleniumStatistics.Defenitions.*;
public class Driver {
    private WebDriver driver;
    public Driver(){
        System.setProperty("webdriver.gecko.driver", "/opt/WebDriver/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(700,10000));
        driver.manage().window().setPosition(new Point(0,-10000));
        setDriver( driver );
    }
    public void Login( String[ ] args ){
        getDriver().get( args[SITE] );
        getDriver().findElement( By.id( "username" ) ).sendKeys( args[LOGIN] );
        getDriver().findElement( By.id( "password" ) ).sendKeys( args[PASSWD] + Keys.ENTER);
    }
    public void Wait() throws Exception{
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'_lvv_11')]/div/div[contains(@tabindex,'-1')]")));
        Thread.sleep(DELAY);
    }
    public List<WebElement> FindMailRecordsDiv(){
        return driver.findElements(By.xpath("//div[contains(@autoid,'_lvv_9')]/div"));
    }
    public void Close() throws Exception{
        Thread.sleep(DELAY);
        driver.quit();
    }
    public WebDriver getDriver()            { return driver;        }
    public void setDriver(WebDriver driver) { this.driver = driver; }
}