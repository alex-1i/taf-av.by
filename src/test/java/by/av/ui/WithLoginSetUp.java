package by.av.ui;

import by.av.ui.pages.home.HomePage;
import by.av.ui.pages.home.loginform.LoginForm;
import by.av.ui.pages.home.loginform.LoginFormLocator;
import com.github.javafaker.Faker;


import static by.av.ui.driver.Waiter.waitOfElement;

abstract class WithLoginSetUp extends HomeTest{

    protected LoginForm loginForm;
    protected final Faker faker = new Faker();
    protected void openLoginForm() {
        new HomePage().openLoginForm();
        waitOfElement(LoginFormLocator.TITLE_LOGIN_FORM);
        loginForm = new LoginForm();
    }
}
