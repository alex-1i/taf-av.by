package by.av.api.signinform;

import java.util.List;

public class SignInByPhoneNumberForm extends SignInForm {

    private static final String URL_SIGN_IN_BY_PHONE = URL_AUTH + "phone/sign-in";

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
        response = sendRequest(URL_SIGN_IN_BY_PHONE, body);
    }

    public List<String> getContextErrorPhone() {
        return response.path("context.errors['phone']");
    }
}
