package by.av.api;

import by.av.api.expectedMessages.ExpectedMessages;
import by.av.api.signinform.SignInByEmailForm;
import by.av.api.signinform.SignInByPhoneNumberForm;
import by.av.api.signinform.SignInForm;
import com.github.javafaker.Faker;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.av.utils.Utils.generateInvalidInputData;
import static by.av.utils.Utils.generatePhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

@Epic("API")
@Feature("Проверка формы входа")
@DisplayName("Тесты формы входа по номеру телефона и по почте")
public class SignInFormTest {

    Faker faker = new Faker();

    @Test
    @DisplayName("Проверка входа по номеру телефона при вводе пустых значений всех параметров")
    public void checkSignInByPhoneNumberWithEmptyParameters() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm("", "", 0);

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorPhone().contains(ExpectedMessages.FILL_IN_BOTH_FIELDS)),
                () -> assertTrue(signInForm.getContextErrorPhoneNumber().contains(ExpectedMessages.REQUIRED_FIELD)),
                () -> assertTrue(signInForm.getContextErrorPhoneCountry().containsAll(List.of(ExpectedMessages.FILL_IN_FIELD, ExpectedMessages.REQUIRED_FIELD))),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, signInForm.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка входа по номеру телефона при вводе пустых значений номера телефона и пароля")
    public void checkSignInByPhoneNumberWithEmptyPhoneNumberAndPassword() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm("", "");

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorPhone().contains(ExpectedMessages.FILL_IN_BOTH_FIELDS)),
                () -> assertTrue(signInForm.getContextErrorPhoneNumber().contains(ExpectedMessages.REQUIRED_FIELD)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, signInForm.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка входа по номеру телефона при вводе пустого значения номера телефона")
    public void checkSignInByPhoneNumberWithEmptyPhoneNumber() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm("", faker.internet().password());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorPhoneNumber().contains(ExpectedMessages.REQUIRED_FIELD)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, signInForm.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка входа по номеру телефона при вводе пустого значения пароля")
    public void checkSignInByPhoneNumberWithEmptyPassword() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm(generatePhoneNumber(), "");

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorPhone().contains(ExpectedMessages.FILL_IN_BOTH_FIELDS)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, signInForm.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка входа по номеру телефона при вводе значения кода другой страны")
    public void checkSignInByPhoneNumberWithAnotherCountryNumber() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm(generatePhoneNumber(), faker.internet().password(), SignInForm.ANOTHER_COUNTRY_NUMBER);

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorPhoneNumber().contains(ExpectedMessages.PHONE_NUMBER_IS_INCORRECT)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, signInForm.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка входа по номеру телефона при вводе невалидного значения кода страны")
    public void checkSignInByPhoneNumberWithInvalidCountryNumber() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm(generatePhoneNumber(), faker.internet().password(), SignInForm.WRONG_COUNTRY_NUMBER);

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_REQUEST_INVALID, signInForm.getMessage()),
                () -> assertEquals(ExpectedMessages.INVALID_REQUEST, signInForm.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка входа по номеру телефона при вводе невалидного значения номера телефона")
    public void checkSignInByPhoneNumberWithInvalidPhoneNumber() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm(generateInvalidInputData(), faker.internet().password());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorPhoneNumber().contains(ExpectedMessages.PHONE_NUMBER_IS_INCORRECT)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, signInForm.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка входа по номеру телефона при вводе  значения незарегистрированного телефона или неверного пароля")
    public void checkSignInByPhoneNumberWithUnregisteredPhoneNumberOrWrongPassword() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm(generatePhoneNumber(), faker.internet().password());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_AUTH_INVALID_SIGN_IN_BY_PHONE, signInForm.getMessage()),
                () -> assertEquals(ExpectedMessages.INVALID_PHONE_OR_PASSWORD, signInForm.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка входа по почте при вводе пустых значений всех параметров")
    public void checkSignInByEmailWithEmptyParameters() {
        SignInByEmailForm signInForm = new SignInByEmailForm("", "");

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorLogin().contains(ExpectedMessages.FILL_IN_BOTH_FIELDS)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, signInForm.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка входа по почте при вводе пустого значения почты")
    public void checkSignInByEmailWithEmptyEmail() {
        SignInByEmailForm signInForm = new SignInByEmailForm("", faker.internet().password());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorLogin().contains(ExpectedMessages.FILL_IN_BOTH_FIELDS)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, signInForm.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка входа по почте при вводе пустого значения пароля")
    public void checkSignInByEmailWithEmptyPassword() {
        SignInByEmailForm signInForm = new SignInByEmailForm(faker.internet().emailAddress(), "");

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorLogin().contains(ExpectedMessages.FILL_IN_BOTH_FIELDS)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, signInForm.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка входа по почте при вводе незарегистрированного значения почты или неверного пароля")
    public void checkSignInByEmailWithUnregisteredEmailOrWrongPassword() {
        SignInByEmailForm signInForm = new SignInByEmailForm(faker.internet().emailAddress(), faker.internet().password());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_AUTH_INVALID_SIGN_IN, signInForm.getMessage()),
                () -> assertEquals(ExpectedMessages.INVALID_LOGIN_OR_PASSWORD, signInForm.getMessageText())
        );
    }
}
