package by.av.api;

import by.av.api.expectedMessages.ExpectedMessages;
import by.av.api.registrationform.RegistrationByEmailForm;
import by.av.api.registrationform.RegistrationByPhoneNumberForm;
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
@Feature("Проверка формы регистрации")
@DisplayName("Тесты формы регистрации по номеру телефона и по почте")
public class RegistrationFormTest {

    private static final int MINIMUM_INSUFFICIENT_PASSWORD_LENGTH = 2;
    private static final int MAXIMUM_INSUFFICIENT_PASSWORD_LENGTH = 7;

    Faker faker = new Faker();

    @Test
    @DisplayName("Проверка регистрации по номеру телефона при вводе пустых значений всех параметров")
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
    @DisplayName("Проверка регистрации по номеру телефона при вводе невалидных значений всех параметров")
    public void checkRegistrationByPhoneNumberWithInvalidParameters() {
        RegistrationByPhoneNumberForm registrationByPhoneNumberForm = new RegistrationByPhoneNumberForm(faker.name().firstName(), faker.internet().password(MINIMUM_INSUFFICIENT_PASSWORD_LENGTH, MAXIMUM_INSUFFICIENT_PASSWORD_LENGTH, false, false, false), SignInForm.WRONG_COUNTRY_NUMBER, generateInvalidInputData());

        assertAll(
                () -> assertEquals(HttpStatus.SC_BAD_REQUEST, registrationByPhoneNumberForm.getStatusCode()),
                () -> assertEquals(ExpectedMessages.EXCEPTION_REQUEST_INVALID, registrationByPhoneNumberForm.getMessage()),
                () -> assertEquals(ExpectedMessages.INVALID_REQUEST, registrationByPhoneNumberForm.getMessageText())
        );
    }

    @Test
    @DisplayName("Проверка регистрации по номеру телефона при вводе невалидных значений имени, короткого пароля и кода другой страны")
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
    @DisplayName("Проверка регистрации по номеру телефона при вводе невалидных значений имени, пароля и номера телефона")
    public void checkRegistrationByPhoneNumberWithInvalidNameInvalidPasswordAndPhoneNumber() {
        RegistrationByPhoneNumberForm registrationByPhoneNumberForm = new RegistrationByPhoneNumberForm(faker.name().firstName(), faker.internet().password(false), generateInvalidInputData());

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
    @DisplayName("Проверка регистрации по почте при вводе пустых значений всех параметров")
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
    @DisplayName("Проверка регистрации по почте при вводе невалидных значений почты, короткого имени и пароля")
    public void checkRegistrationByEmailWithInvalidEmailAndShortNameAndPassword() {
        RegistrationByEmailForm registrationByEmailForm = new RegistrationByEmailForm(generateInvalidInputData(), String.valueOf(faker.lorem().character()), faker.internet().password(MINIMUM_INSUFFICIENT_PASSWORD_LENGTH, MAXIMUM_INSUFFICIENT_PASSWORD_LENGTH));

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
    @DisplayName("Проверка регистрации по почте при вводе невалидных значений всех параметров")
    public void checkRegistrationByEmailWithInvalidParameters() {
        RegistrationByEmailForm registrationByEmailForm = new RegistrationByEmailForm(generateInvalidInputData(), faker.name().firstName(), faker.internet().password(false));

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
