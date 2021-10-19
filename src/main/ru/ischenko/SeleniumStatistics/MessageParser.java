package main.ru.ischenko.SeleniumStatistics;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.Locale.forLanguageTag;
import static main.ru.ischenko.SeleniumStatistics.Definitions.*;
public class MessageParser {
    private WebElement message;
    public MessageParser(WebElement message){
        setMessage(message);
    }
    public String getAuthor()   {
        return getMessage().findElement(By.xpath(String.format(AUTHOR_XPATH, getMessage().getAttribute("id")))).getText();
    }
    public String getTheme()    {
        return getMessage().findElement(By.xpath(String.format(THEME_XPATH, getMessage().getAttribute("id")))).getText();
    }
    public String getDateTimeStamp() throws Exception{  //TODO: must be a better way: program compiles pattern on every message parsing, while it has to do it once
        String messageTimeStamp = getMessage().findElement(By.xpath(String.format(TIMESTAMP_XPATH,getMessage().getAttribute("id")))).getText();
        LocalDate currentDate = LocalDate.now();
        if( messageTimeStamp.matches(RECENT_TIME_FORMAT) ) {
            return String.format(DATE_OUTPUT_FORMAT, currentDate, messageTimeStamp );
        } else if (messageTimeStamp.matches(FOUR_DAYS_TIME_FORMAT)){
            Pattern pattern = Pattern.compile(FOUR_DAYS_TIME_FORMAT);
            Matcher matcher = pattern.matcher(messageTimeStamp);
            if (matcher.find()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE", forLanguageTag("RU"));
                TemporalAccessor accessor = formatter.parse( matcher.group(1) );
                int deltaWeekDays = currentDate.getDayOfWeek().compareTo(DayOfWeek.from(accessor));
                int deltaDays = ( deltaWeekDays < 0? deltaWeekDays + 7 : deltaWeekDays );
                return String.format(DATE_OUTPUT_FORMAT, currentDate.minusDays(deltaDays), matcher.group(2) );
            }
        } else if (messageTimeStamp.matches(WEEK_TIME_FORMAT)){
            Pattern pattern = Pattern.compile(WEEK_TIME_FORMAT);
            Matcher matcher = pattern.matcher(messageTimeStamp);
            if (matcher.find()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate messageDate = LocalDate.parse(matcher.group(2) + "." + currentDate.getYear(), formatter);
                return String.format(DATE_OUTPUT_FORMAT, messageDate,getTimeStampFromMessageBody());
            }
        } else if (messageTimeStamp.matches(MONTH_TIME_FORMAT)){
            Pattern pattern = Pattern.compile(MONTH_TIME_FORMAT);
            Matcher matcher = pattern.matcher(messageTimeStamp);
            if (matcher.find()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate messageDate = LocalDate.parse( matcher.group(1),formatter );
                return  String.format(DATE_OUTPUT_FORMAT, messageDate, getTimeStampFromMessageBody() );
            }
        }
        return messageTimeStamp;
    }
    public String getTimeStampFromMessageBody(){
        WebElement grandContainer = getMessage().findElement(By.xpath(GRAND_PARENT_XPATH));
        String timeStamp = grandContainer.findElement(By.xpath(INNER_TIME_DATE_XPATH)).getText();
        Pattern pattern = Pattern.compile(RECENT_TIME_FORMAT);
        Matcher matcher = pattern.matcher(timeStamp);
        if (matcher.find()) return matcher.group(1);
        else                return ERROR_FILLER;
    }
    public String getMessageBody(){
        WebElement grandContainer = getMessage().findElement(By.xpath(GRAND_PARENT_XPATH));
        String bodyString = Jsoup.parse(grandContainer.findElement(By.xpath(MESSAGE_BODY_XPATH)).getAttribute("innerHTML")).text();
        Pattern pattern = Pattern.compile(BODY_DATA_PATTERN);
        Matcher matcher = pattern.matcher(bodyString);
        if(matcher.find()){
            return String.format( "%-10.10s;%5.5s;%-30.30s",
                    matcher.group(4).trim(),
                    matcher.group(5).replace("."," ").trim().replace(" ",":"),
                    (matcher.group(10) != null?
                            ( matcher.group(10).matches(EMPTY_COMMENT_FORMAT)? EMPTY_COMMENT_FILLER: matcher.group(10).trim() ) :
                            EMPTY_COMMENT_FILLER
                    )
            );
        } else return "";
    }
    public void messageSelectorClick(){
        getMessage().findElement(By.xpath(String.format(MESSAGE_SELECTOR_XPATH, getMessage().getAttribute("id")))).click();
    }
    public WebElement getMessage()              { return message; }
    public void setMessage(WebElement message)  { this.message = message; }
    @Override
    public String toString() {
        try {
            return String.format(FORMAT, getAuthor(), getTheme(), getDateTimeStamp(), getMessageBody());
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return null;
    }
}