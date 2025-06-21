package by.av.api;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.av.utils.Utils.generatePhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

public class SignInFormTest {

    Faker faker = new Faker();

    @Test
    public void checkSignInByPhoneNumberWithEmptyParameters() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm("", "", 0);

        assertAll(
                () -> assertEquals(400, signInForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorPhone().contains("Заполните оба поля")),
                () -> assertTrue(signInForm.getContextErrorPhoneNumber().contains("Это обязательное поле!")),
                () -> assertTrue(signInForm.getContextErrorPhoneCountry().containsAll(List.of("Заполните поле", "Это обязательное поле!"))),
                () -> assertEquals("Запрос не соответствует правилам валидации", signInForm.getMessageText())
        );
    }

    @Test
    public void checkSignInByPhoneNumberWithEmptyPhoneNumberAndPassword() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm("", "");

        assertAll(
                () -> assertEquals(400, signInForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorPhone().contains("Заполните оба поля")),
                () -> assertTrue(signInForm.getContextErrorPhoneNumber().contains("Это обязательное поле!")),
                () -> assertEquals("Запрос не соответствует правилам валидации", signInForm.getMessageText())
        );
    }

    @Test
    public void checkSignInByPhoneNumberWithEmptyPhoneNumber() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm("", faker.internet().password());

        assertAll(
                () -> assertEquals(400, signInForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorPhoneNumber().contains("Это обязательное поле!")),
                () -> assertEquals("Запрос не соответствует правилам валидации", signInForm.getMessageText())
        );
    }

    @Test
    public void checkSignInByPhoneNumberWithEmptyPassword() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm(generatePhoneNumber(), "");

        assertAll(
                () -> assertEquals(400, signInForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorPhone().contains("Заполните оба поля")),
                () -> assertEquals("Запрос не соответствует правилам валидации", signInForm.getMessageText())
        );
    }

    @Test
    public void checkSignInByPhoneNumberWithAnotherCountryNumber() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm(generatePhoneNumber(), faker.internet().password(), 2);

        assertAll(
                () -> assertEquals(400, signInForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorPhoneNumber().contains("Неверно указан номер телефона")),
                () -> assertEquals("Запрос не соответствует правилам валидации", signInForm.getMessageText())
        );
    }

    @Test
    public void checkSignInByPhoneNumberWithInvalidCountryNumber() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm(generatePhoneNumber(), faker.internet().password(), 5);

        assertAll(
                () -> assertEquals(400, signInForm.getStatusCode()),
                () -> assertEquals("exception.request.invalid", signInForm.getMessage()),
                () -> assertEquals("Неверный запрос", signInForm.getMessageText())
        );
    }

    @Test
    public void checkSignInByPhoneNumberWithInvalidPhoneNumber() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm("1234567890", faker.internet().password());

        assertAll(
                () -> assertEquals(400, signInForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorPhoneNumber().contains("Неверно указан номер телефона")),
                () -> assertEquals("Запрос не соответствует правилам валидации", signInForm.getMessageText())
        );
    }

    @Test
    public void checkSignInByPhoneNumberWithUnregisteredPhoneNumberOrWrongPassword() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm(generatePhoneNumber(), faker.internet().password());

        assertAll(
                () -> assertEquals(400, signInForm.getStatusCode()),
                () -> assertEquals("exception.auth.invalid_sign_in_by_phone", signInForm.getMessage()),
                () -> assertEquals("Неверный телефон или пароль. Если забыли пароль, восстановите его", signInForm.getMessageText())
        );
    }

    @Test
    public void checkSignInByEmailWithEmptyParameters() {
        SignInByEmailForm signInForm = new SignInByEmailForm("", "");

        assertAll(
                () -> assertEquals(400, signInForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorLogin().contains("Заполните оба поля")),
                () -> assertEquals("Запрос не соответствует правилам валидации", signInForm.getMessageText())
        );
    }

    @Test
    public void checkSignInByEmailWithEmptyEmail() {
        SignInByEmailForm signInForm = new SignInByEmailForm("", faker.internet().password());

        assertAll(
                () -> assertEquals(400, signInForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorLogin().contains("Заполните оба поля")),
                () -> assertEquals("Запрос не соответствует правилам валидации", signInForm.getMessageText())
        );
    }

    @Test
    public void checkSignInByEmailWithEmptyPassword() {
        SignInByEmailForm signInForm = new SignInByEmailForm(faker.internet().emailAddress(), "");

        assertAll(
                () -> assertEquals(400, signInForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorLogin().contains("Заполните оба поля")),
                () -> assertEquals("Запрос не соответствует правилам валидации", signInForm.getMessageText())
        );
    }

    @Test
    public void checkSignInByEmailWithUnregisteredEmailOrWrongPassword() {
        SignInByEmailForm signInForm = new SignInByEmailForm(faker.internet().emailAddress(), faker.internet().password());

        assertAll(
                () -> assertEquals(400, signInForm.getStatusCode()),
                () -> assertEquals("exception.auth.invalid_sign_in", signInForm.getMessage()),
                () -> assertEquals("Неверный логин или пароль. Если забыли пароль, восстановите его", signInForm.getMessageText())
        );
    }
}
