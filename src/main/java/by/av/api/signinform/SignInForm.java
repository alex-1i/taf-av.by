package by.av.api.signinform;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static io.restassured.RestAssured.given;

public abstract class SignInForm {

    protected final Logger logger = LogManager.getLogger(getClass());
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
        logger.info("Отправка POST-запроса на URL: {}", url);
        logger.info("Тело запроса: {}", body);
        response = given()
                .header("content-type", "application/json")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36")
                .body(body)
                .when().post(url);

        logger.info("Получен ответ. Статус код: {}", response.statusCode());
        logger.debug("Тело ответа: {}", response.asPrettyString());

        return response;
    }

    public int getStatusCode() {
        int statusCode = response.statusCode();
        logger.debug("Статус код ответа: {}", statusCode);
        return statusCode;
    }

    public String getMessage() {
        String message = response.path(RESPONSE_MESSAGE);
        logger.debug("Извлечено поле message: {}", message);
        return message;
    }

    public String getMessageText() {
        String messageText = response.path(RESPONSE_MESSAGE_TEXT);
        logger.debug("Извлечено поле messageText: {}", messageText);
        return messageText;
    }

    public List<String> getContextErrorPhoneCountry() {
        List<String> errors = response.path(RESPONSE_ERRORS_PHONE_COUNTRY);
        logger.debug("Ошибки phone.country: {}", errors);
        return errors;
    }

    public List<String> getContextErrorPhoneNumber() {
        List<String> errors = response.path(RESPONSE_ERRORS_PHONE_NUMBER);
        logger.debug("Ошибки phone.number: {}", errors);
        return errors;
    }

    public List<String> getContextErrorPassword() {
        List<String> errors = response.path(RESPONSE_ERRORS_PASSWORD);
        logger.debug("Ошибки password: {}", errors);
        return errors;
    }

    public List<String> getContextErrorName() {
        List<String> errors = response.path(RESPONSE_ERRORS_NAME);
        logger.debug("Ошибки name: {}", errors);
        return errors;
    }

    public List<String> getContextErrorEmail() {
        List<String> errors = response.path(RESPONSE_ERRORS_EMAIL);
        logger.debug("Ошибки email: {}", errors);
        return errors;
    }
}
