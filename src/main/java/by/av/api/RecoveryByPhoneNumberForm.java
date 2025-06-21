package by.av.api;

public class RecoveryByPhoneNumberForm extends SignInForm {

    public RecoveryByPhoneNumberForm(String number) {
        this(number, DEFAULT_COUNTRY_NUMBER);
    }

    public RecoveryByPhoneNumberForm(String number, int countryNumber) {
        String body = "{\"phone\":{\"country\":" + countryNumber + ",\"number\":\"" + number + "\"}}";
        response = sendRequest("https://api.av.by/auth/phone/password-reset-request", body);
    }
}
