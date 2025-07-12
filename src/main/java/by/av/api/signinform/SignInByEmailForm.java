package by.av.api.signinform;

import java.util.List;

public class SignInByEmailForm extends SignInForm {

    private static final String URL_SIGN_IN_BY_EMAIL = URL_AUTH + "login/sign-in";
    public SignInByEmailForm(String email, String password) {

        String body = "{\"login\":\"" + email + "\",\"password\":\""+ password + "\"}";
        response = sendRequest(URL_SIGN_IN_BY_EMAIL, body);
    }

    public List<String> getContextErrorLogin() {
        return response.path("context.errors['login']");
    }
}
