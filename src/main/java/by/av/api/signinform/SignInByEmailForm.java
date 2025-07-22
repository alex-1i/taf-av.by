package by.av.api.signinform;

import java.util.List;

public class SignInByEmailForm extends SignInForm {

    private static final String URL_SIGN_IN_BY_EMAIL = URL_AUTH + "login/sign-in";
    private static final String RESPONSE_ERRORS_LOGIN = "context.errors['login']";

    public SignInByEmailForm(String email, String password) {
        logger.info("Создание запроса SignInByEmailForm");
        logger.info("Вводимые данные — Email: {}, Password: {}", email, password);

        String body = "{\"login\":\"" + email + "\",\"password\":\"" + password + "\"}";
        response = sendRequest(URL_SIGN_IN_BY_EMAIL, body);
    }

    public List<String> getContextErrorLogin() {
        List<String> errors = response.path(RESPONSE_ERRORS_LOGIN);
        logger.debug("Ошибки поля login: {}", errors);
        return errors;
    }
}
