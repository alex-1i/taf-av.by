package by.av.api;

import by.av.api.expectedMessages.ExpectedMessages;
import by.av.api.recoveryform.RecoveryByEmailForm;
import by.av.api.recoveryform.RecoveryByPhoneNumberForm;
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
@Feature("Проверка формы восстановления пароля")
@DisplayName("Тесты формы восстановления пароля по номеру телефона и по почте")
public class RecoveryFormTest {

    Faker faker = new Faker();

    @Test
    @DisplayName("Проверка восстановления пароля по номеру телефона при вводе пустых значений всех параметров")
    public void checkRecoveryByPhoneNumberWithEmptyParameters() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm("", 0);

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, recoveryByPhoneNumber.getMessage()),
                () -> assertTrue(recoveryByPhoneNumber.getContextErrorPhoneCountry().containsAll(List.of(ExpectedMessages.FILL_IN_FIELD, ExpectedMessages.REQUIRED_FIELD))),
                () -> assertTrue(recoveryByPhoneNumber.getContextErrorPhoneNumber().contains(ExpectedMessages.REQUIRED_FIELD)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка восстановления пароля по номеру телефона при вводе пустого значения телефона")
    public void checkRecoveryByPhoneNumberWithEmptyPhoneNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm("");

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, recoveryByPhoneNumber.getMessage()),
                () -> assertTrue(recoveryByPhoneNumber.getContextErrorPhoneNumber().contains(ExpectedMessages.REQUIRED_FIELD)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка восстановления пароля по номеру телефона при вводе пустого значения кода страны")
    public void checkRecoveryByPhoneNumberWithEmptyCountryNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm(generatePhoneNumber(), 0);

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, recoveryByPhoneNumber.getMessage()),
                () -> assertTrue(recoveryByPhoneNumber.getContextErrorPhoneCountry().containsAll(List.of(ExpectedMessages.FILL_IN_FIELD, ExpectedMessages.REQUIRED_FIELD))),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка восстановления пароля по номеру телефона при вводе невалидного номера телефона")
    public void checkRecoveryByPhoneNumberWithInvalidPhoneNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm(generateInvalidInputData());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, recoveryByPhoneNumber.getMessage()),
                () -> assertTrue(recoveryByPhoneNumber.getContextErrorPhoneNumber().contains(ExpectedMessages.PHONE_NUMBER_IS_INCORRECT)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка восстановления пароля по номеру телефона при вводе кода другой страны")
    public void checkRecoveryByPhoneNumberWithAnotherCountryNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm(generatePhoneNumber(), SignInForm.ANOTHER_COUNTRY_NUMBER);

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, recoveryByPhoneNumber.getMessage()),
                () -> assertTrue(recoveryByPhoneNumber.getContextErrorPhoneNumber().contains(ExpectedMessages.PHONE_NUMBER_IS_INCORRECT)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка восстановления пароля по номеру телефона при вводе невалидного кода страны")
    public void checkRecoveryByPhoneNumberWithInvalidCountryNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm(generatePhoneNumber(), SignInForm.WRONG_COUNTRY_NUMBER);

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_REQUEST_INVALID, recoveryByPhoneNumber.getMessage()),
                () -> assertEquals(ExpectedMessages.INVALID_REQUEST, recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка восстановления пароля по номеру телефона при вводе незарегистрированного номера телефона")
    public void checkRecoveryByPhoneNumberWithUnregisteredPhoneNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm(generatePhoneNumber());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_AUTH_UNKNOWN_PHONE, recoveryByPhoneNumber.getMessage()),
                () -> assertEquals(ExpectedMessages.CHECK_PHONE_NUMBER, recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка восстановления пароля по почте телефона при вводе пустого значения почты")
    public void checkRecoveryByEmailWithEmptyEmail() {
        RecoveryByEmailForm recoveryByEmail = new RecoveryByEmailForm("");

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByEmail.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, recoveryByEmail.getMessage()),
                () -> assertTrue(recoveryByEmail.getContextErrorEmail().contains(ExpectedMessages.REQUIRED_FIELD)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, recoveryByEmail.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка восстановления пароля по почте телефона при вводе невалидного значения почты")
    public void checkRecoveryByEmailWithInvalidEmail() {
        RecoveryByEmailForm recoveryByEmail = new RecoveryByEmailForm(generateInvalidInputData());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByEmail.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, recoveryByEmail.getMessage()),
                () -> assertTrue(recoveryByEmail.getContextErrorEmail().contains(ExpectedMessages.INPUT_FULL_EMAIL)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, recoveryByEmail.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка восстановления пароля по почте телефона при вводе незарегистрированного значения почты")
    public void checkRecoveryByEmailWithUnregisteredEmail() {
        RecoveryByEmailForm recoveryByEmail = new RecoveryByEmailForm(faker.internet().emailAddress());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByEmail.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_AUTH_UNKNOWN_EMAIL, recoveryByEmail.getMessage()),
                () -> assertEquals(ExpectedMessages.CHECK_EMAIL, recoveryByEmail.getMessageText())
        );
    }
}
