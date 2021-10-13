package main.ru.ischenko.SeleniumStatistics;
import org.openqa.selenium.WebElement;
import java.util.List;
import static main.ru.ischenko.SeleniumStatistics.Defenitions.FORMAT;
public class MailMessages {
    private List<WebElement> mails;
    public MailMessages(List<WebElement> mails) {
        setMails(mails);
    }
    public List<WebElement> getMails()              { return mails;         }
    public void setMails(List<WebElement> mails)    { this.mails = mails;   }
    public void Print(){
        System.out.printf(FORMAT, "автор", "тема", "дата, время");
        mails.forEach( message -> { if( !message.getAttribute("id").isEmpty( ) ) System.out.print( new MessageParser( message ) ); } );
    }
}
