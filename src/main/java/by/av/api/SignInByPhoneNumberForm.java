package by.av.api;

import java.util.List;

public class SignInByPhoneNumberForm extends SignInForm {

    public SignInByPhoneNumberForm(String number, String password) {
        this(number, password, DEFAULT_COUNTRY_NUMBER);
    }

    public SignInByPhoneNumberForm(String number, String password, int countryNumber) {
        String body = "{\n" +
                "    \"password\": \"" + password + "\",\n" +
                "    \"phone\": {\n" +
                "        \"country\": " + countryNumber + ",\n" +
                "        \"number\": \"" + number + "\"\n" +
                "    }\n" +
                "}";
        response = sendRequest("https://api.av.by/auth/phone/sign-in", body);
    }

    public List<String> getContextErrorPhone() {
        return response.path("context.errors['phone']");
    }
}
