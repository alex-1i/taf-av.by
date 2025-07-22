package by.av.ui;

import by.av.ui.pages.service.ServicePage;
import by.av.ui.pages.service.ServicePageLocator;

import static by.av.ui.driver.Waiter.waitOfElement;

abstract class WithServicePageSetUp extends HomeTest{

    protected ServicePage servicePage;

    protected void openServicePage() {
        servicePage = new ServicePage();
        servicePage.openServicePage();
        waitOfElement(ServicePageLocator.INPUT_SEARCH);
    }
}
