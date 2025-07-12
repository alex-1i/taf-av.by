package by.av.api;

import by.av.api.expectedMessages.ExpectedMessages;
import by.av.api.signinform.SignInByEmailForm;
import by.av.api.signinform.SignInByPhoneNumberForm;
import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.av.utils.Utils.generateInvalidPhoneNumberOrEmail;
import static by.av.utils.Utils.generatePhoneNumber;
import static org.junit.jupiter.api.Assertions.*;

public class SignInFormTest {

    Faker faker = new Faker();

    @Test
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
    public void checkSignInByPhoneNumberWithAnotherCountryNumber() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm(generatePhoneNumber(), faker.internet().password(), 2);

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorPhoneNumber().contains(ExpectedMessages.PHONE_NUMBER_IS_INCORRECT)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, signInForm.getMessageText())
        );
    }

    @Test
    public void checkSignInByPhoneNumberWithInvalidCountryNumber() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm(generatePhoneNumber(), faker.internet().password(), 5);

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_REQUEST_INVALID, signInForm.getMessage()),
                () -> assertEquals(ExpectedMessages.INVALID_REQUEST, signInForm.getMessageText())
        );
    }

    @Test
    public void checkSignInByPhoneNumberWithInvalidPhoneNumber() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm(generateInvalidPhoneNumberOrEmail(), faker.internet().password());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_VALIDATION_FAILED, signInForm.getMessage()),
                () -> assertTrue(signInForm.getContextErrorPhoneNumber().contains(ExpectedMessages.PHONE_NUMBER_IS_INCORRECT)),
                () -> assertEquals(ExpectedMessages.REQUEST_DOES_NOT_MEET_VALIDATION_RULES, signInForm.getMessageText())
        );
    }

    @Test
    public void checkSignInByPhoneNumberWithUnregisteredPhoneNumberOrWrongPassword() {
        SignInByPhoneNumberForm signInForm = new SignInByPhoneNumberForm(generatePhoneNumber(), faker.internet().password());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_AUTH_INVALID_SIGN_IN_BY_PHONE, signInForm.getMessage()),
                () -> assertEquals(ExpectedMessages.INVALID_PHONE_OR_PASSWORD, signInForm.getMessageText())
        );
    }

    @Test
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
    public void checkSignInByEmailWithUnregisteredEmailOrWrongPassword() {
        SignInByEmailForm signInForm = new SignInByEmailForm(faker.internet().emailAddress(), faker.internet().password());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, signInForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_AUTH_INVALID_SIGN_IN, signInForm.getMessage()),
                () -> assertEquals(ExpectedMessages.INVALID_LOGIN_OR_PASSWORD, signInForm.getMessageText())
        );
    }
}
