package by.av.api;

public class RegistrationByEmailForm extends SignInForm {

    public RegistrationByEmailForm(String email, String name, String password) {
        String body = "{\"email\":\"" + email + "\",\"name\":\"" + name + "\",\"password\":\"" + password + "\"}";
        response = sendRequest("https://api.av.by/auth/email/sign-up", body);
    }
}
