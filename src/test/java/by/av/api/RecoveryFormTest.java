package by.av.api;

import by.av.api.expectedMessages.ExpectedMessages;
import by.av.api.recoveryform.RecoveryByEmailForm;
import by.av.api.recoveryform.RecoveryByPhoneNumberForm;
import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.av.utils.Utils.generateInvalidPhoneNumber;
import static by.av.utils.Utils.generatePhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

public class RecoveryFormTest {

    Faker faker = new Faker();

    @Test
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
    public void checkRecoveryByPhoneNumberWithInvalidPhoneNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm(generateInvalidPhoneNumber());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, recoveryByPhoneNumber.getMessage()),
                () -> assertTrue(recoveryByPhoneNumber.getContextErrorPhoneNumber().contains(ExpectedMessages.PHONE_NUMBER_IS_INCORRECT)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    public void checkRecoveryByPhoneNumberWithAnotherCountryNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm(generatePhoneNumber(), 2);

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, recoveryByPhoneNumber.getMessage()),
                () -> assertTrue(recoveryByPhoneNumber.getContextErrorPhoneNumber().contains(ExpectedMessages.PHONE_NUMBER_IS_INCORRECT)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    public void checkRecoveryByPhoneNumberWithInvalidCountryNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm(generatePhoneNumber(), 5);

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_REQUEST_INVALID, recoveryByPhoneNumber.getMessage()),
                () -> assertEquals(ExpectedMessages.INVALID_REQUEST, recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
    public void checkRecoveryByPhoneNumberWithUnregisteredPhoneNumber() {
        RecoveryByPhoneNumberForm recoveryByPhoneNumber = new RecoveryByPhoneNumberForm(generatePhoneNumber());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByPhoneNumber.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_AUTH_UNKNOWN_PHONE, recoveryByPhoneNumber.getMessage()),
                () -> assertEquals(ExpectedMessages.CHECK_PHONE_NUMBER, recoveryByPhoneNumber.getMessageText())
        );
    }

    @Test
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
    public void checkRecoveryByEmailWithInvalidEmail() {
        RecoveryByEmailForm recoveryByEmail = new RecoveryByEmailForm(generateInvalidPhoneNumber());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByEmail.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, recoveryByEmail.getMessage()),
                () -> assertTrue(recoveryByEmail.getContextErrorEmail().contains(ExpectedMessages.INPUT_FULL_EMAIL)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, recoveryByEmail.getMessageText())
        );
    }

    @Test
    public void checkRecoveryByEmailWithUnregisteredEmail() {
        RecoveryByEmailForm recoveryByEmail = new RecoveryByEmailForm(faker.internet().emailAddress());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, recoveryByEmail.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_AUTH_UNKNOWN_EMAIL, recoveryByEmail.getMessage()),
                () -> assertEquals(ExpectedMessages.CHECK_EMAIL, recoveryByEmail.getMessageText())
        );
    }
}
