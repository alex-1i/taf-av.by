package by.av.api.signinform;

import java.util.List;

public class SignInByEmailForm extends SignInForm {

    private static final String URL_SIGN_IN_BY_EMAIL = URL_AUTH + "login/sign-in";
    private static final String RESPONSE_ERRORS_LOGIN = "context.errors['login']";
    public SignInByEmailForm(String email, String password) {

        String body = "{\"login\":\"" + email + "\",\"password\":\""+ password + "\"}";
        response = sendRequest(URL_SIGN_IN_BY_EMAIL, body);
    }

    public List<String> getContextErrorLogin() {
        return response.path(RESPONSE_ERRORS_LOGIN);
    }
}
