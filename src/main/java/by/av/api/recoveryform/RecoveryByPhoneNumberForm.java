package by.av.api.recoveryform;

import by.av.api.signinform.SignInForm;

public class RecoveryByPhoneNumberForm extends SignInForm {

    private static final String URL_RECOVERY_BY_PHONE = URL_AUTH + "phone/password-reset-request";

    public RecoveryByPhoneNumberForm(String number) {
        this(number, DEFAULT_COUNTRY_NUMBER);
    }

    public RecoveryByPhoneNumberForm(String number, int countryNumber) {
        logger.info("Создание запроса RecoveryByPhoneNumberForm");
        logger.info("Вводимые данные — Номер: {}, Код страны: {}", number, countryNumber);

        String body = "{\"phone\":{\"country\":" + countryNumber + ",\"number\":\"" + number + "\"}}";
        response = sendRequest(URL_RECOVERY_BY_PHONE, body);
    }
}
