package by.av.ui;

import by.av.ui.expectedMessages.ExpectedMessages;
import by.av.ui.pages.home.HomePageLocator;
import by.av.ui.pages.home.loginform.LoginFormLocator;
import by.av.ui.pages.home.loginform.recoverypasswordform.RecoveryPasswordForm;
import by.av.ui.pages.home.loginform.recoverypasswordform.RecoveryPasswordFormLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static by.av.utils.Utils.generateInvalidPhoneNumberOrEmail;
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
                () -> assertEquals(ExpectedMessages.TITLE_LOGIN, recoveryPasswordForm.getButtonLoginText()),
                () -> assertEquals(ExpectedMessages.TITLE_RECOVERY_PASSWORD, recoveryPasswordForm.getTitleRecoveryPasswordFormText()),
                () -> assertEquals(ExpectedMessages.BUTTON_BY_PHONE_NUMBER, recoveryPasswordForm.getButtonRecoveryByPhoneNumberText()),
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, recoveryPasswordForm.getButtonRecoveryByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.BUTTON_BY_EMAIL, recoveryPasswordForm.getButtonRecoveryByEmailText()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, recoveryPasswordForm.getButtonRecoveryByEmailAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.TEXT_RECOVERY_BY_PHONE_NUMBER, recoveryPasswordForm.getTextRecoveryByPhoneNumber()),
                () -> assertEquals(ExpectedMessages.LABEL_PHONE, recoveryPasswordForm.getLabelPhoneNumberText()),
                () -> assertEquals(ExpectedMessages.BUTTON_SEND, recoveryPasswordForm.getButtonSendByPhoneNumberText()),
                () -> assertFalse(recoveryPasswordForm.isButtonSendByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_SEND))
        );
    }

    @Test
    public void checkButtonSendByPhoneNumberEnabledAfterInputInvalidPhoneNumber() {
        recoveryPasswordForm.inputPhoneNumber(generateInvalidPhoneNumberOrEmail());
        waitOfElement(RecoveryPasswordFormLocator.BUTTON_SEND_BY_PHONE_NUMBER);
        assertFalse(recoveryPasswordForm.isButtonSendByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_SEND));
    }

    @Test
    public void checkButtonSendByPhoneNumberEnabledAfterInputPhoneNumber() {
        recoveryPasswordForm.inputPhoneNumber(generatePhoneNumber());
        assertTrue(recoveryPasswordForm.isButtonSendByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonEnabled(ExpectedMessages.BUTTON_SEND));
    }

    @Test
    public void checkClickButtonSendByPhoneNumberEnabledAfterInputPhoneNumber() {
        checkButtonSendByPhoneNumberEnabledAfterInputPhoneNumber();
        recoveryPasswordForm.clickButtonSendByPhoneNumber();
        waitOfElement(LoginFormLocator.ERROR_MESSAGE);
        assertEquals(ExpectedMessages.ERROR_MESSAGE_RECOVERY_BY_PHONE_NUMBER, loginForm.getErrorMessageText());
    }

    @Test
    public void checkReturnToRecoveryByPhoneNumberAfterGoToRecoveryByEmail() {
        waitOfElement(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_EMAIL);
        recoveryPasswordForm.clickButtonRecoveryByEmail();
        assertAll(
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, recoveryPasswordForm.getButtonRecoveryByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, recoveryPasswordForm.getButtonRecoveryByEmailAttributeAriaSelectedValue())
        );
        recoveryPasswordForm.clickButtonRecoveryByPhoneNumber();
        assertAll(
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, recoveryPasswordForm.getButtonRecoveryByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, recoveryPasswordForm.getButtonRecoveryByEmailAttributeAriaSelectedValue())
        );
    }

    @Test
    public void checkTitleTextButtonsLabelAttributeOfRecoveryByEmailNames() {
        waitOfElement(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_EMAIL);
        recoveryPasswordForm.clickButtonRecoveryByEmail();
        waitOfElement(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_EMAIL, HomePageLocator.ATTRIBUTE_ARIA_SELECTED, HomePageLocator.ATTRIBUTE_ACTIVE);
        assertAll(
                () -> assertEquals(ExpectedMessages.TITLE_LOGIN, recoveryPasswordForm.getButtonLoginText()),
                () -> assertEquals(ExpectedMessages.TITLE_RECOVERY_PASSWORD, recoveryPasswordForm.getTitleRecoveryPasswordFormText()),
                () -> assertEquals(ExpectedMessages.BUTTON_BY_PHONE_NUMBER, recoveryPasswordForm.getButtonRecoveryByPhoneNumberText()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, recoveryPasswordForm.getButtonRecoveryByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.BUTTON_BY_EMAIL, recoveryPasswordForm.getButtonRecoveryByEmailText()),
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, recoveryPasswordForm.getButtonRecoveryByEmailAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.TEXT_RECOVERY_BY_EMAIL, recoveryPasswordForm.getTextRecoveryByPEmail()),
                () -> assertEquals(ExpectedMessages.LABEL_EMAIL, recoveryPasswordForm.getLabelEmailText()),
                () -> assertEquals(ExpectedMessages.BUTTON_SEND, recoveryPasswordForm.getButtonSendByEmailText()),
                () -> assertFalse(recoveryPasswordForm.isButtonSendByEmailEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_SEND))
        );
    }

    @Test
    public void checkClickButtonSendByEmailAfterInputAnyKeyInEmailField() {
        waitOfElement(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_EMAIL);
        recoveryPasswordForm.clickButtonRecoveryByEmail();
        recoveryPasswordForm.inputEmail(generateInvalidPhoneNumberOrEmail());
        waitOfElement(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_EMAIL);
        recoveryPasswordForm.clickButtonSendByEmail();
        assertAll(
                () -> assertTrue(recoveryPasswordForm.isButtonSendByEmailEnabled(), ExpectedMessages.getMessageButtonEnabled(ExpectedMessages.BUTTON_SEND)),
                () -> assertEquals(ExpectedMessages.ERROR_MESSAGE_INVALID_EMAIL, loginForm.getErrorMessageText())
        );
    }

    @Test
    public void checkClickButtonSendByEmailAfterInputEmail() {
        waitOfElement(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_EMAIL);
        recoveryPasswordForm.clickButtonRecoveryByEmail();
        recoveryPasswordForm.inputEmail(faker.internet().emailAddress());
        recoveryPasswordForm.clickButtonSendByEmail();
        waitOfElement(LoginFormLocator.ERROR_MESSAGE);
        assertAll(
                () -> assertTrue(recoveryPasswordForm.isButtonSendByEmailEnabled(), ExpectedMessages.getMessageButtonEnabled(ExpectedMessages.BUTTON_SEND)),
                () -> assertEquals(ExpectedMessages.ERROR_MESSAGE_RECOVERY_BY_EMAIL, loginForm.getErrorMessageText())
        );
    }

    @Test
    public void checkClickButtonLoginReturnToLoginForm() {
        waitOfElement(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_PHONE_NUMBER, HomePageLocator.ATTRIBUTE_ARIA_SELECTED, HomePageLocator.ATTRIBUTE_ACTIVE);
        waitOfElement(RecoveryPasswordFormLocator.BUTTON_LOGIN);
        recoveryPasswordForm.clickButtonLogin();
        waitOfElement(LoginFormLocator.TITLE_LOGIN_FORM);
        assertEquals(ExpectedMessages.TITLE_LOGIN, loginForm.getTitleText());
    }
}
