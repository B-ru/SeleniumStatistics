package main.ru.ischenko.SeleniumStatistics;
public class Definitions {
    // indexes
    final static int    PATH_TO_DRIVER          = 0;
    final static int    SITE                    = 1;
    final static int    LOGIN                   = 2;
    final static int    PASSWD                  = 3;
    final static int    DELAY_MULTIPLIER        = 4;
    //regular values
    final static int    DELAY                   = 10000;
    final static int    CLICK_DELAY             = 50;
    final static String FORMAT                  = "%-39.39s %-29.29s %16.16s\n";
    final static String DATE_OUTPUT_FORMAT      = "%10.10s %5.5s";
    final static String TODAY_TIME_FORMAT       = "([0-9]{1,2}:[0-9]{2})$";
    final static String FOUR_DAYS_TIME_FORMAT   = "^(Пн|Вт|Ср|Чт|Пт|Сб|Вс) ([0-9]{1,2}:[0-9]{2})$";
    final static String ONE_WEEK_TIME_FORMAT    = "^(Пн|Вт|Ср|Чт|Пт|Сб|Вс) ([0-9]{2}.[0-9]{2})$";
    final static String MORE_THAN_WEEK_FORMAT   = "^(\\d{2}.\\d{2}.\\d{4})$";
    final static String ERROR_FILER             = "?";
}
