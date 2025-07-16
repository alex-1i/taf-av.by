package by.av.ui;

import by.av.ui.expectedMessages.ExpectedMessages;
import by.av.ui.pages.home.HomePageLocator;
import by.av.ui.pages.home.loginform.LoginFormLocator;
import by.av.ui.pages.home.loginform.registrationform.RegistrationForm;
import by.av.ui.pages.home.loginform.registrationform.RegistrationFormLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static by.av.ui.driver.Waiter.waitOfElement;
import static by.av.ui.driver.Waiter.waitOfElementToBeClickable;
import static by.av.utils.Utils.generateInvalidPhoneNumberOrEmail;
import static by.av.utils.Utils.generatePhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

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
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputName() {
        registrationForm.inputNameByPhoneNumber(faker.name().firstName());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputPhoneNumber() {
        registrationForm.inputPhoneNumber(generatePhoneNumber());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputPassword() {
        registrationForm.inputPasswordForPhoneNumber(faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputNameAndPhoneNumber() {
        registrationForm.inputNameByPhoneNumberAndPhoneNumber(faker.name().firstName(), generatePhoneNumber());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputNameAndPassword() {
        registrationForm.inputNameByPhoneNumberAndPasswordForPhoneNumber(faker.name().firstName(), faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputPhoneNumberAndPassword() {
        registrationForm.inputPhoneNumberAndPasswordForPhoneNumber(generatePhoneNumber(), faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputNameAndInvalidPhoneNumberAndPassword() {
        registrationForm.inputNameByPhoneNumberAndPhoneNumberAndPasswordForPhoneNumber(faker.name().firstName(), generateInvalidPhoneNumberOrEmail(), faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberAfterInputNameAndPhoneNumberAndPassword() {
        registrationForm.inputNameByPhoneNumberAndPhoneNumberAndPasswordForPhoneNumber(faker.name().firstName(), generatePhoneNumber(), faker.internet().password(true));
        assertTrue(registrationForm.isButtonRegistrationByPhoneNumberEnabled(), ExpectedMessages.getMessageButtonEnabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
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
    public void checkButtonRegistrationByEmailEnabledAfterInputName() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputNameByEmail(faker.name().firstName());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    public void checkButtonRegistrationByEmailEnabledAfterInputEmail() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputEmail(faker.internet().emailAddress());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    public void checkButtonRegistrationByEmailEnabledAfterInputPassword() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputPasswordForEmail(faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    public void checkButtonRegistrationByEmailEnabledAfterInputNameAndEmail() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputNameByEmailAndEmail(faker.name().firstName(), faker.internet().emailAddress());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    public void checkButtonRegistrationByEmailEnabledAfterInputNameAndPassword() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputNameByEmailAndPasswordForEmail(faker.name().firstName(), faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    public void checkButtonRegistrationByEmailEnabledAfterInputEmailAndPassword() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputEmailAndPasswordForEmail(faker.internet().emailAddress(), faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonDisabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    public void checkButtonRegistrationByEmailEnabledAfterInputNameAndEmailAndPassword() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputNameByEmailAndEmailAndPasswordForEmail(faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password(true));
        assertTrue(registrationForm.isButtonRegistrationByEmailEnabled(), ExpectedMessages.getMessageButtonEnabled(ExpectedMessages.BUTTON_REGISTRATION));
    }

    @Test
    public void checkClickButtonLoginReturnToLoginForm() {
        registrationForm.clickButtonLogin();
        waitOfElement(LoginFormLocator.TITLE_LOGIN_FORM);
        assertEquals(ExpectedMessages.TITLE_LOGIN, loginForm.getTitleText());
    }
}
