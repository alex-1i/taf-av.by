package by.av.api;

public class RecoveryByEmailForm extends SignInForm {

    public RecoveryByEmailForm(String email) {
        String body = "{\"email\":\"" + email + "\"}";
        response = sendRequest("https://api.av.by/auth/email/password-reset-request", body);
    }
}
