package by.av.api;

public class RegistrationByPhoneNumberForm extends SignInForm {

    public RegistrationByPhoneNumberForm(String name, String password, String phoneNumber) {
        this(name, password, DEFAULT_COUNTRY_NUMBER, phoneNumber);
    }

    public RegistrationByPhoneNumberForm(String name, String password, int countryNumber, String phoneNumber) {
        String body = "{\"name\":\"" + name + "\",\"password\":\"" + password + "\",\"phone\":{\"country\":" + countryNumber + ",\"number\":\"" + phoneNumber + "\"}}";
        response = sendRequest("https://api.av.by/auth/phone/sign-up", body);
    }
}
