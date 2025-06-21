package by.av.ui;

import by.av.ui.driver.Driver;
import by.av.ui.pages.home.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

abstract class HomeTest {

    @BeforeEach
    public void openHomePageAndCloseCookie() {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
    }

    @AfterEach
    public void quitAndNullDriver() {
        Driver.quit();
    }
}
