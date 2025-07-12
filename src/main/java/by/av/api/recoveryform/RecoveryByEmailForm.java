package by.av.api.recoveryform;

import by.av.api.signinform.SignInForm;

public class RecoveryByEmailForm extends SignInForm {

    private static final String URL_RECOVERY_BY_EMAIL = URL_AUTH + "email/password-reset-request";

    public RecoveryByEmailForm(String email) {
        String body = "{\"email\":\"" + email + "\"}";
        response = sendRequest(URL_RECOVERY_BY_EMAIL, body);
    }
}
