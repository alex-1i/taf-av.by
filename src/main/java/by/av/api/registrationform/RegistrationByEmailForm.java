package by.av.api.registrationform;

import by.av.api.signinform.SignInForm;

public class RegistrationByEmailForm extends SignInForm {

    private static final String URL_REGISTRATION_BY_EMAIL = URL_AUTH + "email/sign-up";

    public RegistrationByEmailForm(String email, String name, String password) {
        logger.info("Создание запроса RegistrationByEmailForm");
        logger.info("Вводимые данные — Email: {}, Name: {}, Password: {}", email, name, password);

        String body = "{\"email\":\"" + email + "\",\"name\":\"" + name + "\",\"password\":\"" + password + "\"}";
        response = sendRequest(URL_REGISTRATION_BY_EMAIL, body);
    }
}
