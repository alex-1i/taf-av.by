package by.av.ui.pages.home.loginform.registrationform;

import by.av.ui.pages.home.HomePage;
import by.av.ui.pages.home.HomePageLocator;
import org.openqa.selenium.By;

public class RegistrationForm extends HomePage {

    public String getTitleRegistrationText() {
        return driver.findElement(By.xpath(RegistrationFormLocator.TITLE_REGISTRATION)).getText();
    }

    public String getChooseRegistrationByPhoneNumberText() {
        return driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_PHONE_NUMBER)).getText();
    }

    public void clickChooseRegistrationByPhoneNumber() {
        logger.info("Клик по кнопке \"по номеру телефона\"");
        driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_PHONE_NUMBER)).click();
    }

    public String getChooseRegistrationByPhoneNumberAttributeAriaSelectedValue() {
        return driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_PHONE_NUMBER)).getDomAttribute(HomePageLocator.ATTRIBUTE_ARIA_SELECTED);
    }

    public String getLabelNameByPhoneNumberText() {
        return driver.findElement(By.xpath(RegistrationFormLocator.LABEL_NAME_BY_PHONE_NUMBER)).getText();
    }

    public void inputNameByPhoneNumber(String name) {
        logger.info("Ввод имени: {}", name);
        driver.findElement(By.xpath(RegistrationFormLocator.INPUT_NAME_BY_PHONE_NUMBER)).sendKeys(name);
    }

    public String getLabelPhoneNumberText() {
        return driver.findElement(By.xpath(RegistrationFormLocator.LABEL_PHONE_NUMBER)).getText();
    }

    public void inputPhoneNumber(String phoneNumber) {
        logger.info("Ввод номера телефона: {}", phoneNumber);
        driver.findElement(By.xpath(RegistrationFormLocator.INPUT_PHONE_NUMBER)).sendKeys(phoneNumber);
    }

    public String getLabelPasswordForPhoneNumberText() {
        return driver.findElement(By.xpath(RegistrationFormLocator.LABEL_PASSWORD_FOR_PHONE_NUMBER)).getText();
    }

    public void clickButtonInvertForPhoneNumber() {
        logger.info("Клик по кнопке \"скрытие пароля\"");
        driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_INVERTER_FOR_PHONE_NUMBER)).click();
    }

    public String getButtonInvertForPhoneNumberAttributeAriaPressedValue() {
        return driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_INVERTER_FOR_PHONE_NUMBER)).getDomAttribute(HomePageLocator.ATTRIBUTE_ARIA_PRESSED);
    }

    public void inputPasswordForPhoneNumber(String password) {
        logger.info("Ввод пароля для номера телефона: {}", password);
        driver.findElement(By.xpath(RegistrationFormLocator.INPUT_PASSWORD_FOR_PHONE_NUMBER)).sendKeys(password);
    }

    public void inputNameByPhoneNumberAndPhoneNumber(String name, String phoneNumber) {
        inputNameByPhoneNumber(name);
        inputPhoneNumber(phoneNumber);
    }

    public void inputNameByPhoneNumberAndPasswordForPhoneNumber(String name, String password) {
        inputNameByPhoneNumber(name);
        inputPasswordForPhoneNumber(password);
    }

    public void inputPhoneNumberAndPasswordForPhoneNumber(String phoneNumber, String password) {
        inputPhoneNumber(phoneNumber);
        inputPasswordForPhoneNumber(password);
    }

    public void inputNameByPhoneNumberAndPhoneNumberAndPasswordForPhoneNumber(String name, String phoneNumber, String password) {
        inputNameByPhoneNumberAndPhoneNumber(name, phoneNumber);
        inputPasswordForPhoneNumber(password);
    }

    public String getInputPasswordForPhoneNumberAttributeTypeValue() {
        return driver.findElement(By.xpath(RegistrationFormLocator.INPUT_PASSWORD_FOR_PHONE_NUMBER)).getDomAttribute(HomePageLocator.ATTRIBUTE_TYPE);
    }

    public String getWarnMessageForPasswordForPhoneNumberText() {
        return driver.findElement(By.xpath(RegistrationFormLocator.WARN_MESSAGE_FOR_PASSWORD_FOR_PHONE_NUMBER)).getText();
    }

    public String getButtonRegistrationByPhoneNumberText() {
        return driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_REGISTRATION_BY_PHONE_NUMBER)).getText();
    }

    public boolean isButtonRegistrationByPhoneNumberEnabled() {
        return driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_REGISTRATION_BY_PHONE_NUMBER)).getDomAttribute(HomePageLocator.ATTRIBUTE_DISABLED) == null;
    }

    public String getChooseRegistrationByEmailText() {
        return driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL)).getText();
    }

    public void clickChooseRegistrationByEmail() {
        logger.info("Клик по кнопке \"по почте\"");
        driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL)).click();
    }

    public String getChooseRegistrationByEmailAttributeAriaSelectedValue() {
        return driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_CHOOSE_REGISTRATION_BY_EMAIL)).getDomAttribute(HomePageLocator.ATTRIBUTE_ARIA_SELECTED);
    }

    public String getLabelNameByEmailText() {
        return driver.findElement(By.xpath(RegistrationFormLocator.LABEL_NAME_BY_EMAIL)).getText();
    }

    public void inputNameByEmail(String name) {
        logger.info("Ввод имени: {}", name);
        driver.findElement(By.xpath(RegistrationFormLocator.INPUT_NAME_BY_EMAIL)).sendKeys(name);
    }

    public String getLabelEmailText() {
        return driver.findElement(By.xpath(RegistrationFormLocator.LABEL_EMAIL)).getText();
    }

    public void inputEmail(String email) {
        logger.info("Ввод почты: {}", email);
        driver.findElement(By.xpath(RegistrationFormLocator.INPUT_EMAIL)).sendKeys(email);
    }

    public String getLabelPasswordForEmailText() {
        return driver.findElement(By.xpath(RegistrationFormLocator.LABEL_PASSWORD_FOR_EMAIL)).getText();
    }

    public void clickButtonInvertForEmail() {
        logger.info("Клик по кнопке \"скрытие пароля\"");
        driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_INVERTER_FOR_EMAIL)).click();
    }

    public String getButtonInvertForEmailAttributeAriaPressedValue() {
        return driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_INVERTER_FOR_EMAIL)).getDomAttribute(HomePageLocator.ATTRIBUTE_ARIA_PRESSED);
    }

    public void inputPasswordForEmail(String password) {
        logger.info("Ввод пароля для почты: {}", password);
        driver.findElement(By.xpath(RegistrationFormLocator.INPUT_PASSWORD_FOR_EMAIL)).sendKeys(password);
    }

    public void inputNameByEmailAndEmail(String name, String email) {
        inputNameByEmail(name);
        inputEmail(email);
    }

    public void inputNameByEmailAndPasswordForEmail(String name, String password) {
        inputNameByEmail(name);
        inputPasswordForEmail(password);
    }

    public void inputEmailAndPasswordForEmail(String email, String password) {
        inputEmail(email);
        inputPasswordForEmail(password);
    }

    public void inputNameByEmailAndEmailAndPasswordForEmail(String name, String email, String password) {
        inputNameByEmailAndEmail(name, email);
        inputPasswordForEmail(password);
    }

    public String getInputPasswordForEmailAttributeTypeValue() {
        return driver.findElement(By.xpath(RegistrationFormLocator.INPUT_PASSWORD_FOR_EMAIL)).getDomAttribute(HomePageLocator.ATTRIBUTE_TYPE);
    }

    public String getWarnMessageForPasswordForEmailText() {
        return driver.findElement(By.xpath(RegistrationFormLocator.WARN_MESSAGE_FOR_PASSWORD_FOR_EMAIL)).getText();
    }

    public String getButtonRegistrationByEmailText() {
        return driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_REGISTRATION_BY_EMAIL)).getText();
    }

    public boolean isButtonRegistrationByEmailEnabled() {
        return driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_REGISTRATION_BY_EMAIL)).getDomAttribute(HomePageLocator.ATTRIBUTE_DISABLED) == null;
    }

    public String getButtonLoginText() {
        return driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_LOGIN)).getText();
    }

    public void clickButtonLogin() {
        logger.info("Клик по кнопке \"Вход\"");
        driver.findElement(By.xpath(RegistrationFormLocator.BUTTON_LOGIN)).click();
    }
}
