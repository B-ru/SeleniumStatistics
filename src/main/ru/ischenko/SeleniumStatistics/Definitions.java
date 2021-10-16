package main.ru.ischenko.SeleniumStatistics;
public class Definitions {
    // indexes
    final static int    PATH_TO_DRIVER_IDX      = 0;
    final static int    URL_IDX                 = 1;
    final static int    LOGIN_IDX               = 2;
    final static int    PASSWD_IDX              = 3;
    final static int    DELAY_MULTIPLIER_IDX    = 4;
    //regular values
    final static int    DELAY                   = 10000;
    final static int    CLICK_DELAY             = 50;
    final static String WAIT_ELEMENT_XPATH      = "//div[contains(@class,'_lvv_11')]/div/div[contains(@tabindex,'-1')]";
    final static String MESSAGES_LIST_XPATH     = "//div[contains(@autoid,'_lvv_9')]/div";
    final static String FORMAT                  = "%-33.33s %-29.29s %16.16s\n";
    final static String DATE_OUTPUT_FORMAT      = "%10.10s %5.5s";
    final static String RECENT_TIME_FORMAT      = "([0-9]{1,2}:[0-9]{2})$";
    final static String FOUR_DAYS_TIME_FORMAT   = "^(Пн|Вт|Ср|Чт|Пт|Сб|Вс) ([0-9]{1,2}:[0-9]{2})$";
    final static String WEEK_TIME_FORMAT        = "^(Пн|Вт|Ср|Чт|Пт|Сб|Вс) ([0-9]{2}.[0-9]{2})$";
    final static String MONTH_TIME_FORMAT       = "^(\\d{2}.\\d{2}.\\d{4})$";
    final static String ERROR_FILLER            = "?";
    final static String AUTHOR_XPATH            = "//div[contains(@id,'%s')]//span[contains(@autoid,'_lvv_5')]";
    final static String THEME_XPATH             = "//div[contains(@id,'%s')]//span[contains(@autoid,'_lvv_6')]";
    final static String TIMESTAMP_XPATH         = "//div[contains(@id,'%s')]/div/div/span[text()]";
    final static String GRAND_PARENT_XPATH      = "./../../../../../../../../../../../../..";
    final static String INNER_TIME_DATE_XPATH   = "//div[contains(@class,'_rp_f8')]/div/span[text()]";
    final static String MESSAGE_SELECTOR_XPATH  ="//div[contains(@id,'%s')]/div/button";
}
