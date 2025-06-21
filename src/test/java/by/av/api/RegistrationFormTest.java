package by.av.api;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.av.utils.Utils.generatePhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationFormTest {

    Faker faker = new Faker();

    @Test
    public void checkRegistrationByPhoneNumberWithEmptyParameters() {
        RegistrationByPhoneNumberForm registrationByPhoneNumberForm = new RegistrationByPhoneNumberForm("", "", 0, "");

        assertAll(
                () -> assertEquals(400, registrationByPhoneNumberForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", registrationByPhoneNumberForm.getMessage()),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorName().containsAll(List.of("Это обязательное поле!", "Слишком короткое имя"))),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorPassword().containsAll(List.of("Придумайте пароль", "Минимум 8 символов"))),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorPhoneCountry().containsAll(List.of("Заполните поле", "Это обязательное поле!"))),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorPhoneNumber().contains("Это обязательное поле!")),
                () -> assertEquals("Запрос не соответствует правилам валидации", registrationByPhoneNumberForm.getMessageText())
        );
    }

    @Test
    public void checkRegistrationByPhoneNumberWithInvalidParameters() {
        RegistrationByPhoneNumberForm registrationByPhoneNumberForm = new RegistrationByPhoneNumberForm("test", "123", 5, "123");

        assertAll(
                () -> assertEquals(400, registrationByPhoneNumberForm.getStatusCode()),
                () -> assertEquals("exception.request.invalid", registrationByPhoneNumberForm.getMessage()),
                () -> assertEquals("Неверный запрос", registrationByPhoneNumberForm.getMessageText())
        );
    }

    @Test
    public void checkRegistrationByPhoneNumberWithInvalidNameInvalidShortPasswordAndAnotherCountryNumber() {
        RegistrationByPhoneNumberForm registrationByPhoneNumberForm = new RegistrationByPhoneNumberForm("test", "123", 2, generatePhoneNumber());

        assertAll(
                () -> assertEquals(400, registrationByPhoneNumberForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", registrationByPhoneNumberForm.getMessage()),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorName().contains("Напишите имя кириллицей")),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorPassword().containsAll(List.of("В пароле должны быть цифры и латинские буквы", "Минимум 8 символов"))),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorPhoneNumber().contains("Неверно указан номер телефона")),
                () -> assertEquals("Запрос не соответствует правилам валидации", registrationByPhoneNumberForm.getMessageText())
        );
    }

    @Test
    public void checkRegistrationByPhoneNumberWithInvalidNameInvalidPasswordAndPhoneNumber() {
        RegistrationByPhoneNumberForm registrationByPhoneNumberForm = new RegistrationByPhoneNumberForm("test", faker.internet().password(false), "1234567890");

        assertAll(
                () -> assertEquals(400, registrationByPhoneNumberForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", registrationByPhoneNumberForm.getMessage()),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorName().contains("Напишите имя кириллицей")),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorPassword().contains("В пароле должны быть цифры и латинские буквы")),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorPhoneNumber().contains("Неверно указан номер телефона")),
                () -> assertEquals("Запрос не соответствует правилам валидации", registrationByPhoneNumberForm.getMessageText())
        );
    }

    @Test
    public void checkRegistrationByEmailWithEmptyParameters() {
        RegistrationByEmailForm registrationByEmailForm = new RegistrationByEmailForm("", "", "");

        assertAll(
                () -> assertEquals(400, registrationByEmailForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", registrationByEmailForm.getMessage()),
                () -> assertTrue(registrationByEmailForm.getContextErrorEmail().contains("Это обязательное поле!")),
                () -> assertTrue(registrationByEmailForm.getContextErrorName().containsAll(List.of("Это обязательное поле!","Слишком короткое имя"))),
                () -> assertTrue(registrationByEmailForm.getContextErrorPassword().containsAll(List.of("Придумайте пароль", "Минимум 8 символов"))),
                () -> assertEquals("Запрос не соответствует правилам валидации", registrationByEmailForm.getMessageText())
        );
    }

    @Test
    public void checkRegistrationByEmailWithInvalidEmailAndShortNameAndPassword() {
        RegistrationByEmailForm registrationByEmailForm = new RegistrationByEmailForm("1", "т", faker.internet().password(2, 7));

        assertAll(
                () -> assertEquals(400, registrationByEmailForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", registrationByEmailForm.getMessage()),
                () -> assertTrue(registrationByEmailForm.getContextErrorEmail().contains("Введите почту полностью. Например, info@av.by")),
                () -> assertTrue(registrationByEmailForm.getContextErrorName().contains("Слишком короткое имя")),
                () -> assertTrue(registrationByEmailForm.getContextErrorPassword().contains("Минимум 8 символов")),
                () -> assertEquals("Запрос не соответствует правилам валидации", registrationByEmailForm.getMessageText())
        );
    }

    @Test
    public void checkRegistrationByEmailWithInvalidParameters() {
        RegistrationByEmailForm registrationByEmailForm = new RegistrationByEmailForm("1", "test", faker.internet().password(false));

        assertAll(
                () -> assertEquals(400, registrationByEmailForm.getStatusCode()),
                () -> assertEquals("exception.validation.failed", registrationByEmailForm.getMessage()),
                () -> assertTrue(registrationByEmailForm.getContextErrorEmail().contains("Введите почту полностью. Например, info@av.by")),
                () -> assertTrue(registrationByEmailForm.getContextErrorName().contains("Напишите имя кириллицей")),
                () -> assertTrue(registrationByEmailForm.getContextErrorPassword().contains("В пароле должны быть цифры и латинские буквы")),
                () -> assertEquals("Запрос не соответствует правилам валидации", registrationByEmailForm.getMessageText())
        );
    }
}
