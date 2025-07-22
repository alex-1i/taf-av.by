package by.av.ui;

import by.av.ui.expectedMessages.ExpectedMessages;
import by.av.ui.pages.home.HomePageLocator;
import by.av.ui.pages.home.loginform.LoginFormLocator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static by.av.ui.driver.Waiter.waitOfElement;
import static by.av.utils.Utils.generateInvalidInputData;
import static by.av.utils.Utils.generatePhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

@Epic("UI")
@Feature("Проверка формы входа")
@DisplayName("Тесты формы входа по номеру телефона и по почте")
public class LoginFormTest extends WithLoginSetUp {

    @BeforeEach
    public void setUp() {
        openLoginForm();
    }

    @Test
    @DisplayName("Проверка наличия элементов формы входа по номеру телефона")
    public void checkTitleLabelsButtonsAttributeOfLoginByPhoneNumberNames() {
        assertAll(
                () -> assertEquals(ExpectedMessages.TITLE_LOGIN, loginForm.getTitleText()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, loginForm.getLoginFormOpenedAttributeAriaHiddenValue()),
                () -> assertEquals(ExpectedMessages.BUTTON_BY_PHONE_NUMBER, loginForm.getButtonLoginByPhoneNumberText()),
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, loginForm.getButtonLoginByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.BUTTON_BY_EMAIL_OR_LOGIN, loginForm.getButtonLoginByEmailOrLoginText()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, loginForm.getButtonLoginByEmailOrLoginAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.LABEL_PHONE, loginForm.getLabelPhoneNumberText()),
                () -> assertEquals(ExpectedMessages.LABEL_PASSWORD, loginForm.getLabelPasswordForPhoneNumberText()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, loginForm.getButtonInvertAttributeAriaPressedValue()),
                () -> assertEquals(ExpectedMessages.PASSWORD_HIDDEN, loginForm.getInputPasswordForPhoneNumberAttributeTypeValue()),
                () -> assertEquals(ExpectedMessages.BUTTON_RECOVERY, loginForm.getButtonRecoveryPasswordText()),
                () -> assertEquals(ExpectedMessages.BUTTON_LOGIN, loginForm.getButtonLoginText()),
                () -> assertFalse(loginForm.isButtonLoginEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_LOGIN)),
                () -> assertEquals(ExpectedMessages.BUTTON_REGISTRATION_FORM, loginForm.getButtonRegistrationText())
        );
    }

    @Test
    @DisplayName("Проверка кнопки инверсии пароля для номера телефона")
    public void checkButtonInverterForLoginByPhoneNumber() {
        loginForm.clickButtonInvert();
        assertAll(
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, loginForm.getButtonInvertAttributeAriaPressedValue()),
                () -> assertEquals(ExpectedMessages.PASSWORD_VISIBLE, loginForm.getInputPasswordForPhoneNumberAttributeTypeValue())
        );

    }

    @Test
    @DisplayName("Проверка не активности кнопки входа по номеру телефона после ввода номера телефона")
    public void checkButtonLoginEnabledAfterInputPhoneNumber() {
        loginForm.inputPhoneNumber(generatePhoneNumber());
        assertFalse(loginForm.isButtonLoginEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_LOGIN));
    }

    @Test
    @DisplayName("Проверка не активности кнопки входа по номеру телефона после ввода пароля")
    public void checkButtonLoginEnabledAfterInputPasswordForPhoneNumber() {
        loginForm.inputPasswordForPhoneNumber(faker.internet().password());
        assertFalse(loginForm.isButtonLoginEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_LOGIN));
    }

    @Test
    @DisplayName("Проверка не активности кнопки входа по номеру телефона после ввода невалидного телефона и пароля")
    public void checkButtonLoginEnabledAfterInputInvalidPhoneNumberAndInputPasswordForPhoneNumber() {
        loginForm.inputPhoneNumberAndPasswordForPhoneNumber(generateInvalidInputData(), faker.internet().password());
        assertFalse(loginForm.isButtonLoginEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_LOGIN));
    }

    @Test
    @DisplayName("Проверка активности кнопки входа по номеру телефона после ввода валидного телефона и пароля")
    public void checkButtonLoginEnabledAfterInputPhoneNumberAndInputPasswordForPhoneNumber() {
        loginForm.inputPhoneNumberAndPasswordForPhoneNumber(generatePhoneNumber(), faker.internet().password());
        assertTrue(loginForm.isButtonLoginEnabled(), ExpectedMessages.getMessageButtonEnabled(ExpectedMessages.BUTTON_LOGIN));
    }

    @Test
    @DisplayName("Проверка активности кнопки входа и попытки авторизации по номеру телефона после ввода незарегистрированного телефона и пароля")
    public void checkClickButtonLoginEnabledAfterInputNonRegisteredPhoneNumberAndInputPasswordForPhoneNumber() {
        checkButtonLoginEnabledAfterInputPhoneNumberAndInputPasswordForPhoneNumber();
        loginForm.clickButtonLogin();
        assertEquals(ExpectedMessages.ERROR_MESSAGE_LOGIN_BY_PHONE_NUMBER, loginForm.getErrorMessageText());
    }

    @Test
    @DisplayName("Проверка переходов между формами входа по телефону и по почте")
    public void checkReturnToLoginByPhoneNumberAfterGoToLoginByEmailOrLogin() {
        loginForm.clickButtonLoginByEmailOrLogin();
        assertAll(
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, loginForm.getButtonLoginByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, loginForm.getButtonLoginByEmailOrLoginAttributeAriaSelectedValue())
        );
        loginForm.clickButtonLoginByPhoneNumber();
        assertAll(
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, loginForm.getButtonLoginByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, loginForm.getButtonLoginByEmailOrLoginAttributeAriaSelectedValue())
        );
    }

    @Test
    @DisplayName("Проверка наличия элементов формы входа по почте")
    public void checkTitleLabelsButtonsAttributeOfLoginByEmailOrLoginNames() {
        loginForm.clickButtonLoginByEmailOrLogin();
        assertAll(
                () -> assertEquals(ExpectedMessages.TITLE_LOGIN, loginForm.getTitleText()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, loginForm.getLoginFormOpenedAttributeAriaHiddenValue()),
                () -> assertEquals(ExpectedMessages.BUTTON_BY_PHONE_NUMBER, loginForm.getButtonLoginByPhoneNumberText()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, loginForm.getButtonLoginByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.BUTTON_BY_EMAIL_OR_LOGIN, loginForm.getButtonLoginByEmailOrLoginText()),
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, loginForm.getButtonLoginByEmailOrLoginAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.LABEL_EMAIL_OR_LOGIN, loginForm.getLabelEmailOrLoginText()),
                () -> assertEquals(ExpectedMessages.LABEL_PASSWORD, loginForm.getLabelPasswordForPEmailOrLoginText()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, loginForm.getButtonInvertAttributeAriaPressedValue()),
                () -> assertEquals(ExpectedMessages.PASSWORD_HIDDEN, loginForm.getInputPasswordForEmailOrLoginAttributeATypeValue()),
                () -> assertEquals(ExpectedMessages.BUTTON_RECOVERY, loginForm.getButtonRecoveryPasswordText()),
                () -> assertEquals(ExpectedMessages.BUTTON_LOGIN, loginForm.getButtonLoginText()),
                () -> assertFalse(loginForm.isButtonLoginEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_LOGIN)),
                () -> assertEquals(ExpectedMessages.BUTTON_REGISTRATION_FORM, loginForm.getButtonRegistrationText())
        );
    }

    @Test
    @DisplayName("Проверка кнопки инверсии пароля для почты")
    public void checkButtonInverterForLoginByEmailOrLogin() {
        loginForm.clickButtonLoginByEmailOrLogin();
        waitOfElement(LoginFormLocator.INPUT_PASSWORD_FOR_EMAIL_OR_LOGIN);
        loginForm.clickButtonInvert();
        waitOfElement(LoginFormLocator.BUTTON_INVERTER, HomePageLocator.ATTRIBUTE_ARIA_PRESSED, HomePageLocator.ATTRIBUTE_ACTIVE);
        assertAll(
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, loginForm.getButtonInvertAttributeAriaPressedValue()),
                () -> assertEquals(ExpectedMessages.PASSWORD_VISIBLE, loginForm.getInputPasswordForEmailOrLoginAttributeATypeValue())
        );

    }

    @Test
    @DisplayName("Проверка не активности кнопки входа по почте после ввода почты или логина")
    public void checkButtonLoginEnabledAfterInputEmailOrLogin() {
        loginForm.clickButtonLoginByEmailOrLogin();
        loginForm.inputEmailOrLogin(faker.internet().emailAddress());
        assertFalse(loginForm.isButtonLoginEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_LOGIN));
    }

    @Test
    @DisplayName("Проверка не активности кнопки входа по почте после ввода пароля")
    public void checkButtonLoginEnabledAfterInputPasswordForEmailOrLogin() {
        loginForm.clickButtonLoginByEmailOrLogin();
        loginForm.inputPasswordForEmailOrLogin(faker.internet().password());
        assertFalse(loginForm.isButtonLoginEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_LOGIN));
    }

    @Test
    @DisplayName("Проверка не активности кнопки входа по почте после ввода почты или логина")
    public void checkButtonLoginEnabledAfterInputEmailOrLoginAndInputPasswordForEmailOrLogin() {
        loginForm.clickButtonLoginByEmailOrLogin();
        loginForm.inputEmailOrLoginAndPasswordForEmailOrLogin(faker.internet().emailAddress(), faker.internet().password());
        assertTrue(loginForm.isButtonLoginEnabled(), ExpectedMessages.getMessageButtonEnabled(ExpectedMessages.BUTTON_LOGIN));
    }


    @Test
    @DisplayName("Проверка активности кнопки входа и попытки авторизации по почте после ввода незарегистрированной почты или логина и пароля")
    public void checkClickButtonLoginEnabledAfterInputNonRegisteredEmailOrLoginAndInputPasswordForEmailOrLogin() {
        checkButtonLoginEnabledAfterInputEmailOrLoginAndInputPasswordForEmailOrLogin();
        loginForm.clickButtonLogin();
        assertEquals(ExpectedMessages.ERROR_MESSAGE_LOGIN_BY_EMAIL, loginForm.getErrorMessageText());
    }

    @Test
    @DisplayName("Проверка кнопки закрытия формы")
    public void checkButtonCrossCloseLoginForm() {
        loginForm.clickButtonCross();
        assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, loginForm.getLoginFormClosedAttributeAriaHiddenValue());
    }
}
