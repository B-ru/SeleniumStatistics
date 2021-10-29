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
    final static int    DELAY                   = 5000;
    final static int    CLICK_DELAY             = 350;
    final static String HEADER                  = "person;theme;timestamp;customtimestamp;comment;sys_notes";
    final static String WAIT_ELEMENT_XPATH      = "//div[contains(@class,'_lvv_12')]";
    final static String FIRST_MESSAGE_XPATH     = "//*[contains(@class,'_lvv_12')]//div[@data-convid]";
    final static String NEXT_MESSAGE_XPATH      = "//div[contains(@aria-selected,'true')]/following-sibling::div[@data-convid]";
    final static String FORMAT                  = "\"%s\";\"%s\";%s;%s;%s;%s";
    final static String AUTHOR_XPATH            = "//div[contains(@class,'_rp_x5')]//span[contains(@class,'_pe_l')]/span[text()]";
    final static String AUTHOR_PATTERN          = "^([а-яА-ЯёЁ ]+) <";
    final static String THEME_XPATH             = "//div[contains(@class,'_rp_m5')]//span[contains(@autoid,'_rp_B')]";
    final static String GRAND_PARENT_XPATH      = "./../../../../../../../../../../../../..";
    final static String MESSAGE_BODY_XPATH      = "//div[contains(@id,'Item.MessageNormalizedBody')]/div/div[1]";
    final static String BODY_DATA_PATTERN       = "[дД]ата(( )?:( )?)?([0-9\\.\\/]+).+[вВ]ремя[а-яА-Я :]+:?([0-9.:\\-]+) ([кК]ом[а-я]+рий(( )?:( )?)?([а-яА-Я0-9.,\\- ]+)[сС] уваж)?";
    final static String EMPTY_COMMENT_FORMAT    = "^[-]+( )?";
    final static String EMPTY_FIELD_FILLER      = "";
    final static String CUSTOM_TIMESTAMP_PATTERN= "([0-9]([0-9])?[\\.\\/][0-9]([0-9])?)[\\.\\/]([0-9][0-9]([0-9][0-9])?) ([0-9]([0-9])?:[0-9][0-9])";
    final static String CUSTOM_TIMESTAMP_ERROR  = "неверный формат даты/времени";
}
