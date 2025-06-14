package by.av.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static by.av.utils.Utils.generatePhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

public class LoginFormTest extends WithLoginSetUp {

    @BeforeEach
    public void setUp() {
        openLoginForm();
    }

    @Test
    public void checkTitleLabelsButtonsAttributeOfLoginByPhoneNumberNames() {
        assertAll(
                () -> assertEquals("Вход", loginForm.getTitleText()),
                () -> assertEquals("false", loginForm.getLoginFormOpenedAttributeAriaHiddenValue()),
                () -> assertEquals("по телефону", loginForm.getButtonLoginByPhoneNumberText()),
                () -> assertEquals("true", loginForm.getButtonLoginByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals("почте или логину", loginForm.getButtonLoginByEmailOrLoginText()),
                () -> assertEquals("false", loginForm.getButtonLoginByEmailOrLoginAttributeAriaSelectedValue()),
                () -> assertEquals("Телефон", loginForm.getLabelPhoneNumberText()),
                () -> assertEquals("Пароль", loginForm.getLabelPasswordForPhoneNumberText()),
                () -> assertEquals("false", loginForm.getButtonInvertAttributeAriaPressedValue()),
                () -> assertEquals("password", loginForm.getInputPasswordForPhoneNumberAttributeTypeValue()),
                () -> assertEquals("Не помню пароль", loginForm.getButtonRecoveryPasswordText()),
                () -> assertEquals("Войти", loginForm.getButtonLoginText()),
                () -> assertFalse(loginForm.isButtonLoginEnabled()),
                () -> assertEquals("Регистрация\n" +
                        "для тех, кто первый раз на сайте", loginForm.getButtonRegistrationText())
        );
    }

    @Test
    public void checkButtonInverterForLoginByPhoneNumber() {
        loginForm.clickButtonInvert();
        assertAll(
                () -> assertEquals("true", loginForm.getButtonInvertAttributeAriaPressedValue()),
                () -> assertEquals("text", loginForm.getInputPasswordForPhoneNumberAttributeTypeValue())
        );

    }

    @Test
    public void checkButtonLoginEnabledAfterInputPhoneNumber() {
        loginForm.inputPhoneNumber(generatePhoneNumber());
        assertFalse(loginForm.isButtonLoginEnabled());
    }

    @Test
    public void checkButtonLoginEnabledAfterInputPasswordForPhoneNumber() {
        loginForm.inputPasswordForPhoneNumber(faker.internet().password());
        assertFalse(loginForm.isButtonLoginEnabled());
    }

    @Test
    public void checkButtonLoginEnabledAfterInputInvalidPhoneNumberAndInputPasswordForPhoneNumber() {
        loginForm.inputPhoneNumberAndPasswordForPhoneNumber("111111111", faker.internet().password());
        assertFalse(loginForm.isButtonLoginEnabled());
    }

    @Test
    public void checkButtonLoginEnabledAfterInputPhoneNumberAndInputPasswordForPhoneNumber() {
        loginForm.inputPhoneNumberAndPasswordForPhoneNumber(generatePhoneNumber(), faker.internet().password());
        assertTrue(loginForm.isButtonLoginEnabled());
    }

    @Test
    public void checkClickButtonLoginEnabledAfterInputNonRegisteredPhoneNumberAndInputPasswordForPhoneNumber() {
        checkButtonLoginEnabledAfterInputPhoneNumberAndInputPasswordForPhoneNumber();
        loginForm.clickButtonLogin();
        assertEquals("Неверный телефон или пароль. Если забыли пароль, восстановите его", loginForm.getErrorMessageText());
    }

    @Test
    public void checkReturnToLoginByPhoneNumberAfterGoToLoginByEmailOrLogin() {
        loginForm.clickButtonLoginByEmailOrLogin();
        assertAll(
                () -> assertEquals("false", loginForm.getButtonLoginByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals("true", loginForm.getButtonLoginByEmailOrLoginAttributeAriaSelectedValue())
        );
        loginForm.clickButtonLoginByPhoneNumber();
        assertAll(
                () -> assertEquals("true", loginForm.getButtonLoginByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals("false", loginForm.getButtonLoginByEmailOrLoginAttributeAriaSelectedValue())
        );
    }

    @Test
    public void checkTitleLabelsButtonsAttributeOfLoginByEmailOrLoginNames() {
        loginForm.clickButtonLoginByEmailOrLogin();
        assertAll(
                () -> assertEquals("Вход", loginForm.getTitleText()),
                () -> assertEquals("false", loginForm.getLoginFormOpenedAttributeAriaHiddenValue()),
                () -> assertEquals("по телефону", loginForm.getButtonLoginByPhoneNumberText()),
                () -> assertEquals("false", loginForm.getButtonLoginByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals("почте или логину", loginForm.getButtonLoginByEmailOrLoginText()),
                () -> assertEquals("true", loginForm.getButtonLoginByEmailOrLoginAttributeAriaSelectedValue()),
                () -> assertEquals("Электронная почта или логин", loginForm.getLabelEmailOrLoginText()),
                () -> assertEquals("Пароль", loginForm.getLabelPasswordForPEmailOrLoginText()),
                () -> assertEquals("false", loginForm.getButtonInvertAttributeAriaPressedValue()),
                () -> assertEquals("password", loginForm.getInputPasswordForEmailOrLoginAttributeATypeValue()),
                () -> assertEquals("Не помню пароль", loginForm.getButtonRecoveryPasswordText()),
                () -> assertEquals("Войти", loginForm.getButtonLoginText()),
                () -> assertFalse(loginForm.isButtonLoginEnabled()),
                () -> assertEquals("Регистрация\n" +
                        "для тех, кто первый раз на сайте", loginForm.getButtonRegistrationText())
        );
    }

    @Test
    public void checkButtonInverterForLoginByEmailOrLogin() {
        loginForm.clickButtonLoginByEmailOrLogin();
        loginForm.clickButtonInvert();
        assertAll(
                () -> assertEquals("true", loginForm.getButtonInvertAttributeAriaPressedValue()),
                () -> assertEquals("text", loginForm.getInputPasswordForEmailOrLoginAttributeATypeValue())
        );

    }

    @Test
    public void checkButtonLoginEnabledAfterInputEmailOrLogin() {
        loginForm.clickButtonLoginByEmailOrLogin();
        loginForm.inputEmailOrLogin(faker.internet().emailAddress());
        assertFalse(loginForm.isButtonLoginEnabled());
    }

    @Test
    public void checkButtonLoginEnabledAfterInputPasswordForEmailOrLogin() {
        loginForm.clickButtonLoginByEmailOrLogin();
        loginForm.inputPasswordForEmailOrLogin(faker.internet().password());
        assertFalse(loginForm.isButtonLoginEnabled());
    }

    @Test
    public void checkButtonLoginEnabledAfterInputEmailOrLoginAndInputPasswordForEmailOrLogin() {
        loginForm.clickButtonLoginByEmailOrLogin();
        loginForm.inputEmailOrLoginAndPasswordForEmailOrLogin(faker.internet().emailAddress(), faker.internet().password());
        assertTrue(loginForm.isButtonLoginEnabled());
    }


    @Test
    public void checkClickButtonLoginEnabledAfterInputNonRegisteredEmailOrLoginAndInputPasswordForEmailOrLogin() {
        checkButtonLoginEnabledAfterInputEmailOrLoginAndInputPasswordForEmailOrLogin();
        loginForm.clickButtonLogin();
        assertEquals("Неверный логин или пароль. Если забыли пароль, восстановите его", loginForm.getErrorMessageText());
    }

    @Test
    public void checkButtonCrossCloseLoginForm() {
        loginForm.clickButtonCross();
        assertEquals("true", loginForm.getLoginFormClosedAttributeAriaHiddenValue());
    }
}
