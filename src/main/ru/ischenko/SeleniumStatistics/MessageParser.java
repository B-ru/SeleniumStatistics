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
    private String author               = EMPTY_FIELD_FILLER;
    private String theme                = EMPTY_FIELD_FILLER;
    private String timeStamp            = EMPTY_FIELD_FILLER;
    private String parsedMessageBody    = EMPTY_FIELD_FILLER;
    private String customTimeStamp      = EMPTY_FIELD_FILLER;
    private String comment              = EMPTY_FIELD_FILLER;
    private String sysNote              = EMPTY_FIELD_FILLER;
    public MessageParser(WebElement message){
        setMessage(message);
        setAuthor(parseAuthor());
        setTheme(parseTheme());
        setTimeStamp(parseDateTimeFromHeader());
        setParsedMessageBody(parseMessageBody());
        setCustomTimeStamp(parseCustomTimeStamp());
        setComment(parseComment());
    }
    public String parseAuthor()   {
        return getMessage().findElement(By.xpath(String.format(AUTHOR_XPATH, getMessage().getAttribute("id")))).getText();
    }
    public String parseTheme()    {
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
                return String.format(DATE_OUTPUT_FORMAT, messageDate, parseTimeStampFromMessageBody());
            }
        } else if (messageTimeStamp.matches(MONTH_TIME_FORMAT)){
            Pattern pattern = Pattern.compile(MONTH_TIME_FORMAT);
            Matcher matcher = pattern.matcher(messageTimeStamp);
            if (matcher.find()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate messageDate = LocalDate.parse( matcher.group(1),formatter );
                return  String.format(DATE_OUTPUT_FORMAT, messageDate, parseTimeStampFromMessageBody() );
            }
        }
        return messageTimeStamp;
    }
    public String parseTimeStampFromMessageBody(){
        WebElement grandContainer = getMessage().findElement(By.xpath(GRAND_PARENT_XPATH));
        String timeStamp = grandContainer.findElement(By.xpath(INNER_TIME_DATE_XPATH)).getText();
        Pattern pattern = Pattern.compile(RECENT_TIME_FORMAT);
        Matcher matcher = pattern.matcher(timeStamp);
        if (matcher.find()) return matcher.group(1);
        else                return ERROR_FILLER;
    }
    public String parseMessageBody(){
        WebElement grandContainer = getMessage().findElement(By.xpath(GRAND_PARENT_XPATH));
        String bodyString = Jsoup.parse(grandContainer.findElement(By.xpath(MESSAGE_BODY_XPATH)).getAttribute("innerHTML")).text();
        Pattern pattern = Pattern.compile(BODY_DATA_PATTERN);
        Matcher matcher = pattern.matcher(bodyString);
        if(matcher.find()){
            return bodyString;
        } else
        return EMPTY_FIELD_FILLER;
    }
    public String parseDateTimeFromHeader(){
        return getMessage().getAttribute("data-time");
    }
    public String parseCustomTimeStamp(){
        Pattern pattern = Pattern.compile(BODY_DATA_PATTERN);
        Matcher matcher = pattern.matcher(getParsedMessageBody());
        if(matcher.find()){
            String customTimeStamp = String.format( "%s %s",
                matcher.group(DATE_IDX).trim(),
                matcher.group(TIME_IDX).replace("-",":").replace("."," ").trim().replace(" ",":")
            );
            pattern = Pattern.compile(CUSTOM_TIMESTAMP_PATTERN);
            matcher = pattern.matcher(customTimeStamp);
            if(matcher.find()){
                if(matcher.group(4).matches("^\\d\\d$")){
                    return (matcher.group(1) + ".20" + matcher.group(4)).replace("/", ".");
                }else return customTimeStamp.replace("/", ".");
            } else {
                setSysNote(getSysNote() + CUSTOM_TIMESTAMP_ERROR);
                return EMPTY_FIELD_FILLER;
            }
        }
        return EMPTY_FIELD_FILLER;
    }
    public String parseComment(){
        Pattern pattern = Pattern.compile(BODY_DATA_PATTERN);
        Matcher matcher = pattern.matcher(getParsedMessageBody());
        if(matcher.find()){
            return String.format( "%s",
                (matcher.group(COMMENT_IDX) != null?
                    ( matcher.group(COMMENT_IDX).matches(EMPTY_COMMENT_FORMAT)? EMPTY_FIELD_FILLER : matcher.group(COMMENT_IDX).trim() ) :
                        EMPTY_FIELD_FILLER
                )
            );
        } else return EMPTY_FIELD_FILLER;
    }
    @Override
    public String toString() {
        try {
            return String.format(FORMAT, getAuthor(), getTheme(), getTimeStamp(), getCustomTimeStamp(), getComment(), getSysNote());
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return null;
    }
    public WebElement getMessage()                              { return message; }
    public void setMessage(WebElement message)                  { this.message = message; }
    public void setAuthor(String author)                        { this.author = author; }
    public String getAuthor()                                   { return author; }
    public void setTheme(String theme)                          { this.theme = theme; }
    public String getTheme()                                    { return theme; }
    public String getTimeStamp()                                { return timeStamp; }
    public void setTimeStamp(String timeStamp)                  { this.timeStamp = timeStamp; }
    public String getCustomTimeStamp()                          { return customTimeStamp; }
    public void setCustomTimeStamp(String customTimeStamp)      { this.customTimeStamp = customTimeStamp; }
    public String getComment()                                  { return comment; }
    public void setComment(String comment)                      { this.comment = comment; }
    public String getParsedMessageBody()                        { return parsedMessageBody; }
    public void setParsedMessageBody(String parsedMessageBody)  { this.parsedMessageBody = parsedMessageBody; }
    public String getSysNote()                                  { return sysNote; }
    public void setSysNote(String sysNote)                      { this.sysNote = sysNote; }
}