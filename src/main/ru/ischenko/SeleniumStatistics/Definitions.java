package main.ru.ischenko.SeleniumStatistics;
public class Definitions {
    // indexes
    final static int    PATH_TO_DRIVER_IDX      = 0;
    final static int    URL_IDX                 = 1;
    final static int    LOGIN_IDX               = 2;
    final static int    PASSWD_IDX              = 3;
    final static int    DELAY_MULTIPLIER_IDX    = 4;
    // Matches groups indexes:
    final static int    DATE_IDX                = 4;
    final static int    TIME_IDX                = 5;
    final static int    COMMENT_IDX             = 10;
    //regular values
    final static int    DELAY                   = 10000;
    final static String WAIT_ELEMENT_XPATH      = "//div[contains(@class,'_lvv_12')]";
    final static String MESSAGES_LIST_XPATH     = "//*[contains(@class,'_lvv_12')]//div[@data-convid]";
    final static String FIRST_MESSAGE_XPATH     = "//*[contains(@class,'_lvv_12')]//div[@data-convid]";
    final static String NEXT_MESSAGE_XPATH      = "//div[contains(@aria-selected,'true')]/following-sibling::div[@data-convid]";
    final static String FORMAT                  = "\"%s\";\"%s\";%s;%s;%s;%s";
    final static String DATE_OUTPUT_FORMAT      = "%s %s";
    final static String RECENT_TIME_FORMAT      = "([0-9]{1,2}:[0-9]{2})$";
    final static String FOUR_DAYS_TIME_FORMAT   = "^(Пн|Вт|Ср|Чт|Пт|Сб|Вс) ([0-9]{1,2}:[0-9]{2})$";
    final static String WEEK_TIME_FORMAT        = "^(Пн|Вт|Ср|Чт|Пт|Сб|Вс) ([0-9]{2}.[0-9]{2})$";
    final static String MONTH_TIME_FORMAT       = "^(\\d{2}.\\d{2}.\\d{4})$";
    final static String ERROR_FILLER            = "?";
    final static String AUTHOR_XPATH            = "//div[contains(@id,'%s')]//span[contains(@autoid,'_lvv_f')]";
    final static String THEME_XPATH             = "//div[contains(@id,'%s')]//span[contains(@autoid,'_lvv_g')]";
    final static String TIMESTAMP_XPATH         = "//div[contains(@id,'%s')]/div/div/span[text()]";
    final static String GRAND_PARENT_XPATH      = "./../../../../../../../../../../../../..";
    final static String INNER_TIME_DATE_XPATH   = "//div[contains(@class,'_rp_f8')]/div/span[text()]";
    final static String MESSAGE_BODY_XPATH      = "//div[contains(@id,'Item.MessageNormalizedBody')]/div/div[1]";
    final static String BODY_DATA_PATTERN       = "[дД]ата(( )?:( )?)?([0-9\\.\\/]+).+[вВ]ремя[а-яА-Я :]+:?([0-9.:\\-]+) ([кК]ом[а-я]+рий(( )?:( )?)?([а-яА-Я0-9.,\\- ]+)[сС] уваж)?";
    final static String EMPTY_COMMENT_FORMAT    = "^[-]+( )?";
    final static String EMPTY_FIELD_FILLER      = "";
    final static String CUSTOM_TIMESTAMP_PATTERN= "([0-9]([0-9])?[\\.\\/][0-9]([0-9])?)[\\.\\/]([0-9][0-9]([0-9][0-9])?) [0-9]([0-9])?:[0-9][0-9]";
    final static String CUSTOM_TIMESTAMP_ERROR  = "неверный формат даты/времени";
}
