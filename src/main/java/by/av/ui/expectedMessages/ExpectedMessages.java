package by.av.ui.expectedMessages;

public class ExpectedMessages {
    public static final String TITLE_LOGIN = "Вход";
    public static final String INACTIVE_ATTRIBUTE = "false";
    public static final String ACTIVE_ATTRIBUTE = "true";
    public static final String BUTTON_BY_PHONE_NUMBER = "по телефону";
    public static final String BUTTON_BY_EMAIL_OR_LOGIN = "почте или логину";
    public static final String LABEL_PHONE = "Телефон";
    public static final String LABEL_PASSWORD = "Пароль";
    public static final String PASSWORD_HIDDEN = "password";
    public static final String PASSWORD_VISIBLE = "text";
    public static final String BUTTON_RECOVERY = "Не помню пароль";
    public static final String BUTTON_LOGIN = "Войти";
    public static final String BUTTON_REGISTRATION_FORM = "Регистрация\nдля тех, кто первый раз на сайте";
    public static final String ERROR_MESSAGE_LOGIN_BY_PHONE_NUMBER = "Неверный телефон или пароль. Если забыли пароль, восстановите его";
    public static final String ERROR_MESSAGE_LOGIN_BY_EMAIL = "Неверный логин или пароль. Если забыли пароль, восстановите его";
    public static final String LABEL_EMAIL_OR_LOGIN = "Электронная почта или логин";
    public static final String TITLE_RECOVERY_PASSWORD = "Запрос на восстановление пароля";
    public static final String BUTTON_BY_EMAIL = "почте";
    public static final String TEXT_RECOVERY_BY_PHONE_NUMBER = "Укажите телефон, который вы указывали при регистрации. После подтверждения номера телефона вы сможете ввести новый пароль.";
    public static final String TEXT_RECOVERY_BY_EMAIL = "Укажите адрес электронной почты, который вы указывали при регистрации. На него мы отправим инструкции по восстановлению пароля.";
    public static final String BUTTON_SEND = "Отправить";
    public static final String ERROR_MESSAGE_RECOVERY_BY_PHONE_NUMBER = "Такого пользователя у нас нет. Проверьте указанный номер";
    public static final String ERROR_MESSAGE_RECOVERY_BY_EMAIL = "Такого пользователя у нас нет. Проверьте указанную почту";
    public static final String LABEL_EMAIL = "Электронная почта";
    public static final String ERROR_MESSAGE_INVALID_EMAIL = "Введите почту полностью. Например, info@av.by";
    public static final String TITLE_REGISTRATION = "Регистрация";
    public static final String LABEL_NAME = "Имя на кириллице";
    public static final String WARN_MESSAGE_FOR_PASSWORD = "Не короче 8 символов и только латиница и цифры";
    public static final String BUTTON_REGISTRATION = "Зарегистрироваться";
    public static final String BUTTON_LOGIN_FORM = "Вход\nдля тех, кто уже зарегистрирован";
    public static final String TEXT_FAILED_SEARCH = "Ничего подходящего у нас нет. Попробуйте другой запрос или проверьте написание текущего.";

    public static String getMessageButtonDisabled(String button) {
        return "Кнопка " + button + "должна быть неактивной";
    }

    public static String getMessageButtonEnabled(String button) {
        return "Кнопка " + button + "должна быть активной";
    }
}
