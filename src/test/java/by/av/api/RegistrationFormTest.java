package by.av.api;

import by.av.api.expectedMessages.ExpectedMessages;
import by.av.api.registrationform.RegistrationByEmailForm;
import by.av.api.registrationform.RegistrationByPhoneNumberForm;
import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.av.utils.Utils.generateInvalidPhoneNumber;
import static by.av.utils.Utils.generatePhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationFormTest {

    Faker faker = new Faker();

    @Test
    public void checkRegistrationByPhoneNumberWithEmptyParameters() {
        RegistrationByPhoneNumberForm registrationByPhoneNumberForm = new RegistrationByPhoneNumberForm("", "", 0, "");

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, registrationByPhoneNumberForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, registrationByPhoneNumberForm.getMessage()),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorName().containsAll(List.of(ExpectedMessages.REQUIRED_FIELD, ExpectedMessages.TOO_SHORT_NAME))),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorPassword().containsAll(List.of(ExpectedMessages.CREATE_PASSWORD, ExpectedMessages.MINIMUM_8_CHARS))),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorPhoneCountry().containsAll(List.of(ExpectedMessages.FILL_IN_FIELD, ExpectedMessages.REQUIRED_FIELD))),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorPhoneNumber().contains(ExpectedMessages.REQUIRED_FIELD)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, registrationByPhoneNumberForm.getMessageText())
        );
    }

    @Test
    public void checkRegistrationByPhoneNumberWithInvalidParameters() {
        RegistrationByPhoneNumberForm registrationByPhoneNumberForm = new RegistrationByPhoneNumberForm(faker.name().firstName(), faker.internet().password(2, 7, false, false, false), 5, generateInvalidPhoneNumber());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, registrationByPhoneNumberForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_REQUEST_INVALID, registrationByPhoneNumberForm.getMessage()),
                () -> assertEquals(ExpectedMessages.INVALID_REQUEST, registrationByPhoneNumberForm.getMessageText())
        );
    }

    @Test
    public void checkRegistrationByPhoneNumberWithInvalidNameInvalidShortPasswordAndAnotherCountryNumber() {
        RegistrationByPhoneNumberForm registrationByPhoneNumberForm = new RegistrationByPhoneNumberForm(faker.name().firstName(), faker.internet().password(2, 7, false, false, false), 2, generatePhoneNumber());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, registrationByPhoneNumberForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, registrationByPhoneNumberForm.getMessage()),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorName().contains(ExpectedMessages.WRITE_NAME_IN_CYRILLIC)),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorPassword().containsAll(List.of(ExpectedMessages.PASSWORD_MUST_CONTAINS, ExpectedMessages.MINIMUM_8_CHARS))),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorPhoneNumber().contains(ExpectedMessages.PHONE_NUMBER_IS_INCORRECT)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, registrationByPhoneNumberForm.getMessageText())
        );
    }

    @Test
    public void checkRegistrationByPhoneNumberWithInvalidNameInvalidPasswordAndPhoneNumber() {
        RegistrationByPhoneNumberForm registrationByPhoneNumberForm = new RegistrationByPhoneNumberForm(faker.name().firstName(), faker.internet().password(false), generateInvalidPhoneNumber());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, registrationByPhoneNumberForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, registrationByPhoneNumberForm.getMessage()),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorName().contains(ExpectedMessages.WRITE_NAME_IN_CYRILLIC)),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorPassword().contains(ExpectedMessages.PASSWORD_MUST_CONTAINS)),
                () -> assertTrue(registrationByPhoneNumberForm.getContextErrorPhoneNumber().contains(ExpectedMessages.PHONE_NUMBER_IS_INCORRECT)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, registrationByPhoneNumberForm.getMessageText())
        );
    }

    @Test
    public void checkRegistrationByEmailWithEmptyParameters() {
        RegistrationByEmailForm registrationByEmailForm = new RegistrationByEmailForm("", "", "");

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, registrationByEmailForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, registrationByEmailForm.getMessage()),
                () -> assertTrue(registrationByEmailForm.getContextErrorEmail().contains(ExpectedMessages.REQUIRED_FIELD)),
                () -> assertTrue(registrationByEmailForm.getContextErrorName().containsAll(List.of(ExpectedMessages.REQUIRED_FIELD,ExpectedMessages.TOO_SHORT_NAME))),
                () -> assertTrue(registrationByEmailForm.getContextErrorPassword().containsAll(List.of(ExpectedMessages.CREATE_PASSWORD, ExpectedMessages.MINIMUM_8_CHARS))),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, registrationByEmailForm.getMessageText())
        );
    }

    @Test
    public void checkRegistrationByEmailWithInvalidEmailAndShortNameAndPassword() {
        RegistrationByEmailForm registrationByEmailForm = new RegistrationByEmailForm(generateInvalidPhoneNumber(), String.valueOf(faker.lorem().character()), faker.internet().password(2, 7));

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, registrationByEmailForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, registrationByEmailForm.getMessage()),
                () -> assertTrue(registrationByEmailForm.getContextErrorEmail().contains(ExpectedMessages.INPUT_FULL_EMAIL)),
                () -> assertTrue(registrationByEmailForm.getContextErrorName().contains(ExpectedMessages.TOO_SHORT_NAME)),
                () -> assertTrue(registrationByEmailForm.getContextErrorPassword().contains(ExpectedMessages.MINIMUM_8_CHARS)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, registrationByEmailForm.getMessageText())
        );
    }

    @Test
    public void checkRegistrationByEmailWithInvalidParameters() {
        RegistrationByEmailForm registrationByEmailForm = new RegistrationByEmailForm(generateInvalidPhoneNumber(), faker.name().firstName(), faker.internet().password(false));

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, registrationByEmailForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, registrationByEmailForm.getMessage()),
                () -> assertTrue(registrationByEmailForm.getContextErrorEmail().contains(ExpectedMessages.INPUT_FULL_EMAIL)),
                () -> assertTrue(registrationByEmailForm.getContextErrorName().contains(ExpectedMessages.WRITE_NAME_IN_CYRILLIC)),
                () -> assertTrue(registrationByEmailForm.getContextErrorPassword().contains(ExpectedMessages.PASSWORD_MUST_CONTAINS)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, registrationByEmailForm.getMessageText())
        );
    }
}
