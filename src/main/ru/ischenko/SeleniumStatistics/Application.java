package main.ru.ischenko.SeleniumStatistics;
import static main.ru.ischenko.SeleniumStatistics.Defenitions.DELAYMULTIPLIER;
import static main.ru.ischenko.SeleniumStatistics.Defenitions.PATHTODRIVER;
public class Application {
    public static void main(String[] args) throws Exception {
        Driver driver = new Driver( args[PATHTODRIVER] );
        driver.Login( args );
        driver.Wait( args[DELAYMULTIPLIER]);
        new MailMessages( driver.FindMailRecordsDiv() ).Print();
        driver.Close();
     }
}