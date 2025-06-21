package by.av.api;

import java.util.List;

public class SignInByEmailForm extends SignInForm {

    public SignInByEmailForm(String email, String password) {

        String body = "{\"login\":\"" + email + "\",\"password\":\""+ password + "\"}";
        response = sendRequest("https://api.av.by/auth/login/sign-in", body);
    }

    public List<String> getContextErrorLogin() {
        return response.path("context.errors['login']");
    }
}
