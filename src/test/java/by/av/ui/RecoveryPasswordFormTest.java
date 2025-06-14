package by.av.ui;

import by.av.ui.pages.home.loginform.LoginFormLocator;
import by.av.ui.pages.home.loginform.forgotpasswordform.RecoveryPasswordForm;
import by.av.ui.pages.home.loginform.forgotpasswordform.RecoveryPasswordFormLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static by.av.utils.Utils.generatePhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

public class RecoveryPasswordFormTest extends WithLoginSetUp{

    private RecoveryPasswordForm recoveryPasswordForm;

    @BeforeEach
    public void setUp() {
        openLoginForm();
        loginForm.clickButtonRecoveryPassword();
        waitOfElement(RecoveryPasswordFormLocator.TITLE_RECOVERY_PASSWORD_FORM);
        recoveryPasswordForm = new RecoveryPasswordForm();
    }

    @Test
    public void checkTitleTextButtonsLabelAttributeOfRecoveryByPhoneNumberNames() {
        assertAll(
                () -> assertEquals("Вход", recoveryPasswordForm.getButtonLoginText()),
                () -> assertEquals("Запрос на восстановление пароля", recoveryPasswordForm.getTitleRecoveryPasswordFormText()),
                () -> assertEquals("по телефону", recoveryPasswordForm.getButtonRecoveryByPhoneNumberText()),
                () -> assertEquals("true", recoveryPasswordForm.getButtonRecoveryByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals("почте", recoveryPasswordForm.getButtonRecoveryByEmailText()),
                () -> assertEquals("false", recoveryPasswordForm.getButtonRecoveryByEmailAttributeAriaSelectedValue()),
                () -> assertEquals("Укажите телефон, который вы указывали при регистрации. После подтверждения номера телефона вы сможете ввести новый пароль.", recoveryPasswordForm.getTextRecoveryByPhoneNumber()),
                () -> assertEquals("Телефон", recoveryPasswordForm.getLabelPhoneNumberText()),
                () -> assertEquals("Отправить", recoveryPasswordForm.getButtonSendByPhoneNumberText()),
                () -> assertFalse(recoveryPasswordForm.isButtonSendByPhoneNumberEnabled())
        );
    }

    @Test
    public void checkButtonSendByPhoneNumberEnabledAfterInputInvalidPhoneNumber() {
        recoveryPasswordForm.inputPhoneNumber("111111111");
        assertFalse(recoveryPasswordForm.isButtonSendByPhoneNumberEnabled());
    }

    @Test
    public void checkButtonSendByPhoneNumberEnabledAfterInputPhoneNumber() {
        recoveryPasswordForm.inputPhoneNumber(generatePhoneNumber());
        assertTrue(recoveryPasswordForm.isButtonSendByPhoneNumberEnabled());
    }

    @Test
    public void checkClickButtonSendByPhoneNumberEnabledAfterInputPhoneNumber() {
        checkButtonSendByPhoneNumberEnabledAfterInputPhoneNumber();
        recoveryPasswordForm.clickButtonSendByPhoneNumber();
        assertEquals("Такого пользователя у нас нет. Проверьте указанный номер", loginForm.getErrorMessageText());
    }

    @Test
    public void checkReturnToRecoveryByPhoneNumberAfterGoToRecoveryByEmail() {
        waitOfElement(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_EMAIL);
        recoveryPasswordForm.clickButtonRecoveryByEmail();
        assertAll(
                () -> assertEquals("false", recoveryPasswordForm.getButtonRecoveryByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals("true", recoveryPasswordForm.getButtonRecoveryByEmailAttributeAriaSelectedValue())
        );
        recoveryPasswordForm.clickButtonRecoveryByPhoneNumber();
        assertAll(
                () -> assertEquals("true", recoveryPasswordForm.getButtonRecoveryByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals("false", recoveryPasswordForm.getButtonRecoveryByEmailAttributeAriaSelectedValue())
        );
    }

    @Test
    public void checkTitleTextButtonsLabelAttributeOfRecoveryByEmailNames() {
        recoveryPasswordForm.clickButtonRecoveryByEmail();
        assertAll(
                () -> assertEquals("Вход", recoveryPasswordForm.getButtonLoginText()),
                () -> assertEquals("Запрос на восстановление пароля", recoveryPasswordForm.getTitleRecoveryPasswordFormText()),
                () -> assertEquals("по телефону", recoveryPasswordForm.getButtonRecoveryByPhoneNumberText()),
                () -> assertEquals("false", recoveryPasswordForm.getButtonRecoveryByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals("почте", recoveryPasswordForm.getButtonRecoveryByEmailText()),
                () -> assertEquals("true", recoveryPasswordForm.getButtonRecoveryByEmailAttributeAriaSelectedValue()),
                () -> assertEquals("Укажите адрес электронной почты, который вы указывали при регистрации. На него мы отправим инструкции по восстановлению пароля.", recoveryPasswordForm.getTextRecoveryByPEmail()),
                () -> assertEquals("Электронная почта", recoveryPasswordForm.getLabelEmailText()),
                () -> assertEquals("Отправить", recoveryPasswordForm.getButtonSendByEmailText()),
                () -> assertFalse(recoveryPasswordForm.isButtonSendByEmailEnabled())
        );
    }

    @Test
    public void checkClickButtonSendByEmailAfterInputAnyKeyInEmailField() {
        waitOfElement(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_EMAIL);
        recoveryPasswordForm.clickButtonRecoveryByEmail();
        recoveryPasswordForm.inputEmail("1");
        waitOfElement(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_EMAIL);
        recoveryPasswordForm.clickButtonSendByEmail();
        assertAll(
                () -> assertTrue(recoveryPasswordForm.isButtonSendByEmailEnabled()),
                () -> assertEquals("Введите почту полностью. Например, info@av.by", loginForm.getErrorMessageText())
        );
    }

    @Test
    public void checkClickButtonSendByEmailAfterInputEmail() {
        waitOfElement(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_EMAIL);
        recoveryPasswordForm.clickButtonRecoveryByEmail();
        recoveryPasswordForm.inputEmail(faker.internet().emailAddress());
        recoveryPasswordForm.clickButtonSendByEmail();
        assertAll(
                () -> assertTrue(recoveryPasswordForm.isButtonSendByEmailEnabled()),
                () -> assertEquals("Такого пользователя у нас нет. Проверьте указанную почту", loginForm.getErrorMessageText())
        );
    }

    @Test
    public void checkClickButtonLoginReturnToLoginForm() {
        waitOfElement(RecoveryPasswordFormLocator.BUTTON_LOGIN);
        recoveryPasswordForm.clickButtonLogin();
        waitOfElement(LoginFormLocator.TITLE_LOGIN_FORM);
        assertEquals("Вход", loginForm.getTitleText());
    }
}
