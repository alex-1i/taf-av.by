package by.av.ui.pages.home.loginform.recoverypasswordform;

import by.av.ui.pages.home.HomePage;
import by.av.ui.pages.home.HomePageLocator;
import org.openqa.selenium.By;

public class RecoveryPasswordForm extends HomePage {

    public String getButtonLoginText() {
        return driver.findElement(By.xpath(RecoveryPasswordFormLocator.BUTTON_LOGIN)).getText();
    }

    public void clickButtonLogin() {
        logger.info("Клик по кнопке \"Вход\"");
        driver.findElement(By.xpath(RecoveryPasswordFormLocator.BUTTON_LOGIN)).click();
    }

    public String getTitleRecoveryPasswordFormText() {
        return driver.findElement(By.xpath(RecoveryPasswordFormLocator.TITLE_RECOVERY_PASSWORD_FORM)).getText();
    }

    public String getButtonRecoveryByPhoneNumberText() {
        return driver.findElement(By.xpath(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_PHONE_NUMBER)).getText();
    }

    public void clickButtonRecoveryByPhoneNumber() {
        logger.info("Клик по кнопке \"по номеру телефона\"");
        driver.findElement(By.xpath(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_PHONE_NUMBER)).click();
    }

    public String getButtonRecoveryByPhoneNumberAttributeAriaSelectedValue() {
        return driver.findElement(By.xpath(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_PHONE_NUMBER)).getDomAttribute(HomePageLocator.ATTRIBUTE_ARIA_SELECTED);
    }

    public String getTextRecoveryByPhoneNumber() {
        return driver.findElement(By.xpath(RecoveryPasswordFormLocator.TEXT_RECOVERY_BY_PHONE_NUMBER)).getText();
    }

    public String getLabelPhoneNumberText() {
        return driver.findElement(By.xpath(RecoveryPasswordFormLocator.LABEL_PHONE_NUMBER)).getText();
    }

    public void inputPhoneNumber(String phoneNumber) {
        logger.info("Ввод номера телефона: {}", phoneNumber);
        driver.findElement(By.xpath(RecoveryPasswordFormLocator.INPUT_PHONE_NUMBER)).sendKeys(phoneNumber);
    }

    public String getButtonSendByPhoneNumberText() {
        return driver.findElement(By.xpath(RecoveryPasswordFormLocator.BUTTON_SEND_BY_PHONE_NUMBER)).getText();
    }

    public void clickButtonSendByPhoneNumber() {
        logger.info("Клик по кнопке \"Восстановить по номеру телефона\"");
        driver.findElement(By.xpath(RecoveryPasswordFormLocator.BUTTON_SEND_BY_PHONE_NUMBER)).click();
    }

    public boolean isButtonSendByPhoneNumberEnabled() {
        return driver.findElement(By.xpath(RecoveryPasswordFormLocator.BUTTON_SEND_BY_PHONE_NUMBER)).getDomAttribute(HomePageLocator.ATTRIBUTE_DISABLED) == null;
    }

    public String getButtonRecoveryByEmailText() {
        return driver.findElement(By.xpath(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_EMAIL)).getText();
    }

    public void clickButtonRecoveryByEmail() {
        logger.info("Клик по кнопке \"по почте\"");
        driver.findElement(By.xpath(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_EMAIL)).click();
    }

    public String getButtonRecoveryByEmailAttributeAriaSelectedValue() {
        return driver.findElement(By.xpath(RecoveryPasswordFormLocator.BUTTON_RECOVERY_BY_EMAIL)).getDomAttribute(HomePageLocator.ATTRIBUTE_ARIA_SELECTED);
    }

    public String getTextRecoveryByPEmail() {
        return driver.findElement(By.xpath(RecoveryPasswordFormLocator.TEXT_RECOVERY_BY_EMAIL)).getText();
    }

    public String getLabelEmailText() {
        return driver.findElement(By.xpath(RecoveryPasswordFormLocator.LABEL_EMAIL)).getText();
    }

    public void inputEmail(String email) {
        logger.info("Ввод почты: {}", email);
        driver.findElement(By.xpath(RecoveryPasswordFormLocator.INPUT_EMAIL)).sendKeys(email);
    }

    public String getButtonSendByEmailText() {
        return driver.findElement(By.xpath(RecoveryPasswordFormLocator.BUTTON_SEND_BY_EMAIL)).getText();
    }

    public void clickButtonSendByEmail() {
        logger.info("Клик по кнопке \"Восстановить по почте\"");
        driver.findElement(By.xpath(RecoveryPasswordFormLocator.BUTTON_SEND_BY_EMAIL)).click();
    }

    public boolean isButtonSendByEmailEnabled() {
        return driver.findElement(By.xpath(RecoveryPasswordFormLocator.BUTTON_SEND_BY_EMAIL)).getDomAttribute(HomePageLocator.ATTRIBUTE_DISABLED) == null;
    }
}
