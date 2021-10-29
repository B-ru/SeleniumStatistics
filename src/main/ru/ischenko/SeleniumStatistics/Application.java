package main.ru.ischenko.SeleniumStatistics;
import static main.ru.ischenko.SeleniumStatistics.Definitions.*;
public class Application {
    public static void setup(String[] args, Driver driver) throws  Exception{
        driver.Login( args );
        driver.Wait( args[DELAY_MULTIPLIER_IDX]);
    }
    public static void printAndArchiveMails(Driver driver){
        System.out.println(HEADER);
        while (driver.hasNext()) {
            Message message = new Message((driver.next()));
            System.out.println( message );
            //message.archive();        -- not yet
            //driver.resetMessagePointer();
        }
    }
    public static void main(String[] args) throws Exception {
        Driver driver = new Driver( args[PATH_TO_DRIVER_IDX] );
        setup(args, driver);
        printAndArchiveMails(driver);
        driver.Close();
     }
}