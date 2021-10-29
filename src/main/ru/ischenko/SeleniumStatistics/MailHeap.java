package main.ru.ischenko.SeleniumStatistics;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import static main.ru.ischenko.SeleniumStatistics.Definitions.*;
public class MailHeap implements Iterator {
    private WebDriver   driver;
    private String      messageId = "";
    public MailHeap(String path ){
        System.setProperty( "webdriver.gecko.driver", path );
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1024,800));
        driver.manage().window().setPosition(new Point(3000,0));
        setDriver( driver );
    }
    public void Authorise(String[ ] args ){
        getDriver().get( args[URL_IDX] );
        getDriver().findElement( By.id( "username" ) ).sendKeys( args[LOGIN_IDX] );
        getDriver().findElement( By.id( "password" ) ).sendKeys( args[PASSWD_IDX] + Keys.ENTER);
    }
    public void WaitForLoading(String multiplier ) throws Exception{
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(WAIT_ELEMENT_XPATH)));
        Thread.sleep(Math.round( DELAY * Float.parseFloat(multiplier) ) );
    }
    @Override
    public boolean hasNext() {
        if("".equals(getMessageId())){
            try {
                driver.findElement( By.xpath( FIRST_MESSAGE_XPATH ) );
                return true;
            }
            catch (Exception e) {
                return false;
            }
        } else {
            try {
                driver.findElement( By.xpath( NEXT_MESSAGE_XPATH ) );
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }
    }
    @Override
    public WebElement next(){
        WebElement newMessage;
        if("".equals(getMessageId()))newMessage = driver.findElement( By.xpath( FIRST_MESSAGE_XPATH ) );
        else newMessage = driver.findElement( By.xpath( NEXT_MESSAGE_XPATH ) );
        newMessage.click();
        try { Thread.sleep(CLICK_DELAY); } catch (Exception e) {System.err.println(e.getMessage());}
        setMessageId(newMessage.getAttribute("id"));
        return newMessage;
    }
    public void Close(){
        driver.quit();
    }
    public WebDriver getDriver()                        { return driver;                        }
    public void setDriver(WebDriver driver)             { this.driver = driver;                 }
    public String getMessageId()                        { return messageId;                     }
    public void setMessageId(String messageId)          { this.messageId = messageId;           }
    public void resetMessagePointer()                   { setMessageId("");                     }
}