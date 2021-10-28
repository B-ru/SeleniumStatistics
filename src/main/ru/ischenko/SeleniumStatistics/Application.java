package main.ru.ischenko.SeleniumStatistics;
import static main.ru.ischenko.SeleniumStatistics.Definitions.*;
public class Application {
    public static void main(String[] args) throws Exception {
        Driver driver = new Driver( args[PATH_TO_DRIVER_IDX] );
        driver.Login( args );
        driver.Wait( args[DELAY_MULTIPLIER_IDX]);
        System.out.println("person;theme;timestamp;customtimestamp;comment;sys_notes");
        while(driver.hasNext()){
            System.out.println( new MessageParser( driver.next() ) );
        }
        driver.Close();
     }
}