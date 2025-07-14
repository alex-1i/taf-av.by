package by.av.api;

import by.av.api.expectedMessages.ExpectedMessages;
import by.av.api.registrationform.RegistrationByEmailForm;
import by.av.api.registrationform.RegistrationByPhoneNumberForm;
import by.av.api.signinform.SignInForm;
import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.av.utils.Utils.generateInvalidPhoneNumberOrEmail;
import static by.av.utils.Utils.generatePhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationFormTest {

    private static final int MINIMUM_INSUFFICIENT_PASSWORD_LENGTH = 2;
    private static final int MAXIMUM_INSUFFICIENT_PASSWORD_LENGTH = 7;

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
        RegistrationByPhoneNumberForm registrationByPhoneNumberForm = new RegistrationByPhoneNumberForm(faker.name().firstName(), faker.internet().password(MINIMUM_INSUFFICIENT_PASSWORD_LENGTH, MAXIMUM_INSUFFICIENT_PASSWORD_LENGTH, false, false, false), SignInForm.WRONG_COUNTRY_NUMBER, generateInvalidPhoneNumberOrEmail());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, registrationByPhoneNumberForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_REQUEST_INVALID, registrationByPhoneNumberForm.getMessage()),
                () -> assertEquals(ExpectedMessages.INVALID_REQUEST, registrationByPhoneNumberForm.getMessageText())
        );
    }

    @Test
    public void checkRegistrationByPhoneNumberWithInvalidNameInvalidShortPasswordAndAnotherCountryNumber() {
        RegistrationByPhoneNumberForm registrationByPhoneNumberForm = new RegistrationByPhoneNumberForm(faker.name().firstName(), faker.internet().password(MINIMUM_INSUFFICIENT_PASSWORD_LENGTH, MAXIMUM_INSUFFICIENT_PASSWORD_LENGTH, false, false, false), SignInForm.ANOTHER_COUNTRY_NUMBER, generatePhoneNumber());

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
        RegistrationByPhoneNumberForm registrationByPhoneNumberForm = new RegistrationByPhoneNumberForm(faker.name().firstName(), faker.internet().password(false), generateInvalidPhoneNumberOrEmail());

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
        RegistrationByEmailForm registrationByEmailForm = new RegistrationByEmailForm(generateInvalidPhoneNumberOrEmail(), String.valueOf(faker.lorem().character()), faker.internet().password(MINIMUM_INSUFFICIENT_PASSWORD_LENGTH, MAXIMUM_INSUFFICIENT_PASSWORD_LENGTH));

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
        RegistrationByEmailForm registrationByEmailForm = new RegistrationByEmailForm(generateInvalidPhoneNumberOrEmail(), faker.name().firstName(), faker.internet().password(false));

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
