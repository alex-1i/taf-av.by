package by.av.ui.pages.home.loginform.registrationform;

public class RegistrationFormLocator {

    public static final String TITLE_REGISTRATION = "//div[text()=\"Регистрация\"]";
    public static final String BUTTON_CHOOSE_REGISTRATION_BY_PHONE_NUMBER = "(//button[text()=\"по телефону\"])[1]";
    public static final String LABEL_NAME_BY_PHONE_NUMBER = "//label[@for=\"namePhone\"]";
    public static final String INPUT_NAME_BY_PHONE_NUMBER = "//input[@id=\"namePhone\"]";
    public static final String LABEL_PHONE_NUMBER = "//label[@for=\"phone\"]";
    public static final String INPUT_PHONE_NUMBER = "//input[@id=\"registrationPhone\"]";
    public static final String LABEL_PASSWORD_FOR_PHONE_NUMBER = "//label[@for=\"registrationPasswordPhone\"]";
    public static final String BUTTON_INVERTER_FOR_PHONE_NUMBER = "(//button[@class=\"toggle-password-button\"])[1]";
    public static final String INPUT_PASSWORD_FOR_PHONE_NUMBER = "//input[@id=\"registrationPasswordPhone\"]";
    public static final String WARN_MESSAGE_FOR_PASSWORD_FOR_PHONE_NUMBER = "(//small[text()=\"Не короче 8 символов и только латиница и цифры\"])[1]";
    public static final String BUTTON_REGISTRATION_BY_PHONE_NUMBER = "(//button[@class=\"button button--primary\"])[1]";
    public static final String BUTTON_CHOOSE_REGISTRATION_BY_EMAIL = "(//button[text()=\"почте\"])[1]";
    public static final String LABEL_NAME_BY_EMAIL = "//label[@for=\"name\"]";
    public static final String INPUT_NAME_BY_EMAIL = "//input[@id=\"name\"]";
    public static final String LABEL_EMAIL = "//label[@for=\"regEmail\"]";
    public static final String INPUT_EMAIL = "//input[@id=\"regEmail\"]";
    public static final String LABEL_PASSWORD_FOR_EMAIL = "//label[@for=\"regPassword\"]";
    public static final String BUTTON_INVERTER_FOR_EMAIL = "(//button[@class=\"toggle-password-button\"])[2]";
    public static final String INPUT_PASSWORD_FOR_EMAIL = "//input[@id=\"regPassword\"]";
    public static final String WARN_MESSAGE_FOR_PASSWORD_FOR_EMAIL = "(//small[text()=\"Не короче 8 символов и только латиница и цифры\"])[2]";
    public static final String BUTTON_REGISTRATION_BY_EMAIL = "(//button[@class=\"button button--primary\"])[2]";
    public static final String BUTTON_LOGIN = "//button[@class=\"drawer__slide-toggle\"]";

    public static final String ERROR_MESSAGE_FOR_NAME_BY_PHONE_NUMBER = "(//div[@class=\"error-message\"])[1]";
    public static final String ERROR_MESSAGE_FOR_PASSWORD_FOR_PHONE_NUMBER = "(//div[@class=\"error-message\"])[2]";
    public static final String ERROR_MESSAGE_FOR_NAME_BY_EMAIL = "(//div[@class=\"error-message\"])[3]";
    public static final String ERROR_MESSAGE_FOR_EMAIL = "(//div[@class=\"error-message\"])[4]";
    public static final String ERROR_MESSAGE_FOR_PASSWORD_FOR_EMAIL = "(//div[@class=\"error-message\"])[5]";
}
