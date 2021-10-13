package main.ru.ischenko.SeleniumStatistics;
public class Application {
    public static void main(String[] args) throws Exception {
        Driver driver = new Driver();
        driver.Login( args );
        driver.Wait();
        new MailMessages( driver.FindMailRecordsDiv() ).Print();
        driver.Close();
     }
}