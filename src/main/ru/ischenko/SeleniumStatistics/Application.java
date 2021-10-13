package main.ru.ischenko.SeleniumStatistics;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class Application {
    public static void main(String[] args) throws Exception {
        //TODO: setup method
        System.setProperty("webdriver.gecko.driver", "/opt/WebDriver/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(700,10000));
        driver.manage().window().setPosition(new Point(0,-10000));
        //TODO: login method
        driver.get("https://somepage.org/");
        driver.findElement(By.id("username")).sendKeys("username");
        driver.findElement(By.id("password")).sendKeys("password" + Keys.ENTER);
        //TODO: wait method
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'_lvv_11')]/div/div[contains(@tabindex,'-1')]")));
        Thread.sleep(5000);
        //TODO: main logic methods
        List<WebElement> mails = driver.findElements(By.xpath("//div[contains(@autoid,'_lvv_9')]/div"));
        System.out.printf("%40.40s\t%40.40s\t%20.20s\n", "автор", "тема", "дата, время");
        mails.forEach( message -> {
            if(!message.getAttribute("id").isEmpty()){
                String authorXPath = String.format("//div[contains(@id,'%s')]//span[contains(@autoid,'_lvv_5')]",message.getAttribute("id"));
                String themeXPath = String.format("//div[contains(@id,'%s')]//span[contains(@autoid,'_lvv_6')]",message.getAttribute("id"));
                String dateXPath = String.format("//div[contains(@id,'%s')]/div/div/span[text()]",message.getAttribute("id"));
                System.out.printf("%40.40s\t%40.40s\t%20.20s\n",
                    message.findElement(By.xpath(authorXPath)).getText(),
                    message.findElement(By.xpath(themeXPath)).getText(),
                    message.findElement(By.xpath(dateXPath)).getText());
            }
        });
        //TODO: close method
        Thread.sleep(10000);
        driver.quit();
     }
}