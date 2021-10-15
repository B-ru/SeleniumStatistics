package main.ru.ischenko.SeleniumStatistics;
import static main.ru.ischenko.SeleniumStatistics.Definitions.DELAY_MULTIPLIER;
import static main.ru.ischenko.SeleniumStatistics.Definitions.PATH_TO_DRIVER;
public class Application {
    public static void main(String[] args) throws Exception {
        Driver driver = new Driver( args[PATH_TO_DRIVER] );
        driver.Login( args );
        driver.Wait( args[DELAY_MULTIPLIER]);
        new MailMessages( driver.FindMailRecordsDiv() ).Print();
        driver.Close();
     }
}