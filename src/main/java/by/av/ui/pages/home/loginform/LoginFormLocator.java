package by.av.ui.pages.home.loginform;

public class LoginFormLocator {

    public static final String LOGIN_FORM = "Вход";
    public static final String BY_PHONE_NUMBER_FORM = "по телефону";
    public static final String BY_EMAIL_FORM = "почте";
    public static final String LOGIN_FORM_OPENED = "//div[@class=\"drawer drawer--animated drawer--light drawer--opened\"]";
    public static final String LOGIN_FORM_CLOSED = "//div[@class=\"drawer drawer--animated drawer--light\"]";
    public static final String BUTTON_CROSS = "//button[@class=\"drawer__close\"]";
    public static final String TITLE_LOGIN_FORM = "//div[text()=\"Вход\"]";
    public static final String BUTTON_LOGIN_BY_PHONE_NUMBER = getButtonByPhoneNumberOrEmailLocator(LOGIN_FORM, BY_PHONE_NUMBER_FORM);
    public static final String LABEL_PHONE_NUMBER = "//label[@for=\"authPhone\"]";
    public static final String INPUT_PHONE_NUMBER = "//input[@id=\"authPhone\"]";
    public static final String LABEL_PASSWORD_FOR_PHONE_NUMBER = "//label[@for=\"passwordPhone\"]";
    public static final String BUTTON_INVERTER = "//div[text()=\"Вход\"]/parent::div//button[@class=\"toggle-password-button\"]";
    public static final String INPUT_PASSWORD_FOR_PHONE_NUMBER = "//input[@id=\"passwordPhone\"]";
    public static final String BUTTON_LOGIN_BY_EMAIL_OR_LOGIN = "//button[text()=\"почте или логину\"]";
    public static final String LABEL_EMAIL_OR_LOGIN = "//label[@for=\"authLogin\"]";
    public static final String INPUT_EMAIL_OR_LOGIN = "//input[@id=\"authLogin\"]";
    public static final String LABEL_PASSWORD_FOR_EMAIL_OR_LOGIN = "//label[@for=\"loginPassword\"]";
    public static final String INPUT_PASSWORD_FOR_EMAIL_OR_LOGIN = "//input[@id=\"loginPassword\"]";
    public static final String BUTTON_RECOVERY_PASSWORD = "//button[@class=\"button button--link button--small\"]";
    public static final String BUTTON_LOGIN = "//button[@class=\"button button--action\"]";
    public static final String BUTTON_REGISTRATION = getButtonRegistrationLocator(LOGIN_FORM);
    public static final String ERROR_MESSAGE = "//div[@class=\"error-message\"]";

    public static String getButtonByPhoneNumberOrEmailLocator(String parent, String phoneOrEmail) {
        return "//div[text()=\"" + parent + "\"]/parent::div//button[text()=\""+ phoneOrEmail + "\"]";
    }

    public static String getButtonRegistrationLocator(String parent) {
        return "//div[text()=\"" + parent + "\"]/ancestor::div[contains(@class, 'drawer__slide')]//button[@class=\"drawer__slide-toggle\"]";
    }
}
