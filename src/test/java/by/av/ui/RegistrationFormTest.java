package by.av.ui;

import by.av.ui.expectedMessages.ExpectedMessages;
import by.av.ui.pages.home.HomePageLocator;
import by.av.ui.pages.home.loginform.LoginFormLocator;
import by.av.ui.pages.home.loginform.registrationform.RegistrationForm;
import by.av.ui.pages.home.loginform.registrationform.RegistrationFormLocator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static by.av.ui.driver.Waiter.waitOfElement;
import static by.av.ui.driver.Waiter.waitOfElementToBeClickable;
import static by.av.utils.Utils.generateInvalidInputData;
import static by.av.utils.Utils.generatePhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

@Epic("UI")
@Feature("Проверка формы регистрации")
@DisplayName("Тесты формы регистрации по номеру телефона и по почте")
public class RegistrationFormTest extends WithLoginSetUp {

    private RegistrationForm registrationForm;

    @BeforeEach
    public void setUp() {
        openLoginForm();
        loginForm.clickButtonRegistration();
        waitOfElement(RegistrationFormLocator.TITLE_REGISTRATION);
        registrationForm = new RegistrationForm();
    }

    @Test
    @DisplayName("Проверка наличия элементов формы регистрации по номеру телефона")
    public void checkTitleButtonsLabelsHelperAttributeOfRegistrationByPhoneNumberNames() {
        assertAll(
                () -> assertEquals(ExpectedMessages.TITLE_REGISTRATION, registrationForm.getTitleRegistrationText()),
                () -> assertEquals(ExpectedMessages.BUTTON_BY_PHONE_NUMBER, registrationForm.getChooseRegistrationByPhoneNumberText()),
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, registrationForm.getChooseRegistrationByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.BUTTON_BY_EMAIL, registrationForm.getChooseRegistrationByEmailText()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, registrationForm.getChooseRegistrationByEmailAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.LABEL_NAME, registrationForm.getLabelNameByPhoneNumberText()),
                () -> assertEquals(ExpectedMessages.LABEL_PHONE, registrationForm.getLabelPhoneNumberText()),
                () -> assertEquals(ExpectedMessages.LABEL_PASSWORD, registrationForm.getLabelPasswordForPhoneNumberText()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, registrationForm.getButtonInvertForPhoneNumberAttributeAriaPressedValue()),
                () -> assertEquals(ExpectedMessages.PASSWORD_HIDDEN, registrationForm.getInputPasswordForPhoneNumberAttributeTypeValue()),
                () -> assertEquals(ExpectedMessages.WARN_MESSAGE_FOR_PASSWORD, registrationForm.getWarnMessageForPasswordForPhoneNumberText()),
                () -> assertEquals(ExpectedMessages.BUTTON_REGISTRATION, registrationForm.getButtonRegistrationByPhoneNumberText()),
                () -> assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION)),
                () -> assertEquals(ExpectedMessages.BUTTON_LOGIN_FORM, registrationForm.getButtonLoginText())
        );
    }

    @Test
    @DisplayName("Проверка кнопки инверсии пароля для номера телефона")
    public void checkButtonInverterForPhoneNumber() {
        waitOfElement(RegistrationFormLocator.BUTTON_INVERTER_FOR_PHONE_NUMBER);
        registrationForm.clickButtonInvertForPhoneNumber();
        waitOfElement(RegistrationFormLocator.BUTTON_INVERTER_FOR_PHONE_NUMBER, HomePageLocator.ATTRIBUTE_ARIA_PRESSED, HomePageLocator.ATTRIBUTE_ACTIVE);
        assertAll(
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, registrationForm.getButtonInvertForPhoneNumberAttributeAriaPressedValue()),
                () -> assertEquals(ExpectedMessages.PASSWORD_VISIBLE, registrationForm.getInputPasswordForPhoneNumberAttributeTypeValue())
        );
    }

    @Test
    @DisplayName("Проверка не активности кнопки регистрации по номеру телефона после ввода имени")
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputName() {
        registrationForm.inputNameByPhoneNumber(faker.name().firstName());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка не активности кнопки регистрации по номеру телефона после ввода номера телефона")
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputPhoneNumber() {
        registrationForm.inputPhoneNumber(generatePhoneNumber());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка не активности кнопки регистрации по номеру телефона после ввода пароля")
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputPassword() {
        registrationForm.inputPasswordForPhoneNumber(faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка не активности кнопки регистрации по номеру телефона после ввода имени и номера телефона")
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputNameAndPhoneNumber() {
        registrationForm.inputNameByPhoneNumberAndPhoneNumber(faker.name().firstName(), generatePhoneNumber());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка не активности кнопки регистрации по номеру телефона после ввода имени пароля")
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputNameAndPassword() {
        registrationForm.inputNameByPhoneNumberAndPasswordForPhoneNumber(faker.name().firstName(), faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка не активности кнопки регистрации по номеру телефона после ввода номеру телефона и пароля")
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputPhoneNumberAndPassword() {
        registrationForm.inputPhoneNumberAndPasswordForPhoneNumber(generatePhoneNumber(), faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка не активности кнопки регистрации по номеру телефона после ввода имени, невалидного номера телефона и пароля")
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputNameAndInvalidPhoneNumberAndPassword() {
        registrationForm.inputNameByPhoneNumberAndPhoneNumberAndPasswordForPhoneNumber(faker.name().firstName(), generateInvalidInputData(), faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка активности кнопки регистрации по номеру телефона после ввода всех данных")
    public void checkButtonRegistrationByPhoneNumberAfterInputNameAndPhoneNumberAndPassword() {
        registrationForm.inputNameByPhoneNumberAndPhoneNumberAndPasswordForPhoneNumber(faker.name().firstName(), generatePhoneNumber(), faker.internet().password(true));
        assertTrue(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonEnabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка переходов между формами регистрации по телефону и по почте")
    public void checkReturnToRegistrationByPhoneNumberAfterGoToRegistrationByEmailOrLogin() {
        registrationForm.clickChooseRegistrationByEmail();
        assertAll(
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, registrationForm.getChooseRegistrationByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, registrationForm.getChooseRegistrationByEmailAttributeAriaSelectedValue())
        );
        registrationForm.clickChooseRegistrationByPhoneNumber();
        assertAll(
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, registrationForm.getChooseRegistrationByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, registrationForm.getChooseRegistrationByEmailAttributeAriaSelectedValue())
        );
    }

    @Test
    @DisplayName("Проверка наличия элементов формы регистрации по почте")
    public void checkTitleButtonsLabelsHelperAttributeOfRegistrationByEmailNames() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        assertAll(
                () -> assertEquals(ExpectedMessages.TITLE_REGISTRATION, registrationForm.getTitleRegistrationText()),
                () -> assertEquals(ExpectedMessages.BUTTON_BY_PHONE_NUMBER, registrationForm.getChooseRegistrationByPhoneNumberText()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, registrationForm.getChooseRegistrationByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.BUTTON_BY_EMAIL, registrationForm.getChooseRegistrationByEmailText()),
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, registrationForm.getChooseRegistrationByEmailAttributeAriaSelectedValue()),
                () -> assertEquals(ExpectedMessages.LABEL_NAME, registrationForm.getLabelNameByEmailText()),
                () -> assertEquals(ExpectedMessages.LABEL_EMAIL, registrationForm.getLabelEmailText()),
                () -> assertEquals(ExpectedMessages.LABEL_PASSWORD, registrationForm.getLabelPasswordForEmailText()),
                () -> assertEquals(ExpectedMessages.INACTIVE_ATTRIBUTE, registrationForm.getButtonInvertForEmailAttributeAriaPressedValue()),
                () -> assertEquals(ExpectedMessages.PASSWORD_HIDDEN, registrationForm.getInputPasswordForEmailAttributeTypeValue()),
                () -> assertEquals(ExpectedMessages.WARN_MESSAGE_FOR_PASSWORD, registrationForm.getWarnMessageForPasswordForEmailText()),
                () -> assertEquals(ExpectedMessages.BUTTON_REGISTRATION, registrationForm.getButtonRegistrationByEmailText()),
                () -> assertFalse(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION)),
                () -> assertEquals(ExpectedMessages.BUTTON_LOGIN_FORM, registrationForm.getButtonLoginText())
        );
    }

    @Test
    @DisplayName("Проверка кнопки инверсии пароля для почты")
    public void checkButtonInverterForEmail() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL, HomePageLocator.ATTRIBUTE_ARIA_SELECTED, HomePageLocator.ATTRIBUTE_ACTIVE);
        waitOfElementToBeClickable(RegistrationFormLocator.BUTTON_INVERTER_FOR_EMAIL);
        registrationForm.clickButtonInvertForEmail();
        assertAll(
                () -> assertEquals(ExpectedMessages.ACTIVE_ATTRIBUTE, registrationForm.getButtonInvertForEmailAttributeAriaPressedValue()),
                () -> assertEquals(ExpectedMessages.PASSWORD_VISIBLE, registrationForm.getInputPasswordForEmailAttributeTypeValue())
        );
    }

    @Test
    @DisplayName("Проверка не активности кнопки регистрации по почте после ввода имени")
    public void checkButtonRegistrationByEmailEnabledAfterInputName() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputNameByEmail(faker.name().firstName());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка не активности кнопки регистрации по почте после ввода почты")
    public void checkButtonRegistrationByEmailEnabledAfterInputEmail() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputEmail(faker.internet().emailAddress());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка не активности кнопки регистрации по почте после ввода пароля")
    public void checkButtonRegistrationByEmailEnabledAfterInputPassword() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputPasswordForEmail(faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка не активности кнопки регистрации по почте после ввода имени и почты")
    public void checkButtonRegistrationByEmailEnabledAfterInputNameAndEmail() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputNameByEmailAndEmail(faker.name().firstName(), faker.internet().emailAddress());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка не активности кнопки регистрации по почте после ввода имени пароля")
    public void checkButtonRegistrationByEmailEnabledAfterInputNameAndPassword() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputNameByEmailAndPasswordForEmail(faker.name().firstName(), faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка не активности кнопки регистрации по почте после ввода почты и пароля")
    public void checkButtonRegistrationByEmailEnabledAfterInputEmailAndPassword() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputEmailAndPasswordForEmail(faker.internet().emailAddress(), faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка активности кнопки регистрации по почте после ввода имени, почты и пароля")
    public void checkButtonRegistrationByEmailEnabledAfterInputNameAndEmailAndPassword() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputNameByEmailAndEmailAndPasswordForEmail(faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password(true));
        assertTrue(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonEnabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    @DisplayName("Проверка кнопки возврата на форму входа")
    public void checkClickButtonLoginReturnToLoginForm() {
        registrationForm.clickButtonLogin();
        waitOfElement(LoginFormLocator.TITLE_LOGIN_FORM);
        assertEquals(ExpectedMessages.TITLE_LOGIN, loginForm.getTitleText());
    }
}
