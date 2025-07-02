package by.av.ui.pages.home.loginform.registrationform;

import by.av.ui.pages.home.loginform.LoginFormLocator;
import by.av.ui.pages.home.loginform.recoverypasswordform.RecoveryPasswordFormLocator;

public class RegistrationFormLocator {

    public static final String REGISTRATION_FORM = "Регистрация";
    public static final String TITLE_REGISTRATION = "//div[text()=\"Регистрация\"]";
    public static final String BUTTON_CHOOSE_REGISTRATION_BY_PHONE_NUMBER = LoginFormLocator.getButtonByPhoneNumberOrEmailLocator(REGISTRATION_FORM, LoginFormLocator.BY_PHONE_NUMBER_FORM);
    public static final String LABEL_NAME_BY_PHONE_NUMBER = "//label[@for=\"namePhone\"]";
    public static final String INPUT_NAME_BY_PHONE_NUMBER = "//input[@id=\"namePhone\"]";
    public static final String LABEL_PHONE_NUMBER = "//label[@for=\"phone\"]";
    public static final String INPUT_PHONE_NUMBER = "//input[@id=\"registrationPhone\"]";
    public static final String LABEL_PASSWORD_FOR_PHONE_NUMBER = "//label[@for=\"registrationPasswordPhone\"]";
    public static final String BUTTON_INVERTER_FOR_PHONE_NUMBER = getButtonInvertOnRegistrationForm(LoginFormLocator.BY_PHONE_NUMBER_FORM);
    public static final String INPUT_PASSWORD_FOR_PHONE_NUMBER = "//input[@id=\"registrationPasswordPhone\"]";
    public static final String WARN_MESSAGE_FOR_PASSWORD_FOR_PHONE_NUMBER = getWarnMessageForPassword(LoginFormLocator.BY_PHONE_NUMBER_FORM);
    public static final String BUTTON_REGISTRATION_BY_PHONE_NUMBER = RecoveryPasswordFormLocator.getButtonSendLocator(LoginFormLocator.BY_PHONE_NUMBER_FORM);
    public static final String BUTTON_CHOOSE_REGISTRATION_BY_EMAIL = LoginFormLocator.getButtonByPhoneNumberOrEmailLocator(REGISTRATION_FORM, LoginFormLocator.BY_EMAIL_FORM);
    public static final String LABEL_NAME_BY_EMAIL = "//label[@for=\"name\"]";
    public static final String INPUT_NAME_BY_EMAIL = "//input[@id=\"name\"]";
    public static final String LABEL_EMAIL = "//label[@for=\"regEmail\"]";
    public static final String INPUT_EMAIL = "//input[@id=\"regEmail\"]";
    public static final String LABEL_PASSWORD_FOR_EMAIL = "//label[@for=\"regPassword\"]";
    public static final String BUTTON_INVERTER_FOR_EMAIL = getButtonInvertOnRegistrationForm(LoginFormLocator.BY_EMAIL_FORM);
    public static final String INPUT_PASSWORD_FOR_EMAIL = "//input[@id=\"regPassword\"]";
    public static final String WARN_MESSAGE_FOR_PASSWORD_FOR_EMAIL = getWarnMessageForPassword(LoginFormLocator.BY_EMAIL_FORM);
    public static final String BUTTON_REGISTRATION_BY_EMAIL = RecoveryPasswordFormLocator.getButtonSendLocator(LoginFormLocator.BY_EMAIL_FORM);
    public static final String BUTTON_LOGIN = "//button[@class=\"drawer__slide-toggle\"]";

    public static final String ERROR_MESSAGE_FOR_NAME_BY_PHONE_NUMBER = getErrorMessage(LoginFormLocator.BY_PHONE_NUMBER_FORM, "namePhone");
    public static final String ERROR_MESSAGE_FOR_PASSWORD_FOR_PHONE_NUMBER = getErrorMessage(LoginFormLocator.BY_PHONE_NUMBER_FORM, "registrationPasswordPhone");
    public static final String ERROR_MESSAGE_FOR_NAME_BY_EMAIL = getErrorMessage(LoginFormLocator.BY_EMAIL_FORM, "name");
    public static final String ERROR_MESSAGE_FOR_EMAIL = getErrorMessage(LoginFormLocator.BY_EMAIL_FORM, "regEmail");
    public static final String ERROR_MESSAGE_FOR_PASSWORD_FOR_EMAIL = getErrorMessage(LoginFormLocator.BY_EMAIL_FORM, "regPassword");

    public static String getButtonInvertOnRegistrationForm(String parent) {
        return "//div[@aria-labelledby=\"" + parent + "\"]//button[contains(@class, \"toggle-password-button\")]";
    }

    public static String getWarnMessageForPassword(String parent) {
        return "//div[@aria-labelledby=\"" + parent + "\"]//small[text()=\"Не короче 8 символов и только латиница и цифры\"]";
    }

    public static String getErrorMessage(String parent, String id) {
        return  "//div[@aria-labelledby=\"" + parent + "\"]//input[@id=\"" + id + "\"]/following-sibling::div[@class=\"error-message\"]";
    }
}
