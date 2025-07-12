package by.av.api.signinform;

import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public abstract class SignInForm {
    protected Response response;
    protected static final int DEFAULT_COUNTRY_NUMBER = 1;
    protected static final String URL_AUTH = "https://api.av.by/auth/";

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
        return response.path("message");
    }

    public String getMessageText() {
        return response.path("messageText");
    }

    public List<String> getContextErrorPhoneCountry() {
        return response.path("context.errors['phone.country']");
    }

    public List<String> getContextErrorPhoneNumber() {
        return response.path("context.errors['phone.number']");
    }

    public List<String> getContextErrorPassword() {
        return response.path("context.errors['password']");
    }

    public List<String> getContextErrorName() { return response.path("context.errors['name']"); }

    public List<String> getContextErrorEmail() {
        return response.path("context.errors['email']");
    }
}
