package main.ru.ischenko.SeleniumStatistics;
import static main.ru.ischenko.SeleniumStatistics.Definitions.DELAY_MULTIPLIER_IDX;
import static main.ru.ischenko.SeleniumStatistics.Definitions.PATH_TO_DRIVER_IDX;
public class Application {
    public static void main(String[] args) throws Exception {
        Driver driver = new Driver( args[PATH_TO_DRIVER_IDX] );
        driver.Login( args );
        driver.Wait( args[DELAY_MULTIPLIER_IDX]);
        new MailMessages( driver.FindMailRecordsDiv() ).Print();
        driver.Close();
     }
}