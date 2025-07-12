package by.av.api.registrationform;

import by.av.api.signinform.SignInForm;

public class RegistrationByPhoneNumberForm extends SignInForm {

    private static final String URL_REGISTRATION_BY_PHONE = URL_AUTH + "phone/sign-up";
    public RegistrationByPhoneNumberForm(String name, String password, String phoneNumber) {
        this(name, password, DEFAULT_COUNTRY_NUMBER, phoneNumber);
    }

    public RegistrationByPhoneNumberForm(String name, String password, int countryNumber, String phoneNumber) {
        String body = "{\"name\":\"" + name + "\",\"password\":\"" + password + "\",\"phone\":{\"country\":" + countryNumber + ",\"number\":\"" + phoneNumber + "\"}}";
        response = sendRequest(URL_REGISTRATION_BY_PHONE, body);
    }
}
