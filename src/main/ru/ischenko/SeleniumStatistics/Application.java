package main.ru.ischenko.SeleniumStatistics;
import static main.ru.ischenko.SeleniumStatistics.Definitions.*;
public class Application {
    public static void setup(String[] args, MailHeap mailHeap) throws  Exception{
        mailHeap.Login( args );
        mailHeap.Wait( args[DELAY_MULTIPLIER_IDX]);
    }
    public static void printAndArchiveMails(MailHeap mailHeap){
        System.out.println(HEADER);
        while (mailHeap.hasNext()) {
            Message message = new Message((mailHeap.next()));
            System.out.println( message );
            message.archive();
            mailHeap.resetMessagePointer();
        }
    }
    public static void main(String[] args) throws Exception {
        MailHeap mailHeap = new MailHeap( args[PATH_TO_DRIVER_IDX] );
        setup(args, mailHeap);
        printAndArchiveMails(mailHeap);
        mailHeap.Close();
     }
}