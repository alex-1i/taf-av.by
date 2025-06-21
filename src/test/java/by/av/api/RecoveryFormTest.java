package by.av.api;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.av.utils.Utils.generatePhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

public class RecoveryFormTest {

    Faker faker = new Faker();

    @Test
    public void checkRecoveryByPhoneNumberWithEmptyParameters() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm("", 0);

        assertAll(
                () -> assertEquals(400, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals("exception.validation.failed", recoveryByPhoneNumber.getMessage()),
                () -> assertTrue(recoveryByPhoneNumber.getContextErrorPhoneCountry().containsAll(List.of("Заполните поле", "Это обязательное поле!"))),
                () -> assertTrue(recoveryByPhoneNumber.getContextErrorPhoneNumber().contains("Это обязательное поле!")),
                () -> assertEquals("Запрос не соответствует правилам валидации", recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    public void checkRecoveryByPhoneNumberWithEmptyPhoneNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm("");

        assertAll(
                () -> assertEquals(400, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals("exception.validation.failed", recoveryByPhoneNumber.getMessage()),
                () -> assertTrue(recoveryByPhoneNumber.getContextErrorPhoneNumber().contains("Это обязательное поле!")),
                () -> assertEquals("Запрос не соответствует правилам валидации", recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    public void checkRecoveryByPhoneNumberWithEmptyCountryNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm(generatePhoneNumber(), 0);

        assertAll(
                () -> assertEquals(400, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals("exception.validation.failed", recoveryByPhoneNumber.getMessage()),
                () -> assertTrue(recoveryByPhoneNumber.getContextErrorPhoneCountry().containsAll(List.of("Заполните поле", "Это обязательное поле!"))),
                () -> assertEquals("Запрос не соответствует правилам валидации", recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    public void checkRecoveryByPhoneNumberWithInvalidPhoneNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm("1234567890");

        assertAll(
                () -> assertEquals(400, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals("exception.validation.failed", recoveryByPhoneNumber.getMessage()),
                () -> assertTrue(recoveryByPhoneNumber.getContextErrorPhoneNumber().contains("Неверно указан номер телефона")),
                () -> assertEquals("Запрос не соответствует правилам валидации", recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    public void checkRecoveryByPhoneNumberWithAnotherCountryNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm(generatePhoneNumber(), 2);

        assertAll(
                () -> assertEquals(400, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals("exception.validation.failed", recoveryByPhoneNumber.getMessage()),
                () -> assertTrue(recoveryByPhoneNumber.getContextErrorPhoneNumber().contains("Неверно указан номер телефона")),
                () -> assertEquals("Запрос не соответствует правилам валидации", recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    public void checkRecoveryByPhoneNumberWithInvalidCountryNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm(generatePhoneNumber(), 5);

        assertAll(
                () -> assertEquals(400, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals("exception.request.invalid", recoveryByPhoneNumber.getMessage()),
                () -> assertEquals("Неверный запрос", recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    public void checkRecoveryByPhoneNumberWithUnregisteredPhoneNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm(generatePhoneNumber());

        assertAll(
                () -> assertEquals(400, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals("exception.auth.unknown_phone", recoveryByPhoneNumber.getMessage()),
                () -> assertEquals("Такого пользователя у нас нет. Проверьте указанный номер", recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    public void checkRecoveryByEmailWithEmptyEmail() {
        RecoveryByEmailForm recoveryByEmail = new RecoveryByEmailForm("");

        assertAll(
                () -> assertEquals(400, recoveryByEmail.getStatusCode()),
                () -> assertEquals("exception.validation.failed", recoveryByEmail.getMessage()),
                () -> assertTrue(recoveryByEmail.getContextErrorEmail().contains("Это обязательное поле!")),
                () -> assertEquals("Запрос не соответствует правилам валидации", recoveryByEmail.getMessageText())
        );
    }

    @Test
    public void checkRecoveryByEmailWithInvalidEmail() {
        RecoveryByEmailForm recoveryByEmail = new RecoveryByEmailForm("123");

        assertAll(
                () -> assertEquals(400, recoveryByEmail.getStatusCode()),
                () -> assertEquals("exception.validation.failed", recoveryByEmail.getMessage()),
                () -> assertTrue(recoveryByEmail.getContextErrorEmail().contains("Введите почту полностью. Например, info@av.by")),
                () -> assertEquals("Запрос не соответствует правилам валидации", recoveryByEmail.getMessageText())
        );
    }

    @Test
    public void checkRecoveryByEmailWithUnregisteredEmail() {
        RecoveryByEmailForm recoveryByEmail = new RecoveryByEmailForm(faker.internet().emailAddress());

        assertAll(
                () -> assertEquals(400, recoveryByEmail.getStatusCode()),
                () -> assertEquals("exception.auth.unknown_email", recoveryByEmail.getMessage()),
                () -> assertEquals("Такого пользователя у нас нет. Проверьте указанную почту", recoveryByEmail.getMessageText())
        );
    }
}
