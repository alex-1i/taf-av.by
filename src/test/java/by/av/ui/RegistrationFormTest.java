package by.av.ui;

import by.av.ui.pages.home.loginform.LoginFormLocator;
import by.av.ui.pages.home.loginform.registrationform.RegistrationForm;
import by.av.ui.pages.home.loginform.registrationform.RegistrationFormLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static by.av.utils.Utils.generatePhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationFormTest extends WithLoginSetUp {

    // к сожалению при частом нажатии на кнопку регистрации возникает капча, поэтому некоторые методы закомментированы

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
                () -> assertEquals("Регистрация", registrationForm.getTitleRegistrationText()),
                () -> assertEquals("по телефону", registrationForm.getChooseRegistrationByPhoneNumberText()),
                () -> assertEquals("true", registrationForm.getChooseRegistrationByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals("почте", registrationForm.getChooseRegistrationByEmailText()),
                () -> assertEquals("false", registrationForm.getChooseRegistrationByEmailAttributeAriaSelectedValue()),
                () -> assertEquals("Имя на кириллице", registrationForm.getLabelNameByPhoneNumberText()),
                () -> assertEquals("Телефон", registrationForm.getLabelPhoneNumberText()),
                () -> assertEquals("Пароль", registrationForm.getLabelPasswordForPhoneNumberText()),
                () -> assertEquals("false", registrationForm.getButtonInvertForPhoneNumberAttributeAriaPressedValue()),
                () -> assertEquals("password", registrationForm.getInputPasswordForPhoneNumberAttributeTypeValue()),
                () -> assertEquals("Не короче 8 символов и только латиница и цифры", registrationForm.getWarnMessageForPasswordForPhoneNumberText()),
                () -> assertEquals("Зарегистрироваться", registrationForm.getButtonRegistrationByPhoneNumberText()),
                () -> assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled()),
                () -> assertEquals("Вход\nдля тех, кто уже зарегистрирован", registrationForm.getButtonLoginText())
        );
    }

    @Test
    public void checkButtonInverterForPhoneNumber() {
        waitOfElement(RegistrationFormLocator.BUTTON_INVERTER_FOR_PHONE_NUMBER);
        registrationForm.clickButtonInvertForPhoneNumber();
        assertAll(
                () -> assertEquals("true", registrationForm.getButtonInvertForPhoneNumberAttributeAriaPressedValue()),
                () -> assertEquals("text", registrationForm.getInputPasswordForPhoneNumberAttributeTypeValue())
        );
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputName() {
        registrationForm.inputNameByPhoneNumber("test");
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled());
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputPhoneNumber() {
        registrationForm.inputPhoneNumber(generatePhoneNumber());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled());
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputPassword() {
        registrationForm.inputPasswordForPhoneNumber(faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled());
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputNameAndPhoneNumber() {
        registrationForm.inputNameByPhoneNumberAndPhoneNumber("test", generatePhoneNumber());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled());
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputNameAndPassword() {
        registrationForm.inputNameByPhoneNumberAndPasswordForPhoneNumber("test", faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled());
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputPhoneNumberAndPassword() {
        registrationForm.inputPhoneNumberAndPasswordForPhoneNumber(generatePhoneNumber(), faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled());
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberEnabledAfterInputNameAndInvalidPhoneNumberAndPassword() {
        registrationForm.inputNameByPhoneNumberAndPhoneNumberAndPasswordForPhoneNumber("test", "111111111", faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByPhoneNumberEnabled());
    }

    @Test
    public void checkButtonRegistrationByPhoneNumberAfterInputNameAndPhoneNumberAndPassword() {
        registrationForm.inputNameByPhoneNumberAndPhoneNumberAndPasswordForPhoneNumber("test", generatePhoneNumber(), faker.internet().password(true));
        assertTrue(registrationForm.isButtonRegistrationByPhoneNumberEnabled());
    }

//    @Test
//    public void checkAndClickButtonRegistrationByPhoneNumberAfterInputInvalidNameAndPhoneNumberAndPasswordConsistsOfOnlyLetters() {
//        registrationForm.inputNameByPhoneNumberAndPhoneNumberAndPasswordForPhoneNumber("test", generatePhoneNumber(), faker.internet().password(false));
//        registrationForm.clickButtonRegistrationByPhoneNumber();
//        assertAll(
//                () -> assertTrue(registrationForm.isButtonRegistrationByPhoneNumberEnabled()),
//                () -> assertEquals("Напишите имя кириллицей", registrationForm.getErrorMessageForNameByPhoneNumberText()),
//                () -> assertEquals("В пароле должны быть цифры и латинские буквы", registrationForm.getErrorMessageForPasswordForPhoneNumberText())
//        );
//    }

//    @Test
//    public void checkAndClickButtonRegistrationByPhoneNumberAfterInputInvalidNameAndPhoneNumberAndPasswordConsistsOfOnlyDigits() {
//        registrationForm.inputNameByPhoneNumberAndPhoneNumberAndPasswordForPhoneNumber("test", generatePhoneNumber(), "1234567890");
//        registrationForm.clickButtonRegistrationByPhoneNumber();
//        assertAll(
//                () -> assertTrue(registrationForm.isButtonRegistrationByPhoneNumberEnabled()),
//                () -> assertEquals("Напишите имя кириллицей", registrationForm.getErrorMessageForNameByPhoneNumberText()),
//                () -> assertEquals("В пароле должны быть цифры и латинские буквы", registrationForm.getErrorMessageForPasswordForPhoneNumberText())
//        );
//    }

//    @Test
//    public void checkAndClickButtonRegistrationByPhoneNumberAfterInputInvalidNameAndPhoneNumberAndShortPassword() {
//        registrationForm.inputNameByPhoneNumberAndPhoneNumberAndPasswordForPhoneNumber("test", generatePhoneNumber(), faker.internet().password(2,7, false, false, true));
//        registrationForm.clickButtonRegistrationByPhoneNumber();
//        assertAll(
//                () -> assertTrue(registrationForm.isButtonRegistrationByPhoneNumberEnabled()),
//                () -> assertEquals("Напишите имя кириллицей", registrationForm.getErrorMessageForNameByPhoneNumberText()),
//                () -> assertEquals("Минимум 8 символов", registrationForm.getErrorMessageForPasswordForPhoneNumberText())
//        );
//    }

    @Test
    public void checkReturnToRegistrationByPhoneNumberAfterGoToRegistrationByEmailOrLogin() {
        registrationForm.clickChooseRegistrationByEmail();
        assertAll(
                () -> assertEquals("false", registrationForm.getChooseRegistrationByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals("true", registrationForm.getChooseRegistrationByEmailAttributeAriaSelectedValue())
        );
        registrationForm.clickChooseRegistrationByPhoneNumber();
        assertAll(
                () -> assertEquals("true", registrationForm.getChooseRegistrationByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals("false", registrationForm.getChooseRegistrationByEmailAttributeAriaSelectedValue())
        );
    }

    @Test
    public void checkTitleButtonsLabelsHelperAttributeOfRegistrationByEmailNames() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        assertAll(
                () -> assertEquals("Регистрация", registrationForm.getTitleRegistrationText()),
                () -> assertEquals("по телефону", registrationForm.getChooseRegistrationByPhoneNumberText()),
                () -> assertEquals("false", registrationForm.getChooseRegistrationByPhoneNumberAttributeAriaSelectedValue()),
                () -> assertEquals("почте", registrationForm.getChooseRegistrationByEmailText()),
                () -> assertEquals("true", registrationForm.getChooseRegistrationByEmailAttributeAriaSelectedValue()),
                () -> assertEquals("Имя на кириллице", registrationForm.getLabelNameByEmailText()),
                () -> assertEquals("Электронная почта", registrationForm.getLabelEmailText()),
                () -> assertEquals("Пароль", registrationForm.getLabelPasswordForEmailText()),
                () -> assertEquals("false", registrationForm.getButtonInvertForEmailAttributeAriaPressedValue()),
                () -> assertEquals("password", registrationForm.getInputPasswordForEmailAttributeTypeValue()),
                () -> assertEquals("Не короче 8 символов и только латиница и цифры", registrationForm.getWarnMessageForPasswordForEmailText()),
                () -> assertEquals("Зарегистрироваться", registrationForm.getButtonRegistrationByEmailText()),
                () -> assertFalse(registrationForm.isButtonRegistrationByEmailEnabled()),
                () -> assertEquals("Вход\nдля тех, кто уже зарегистрирован", registrationForm.getButtonLoginText())
        );
    }

    @Test
    public void checkButtonInverterForEmail() {
        registrationForm.clickChooseRegistrationByEmail();
        waitOfElement(RegistrationFormLocator.BUTTON_INVERTER_FOR_EMAIL);
        registrationForm.clickButtonInvertForEmail();
        assertAll(
                () -> assertEquals("true", registrationForm.getButtonInvertForEmailAttributeAriaPressedValue()),
                () -> assertEquals("text", registrationForm.getInputPasswordForEmailAttributeTypeValue())
        );
    }

    @Test
    public void checkButtonRegistrationByEmailEnabledAfterInputName() {
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputNameByEmail("test");
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled());
    }

    @Test
    public void checkButtonRegistrationByEmailEnabledAfterInputEmail() {
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputEmail(faker.internet().emailAddress());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled());
    }

    @Test
    public void checkButtonRegistrationByEmailEnabledAfterInputPassword() {
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputPasswordForEmail(faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled());
    }

    @Test
    public void checkButtonRegistrationByEmailEnabledAfterInputNameAndEmail() {
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputNameByEmailAndEmail("test", faker.internet().emailAddress());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled());
    }

    @Test
    public void checkButtonRegistrationByEmailEnabledAfterInputNameAndPassword() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputNameByEmailAndPasswordForEmail("test", faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled());
    }

    @Test
    public void checkButtonRegistrationByEmailEnabledAfterInputEmailAndPassword() {
        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputEmailAndPasswordForEmail(faker.internet().emailAddress(), faker.internet().password());
        assertFalse(registrationForm.isButtonRegistrationByEmailEnabled());
    }

    @Test
    public void checkButtonRegistrationByEmailEnabledAfterInputNameAndEmailAndPassword() {
        registrationForm.clickChooseRegistrationByEmail();
        registrationForm.inputNameByEmailAndEmailAndPasswordForEmail("test", faker.internet().emailAddress(), faker.internet().password(true));
        assertTrue(registrationForm.isButtonRegistrationByEmailEnabled());
    }

//    @Test
//    public void checkAndClickButtonRegistrationByEmailEnabledAfterInputInvalidNameAndInvalidEmailAndPasswordConsistsOfOnlyLetters() {
//        registrationForm.clickChooseRegistrationByEmail();
//        registrationForm.inputNameByEmailAndEmailAndPasswordForEmail("test","1@1.1", faker.internet().password(false));
//        registrationForm.clickButtonRegistrationByEmail();
//        assertAll(
//                () -> assertTrue(registrationForm.isButtonRegistrationByEmailEnabled()),
//                () -> assertEquals("Напишите имя кириллицей", registrationForm.getErrorMessageForNameByEmailText()),
//                () -> assertEquals("Введите почту полностью. Например, info@av.by", registrationForm.getErrorMessageForEmailText()),
//                () -> assertEquals("В пароле должны быть цифры и латинские буквы", registrationForm.getErrorMessageForPasswordForEmailText())
//        );
//    }

//    @Test
//    public void checkAndClickButtonRegistrationByEmailEnabledAfterInputInvalidNameAndInvalidEmailAndPasswordConsistsOfOnlyDigits() {
//        waitOfElement(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL);
//        registrationForm.clickChooseRegistrationByEmail();
//        registrationForm.inputNameByEmailAndEmailAndPasswordForEmail("test","1@1.1", "1234567890");
//        registrationForm.clickButtonRegistrationByEmail();
//        assertAll(
//                () -> assertTrue(registrationForm.isButtonRegistrationByEmailEnabled()),
//                () -> assertEquals("Напишите имя кириллицей", registrationForm.getErrorMessageForNameByEmailText()),
//                () -> assertEquals("Введите почту полностью. Например, info@av.by", registrationForm.getErrorMessageForEmailText()),
//                () -> assertEquals("В пароле должны быть цифры и латинские буквы", registrationForm.getErrorMessageForPasswordForEmailText())
//        );
//    }

//    @Test
//    public void checkAndClickButtonRegistrationByEmailEnabledAfterInputInvalidNameAndInvalidEmailAndShortPassword() {
//        registrationForm.clickChooseRegistrationByEmail();
//        registrationForm.inputNameByEmailAndEmailAndPasswordForEmail("test","1@1.1", faker.internet().password(2,7, false, false, true));
//        registrationForm.clickButtonRegistrationByEmail();
//        assertAll(
//                () -> assertTrue(registrationForm.isButtonRegistrationByEmailEnabled()),
//                () -> assertEquals("Напишите имя кириллицей", registrationForm.getErrorMessageForNameByEmailText()),
//                () -> assertEquals("Введите почту полностью. Например, info@av.by", registrationForm.getErrorMessageForEmailText()),
//                () -> assertEquals("В пароле должны быть цифры и латинские буквы", registrationForm.getErrorMessageForPasswordForEmailText())
//        );
//    }

    @Test
    public void checkClickButtonLoginReturnToLoginForm() {
        registrationForm.clickButtonLogin();
        waitOfElement(LoginFormLocator.TITLE_LOGIN_FORM);
        assertEquals("Вход", loginForm.getTitleText());
    }
}
