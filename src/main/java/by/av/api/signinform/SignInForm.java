package by.av.api.signinform;

import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public abstract class SignInForm {
    protected Response response;
    public static final int DEFAULT_COUNTRY_NUMBER = 1;
    public static final int ANOTHER_COUNTRY_NUMBER = 2;
    public static final int WRONG_COUNTRY_NUMBER = 5;

    protected static final String URL_AUTH = "https://api.av.by/auth/";
    private static final String RESPONSE_MESSAGE = "message";
    private static final String RESPONSE_MESSAGE_TEXT = "messageText";
    private static final String RESPONSE_ERRORS_PHONE_COUNTRY = "context.errors['phone.country']";
    private static final String RESPONSE_ERRORS_PHONE_NUMBER = "context.errors['phone.number']";
    private static final String RESPONSE_ERRORS_PASSWORD = "context.errors['password']";
    private static final String RESPONSE_ERRORS_NAME = "context.errors['name']";
    private static final String RESPONSE_ERRORS_EMAIL = "context.errors['email']";

    protected Response sendRequest(String url, String body) {
        return given()
                .header("content-type", "application/json")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36")
                .body(body)
                .when().post(url);
    }

    public int getStatusCode() {
        return response.statusCode();
    }

    public String getMessage() {
        return response.path(RESPONSE_MESSAGE);
    }

    public String getMessageText() {
        return response.path(RESPONSE_MESSAGE_TEXT);
    }

    public List<String> getContextErrorPhoneCountry() {
        return response.path(RESPONSE_ERRORS_PHONE_COUNTRY);
    }

    public List<String> getContextErrorPhoneNumber() {
        return response.path(RESPONSE_ERRORS_PHONE_NUMBER);
    }

    public List<String> getContextErrorPassword() {
        return response.path(RESPONSE_ERRORS_PASSWORD);
    }

    public List<String> getContextErrorName() { return response.path(RESPONSE_ERRORS_NAME); }

    public List<String> getContextErrorEmail() {
        return response.path(RESPONSE_ERRORS_EMAIL);
    }
}
