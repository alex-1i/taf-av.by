package by.av.ui.pages.home.loginform.recoverypasswordform;

import by.av.ui.pages.home.loginform.LoginFormLocator;

public class RecoveryPasswordFormLocator {

    public static final String RECOVERY_FORM = "Запрос на восстановление пароля";
    public static final String BUTTON_LOGIN = "//button[@title=\"Вход\"]";
    public static final String TITLE_RECOVERY_PASSWORD_FORM = "//div[text()=\"Запрос на восстановление пароля\"]";
    public static final String BUTTON_RECOVERY_BY_PHONE_NUMBER = LoginFormLocator.getButtonByPhoneNumberOrEmailLocator(RECOVERY_FORM, LoginFormLocator.BY_PHONE_NUMBER_FORM);
    public static final String TEXT_RECOVERY_BY_PHONE_NUMBER = getTextRecoveryLocator(LoginFormLocator.BY_PHONE_NUMBER_FORM);
    public static final String LABEL_PHONE_NUMBER = "//label[@for=\"forgotEmail\"][text()=\"Телефон\"]";
    public static final String INPUT_PHONE_NUMBER = "//input[@id=\"phone\"]";
    public static final String BUTTON_SEND_BY_PHONE_NUMBER = getButtonSendLocator(LoginFormLocator.BY_PHONE_NUMBER_FORM);
    public static final String BUTTON_RECOVERY_BY_EMAIL = LoginFormLocator.getButtonByPhoneNumberOrEmailLocator(RECOVERY_FORM, LoginFormLocator.BY_EMAIL_FORM);
    public static final String TEXT_RECOVERY_BY_EMAIL = getTextRecoveryLocator(LoginFormLocator.BY_EMAIL_FORM);
    public static final String LABEL_EMAIL = "//label[@for=\"forgotEmail\"][text()=\"Электронная почта\"]";
    public static final String INPUT_EMAIL = "//input[@id=\"email\"]";
    public static final String BUTTON_SEND_BY_EMAIL = getButtonSendLocator(LoginFormLocator.BY_EMAIL_FORM);

    public static String getTextRecoveryLocator(String parent) {
        return "//div[@aria-labelledby=\"" + parent + "\"]//div[@class='auth__text']";
    }

    public static String getButtonSendLocator(String parent) {
        return "//div[contains(@class,\"drawer__slide--active\")]//div[@aria-labelledby=\"" + parent + "\"]//button[contains(@class,\"button--primary\")]";
    }
}
