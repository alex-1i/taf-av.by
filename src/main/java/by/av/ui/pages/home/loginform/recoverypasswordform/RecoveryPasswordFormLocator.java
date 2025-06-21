package by.av.ui.pages.home.loginform.recoverypasswordform;

public class RecoveryPasswordFormLocator {

    public static final String BUTTON_LOGIN = "//button[@title=\"Вход\"]";
    public static final String TITLE_RECOVERY_PASSWORD_FORM = "//div[text()=\"Запрос на восстановление пароля\"]";
    public static final String BUTTON_RECOVERY_BY_PHONE_NUMBER = "(//button[text()=\"по телефону\"])[2]";
    public static final String TEXT_RECOVERY_BY_PHONE_NUMBER = "(//div[@class=\"auth__text\"])[2]";
    public static final String LABEL_PHONE_NUMBER = "//label[@for=\"forgotEmail\"][text()=\"Телефон\"]";
    public static final String INPUT_PHONE_NUMBER = "//input[@id=\"phone\"]";
    public static final String BUTTON_SEND_BY_PHONE_NUMBER = "(//button[@class=\"button button--primary\"])[3]";
    public static final String BUTTON_RECOVERY_BY_EMAIL = "(//button[text()=\"почте\"])[2]";
    public static final String TEXT_RECOVERY_BY_EMAIL = "(//div[@class=\"auth__text\"])[3]";
    public static final String LABEL_EMAIL = "//label[@for=\"forgotEmail\"][text()=\"Электронная почта\"]";
    public static final String INPUT_EMAIL = "//input[@id=\"email\"]";
    public static final String BUTTON_SEND_BY_EMAIL = "(//button[@class=\"button button--primary\"])[4]";
}
