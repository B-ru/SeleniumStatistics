package main.ru.ischenko.SeleniumStatistics;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static main.ru.ischenko.SeleniumStatistics.Defenitions.FORMAT;
public class MessageParser {
    private WebElement message;
    public MessageParser(WebElement message){
        setMessage(message);
    }
    public WebElement getMessage()                { return message; }
    public void setMessage(WebElement message)    { this.message = message; }
    public String getAuthor()   {
        return getMessage().findElement(By.xpath(String.format("//div[contains(@id,'%s')]//span[contains(@autoid,'_lvv_5')]", getMessage().getAttribute("id")))).getText();
    }
    public String getTheme()    {
        return getMessage().findElement(By.xpath(String.format("//div[contains(@id,'%s')]//span[contains(@autoid,'_lvv_6')]", getMessage().getAttribute("id")))).getText();
    }
    public String getTimeStamp(){
        return getMessage().findElement(By.xpath(String.format("//div[contains(@id,'%s')]/div/div/span[text()]",getMessage().getAttribute("id")))).getText();
    }
    @Override
    public String toString()    {
        return String.format(FORMAT,getAuthor(),getTheme(),getTimeStamp());
    }
}