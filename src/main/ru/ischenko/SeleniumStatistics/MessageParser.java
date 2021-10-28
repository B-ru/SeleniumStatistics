package main.ru.ischenko.SeleniumStatistics;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        WebElement grandContainer = getMessage().findElement(By.xpath(GRAND_PARENT_XPATH));
        String rawAuthorData = grandContainer.findElement(By.xpath(AUTHOR_XPATH)).getText();
        Pattern pattern = Pattern.compile(AUTHOR_PATTERN);
        Matcher matcher = pattern.matcher(rawAuthorData);
        if(matcher.find()) return matcher.group(1);
        else return rawAuthorData;
    }
    public String parseTheme()    {
        WebElement grandContainer = getMessage().findElement(By.xpath(GRAND_PARENT_XPATH));
        return grandContainer.findElement(By.xpath(THEME_XPATH)).getText();
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
                    return (matcher.group(1) + ".20" + matcher.group(4)).replace("/", ".") + " " + matcher.group(6);
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