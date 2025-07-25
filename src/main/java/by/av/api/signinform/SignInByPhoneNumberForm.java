package by.av.api.signinform;

import java.util.List;

public class SignInByPhoneNumberForm extends SignInForm {

    private static final String URL_SIGN_IN_BY_PHONE = URL_AUTH + "phone/sign-in";
    private static final String RESPONSE_ERRORS_PHONE = "context.errors['phone']";

    public SignInByPhoneNumberForm(String number, String password) {
        this(number, password, DEFAULT_COUNTRY_NUMBER);
    }

    public SignInByPhoneNumberForm(String number, String password, int countryNumber) {
        logger.info("Создание запроса SignInByPhoneNumberForm");
        logger.info("Вводимые данные — Номер: {}, Пароль: {}, Код страны: {}", number, password, countryNumber);

        String body = "{\n" +
                "    \"password\": \"" + password + "\",\n" +
                "    \"phone\": {\n" +
                "        \"country\": " + countryNumber + ",\n" +
                "        \"number\": \"" + number + "\"\n" +
                "    }\n" +
                "}";
        response = sendRequest(URL_SIGN_IN_BY_PHONE, body);
    }

    public List<String> getContextErrorPhone() {
        List<String> errors = response.path(RESPONSE_ERRORS_PHONE);
        logger.debug("Ошибки поля phone: {}", errors);
        return errors;
    }
}
