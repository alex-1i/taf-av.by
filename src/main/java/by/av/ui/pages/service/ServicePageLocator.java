package by.av.ui.pages.service;

public class ServicePageLocator {

    public static final String INPUT_SEARCH = "//input[@placeholder=\"Поиск компаний и услуг\"]";
    public static final String SEARCH_RESULT = "//li[@class=\"form-combobox__result\"]";
    public static final String LIST_OF_COMPANIES = "//div[@class=\"companies-list__content\"]/div[@class=\"companies-list__inner\"][1]/div";
    public static final String LIST_OF_SERVICES = "//ul[@class=\"form-combobox__list\"]/li[@class=\"form-combobox__item\"]";
    public static final String ATTRIBUTE = "value";
    public static final int DEFAULT_NUMBER_OF_SERVICES = 10;
    public static final int DEFAULT_NUMBER_OF_COMPANIES = 0;

    public static String getActiveService(String service) {
        return "//button[contains(normalize-space(.), \"" + service + "\")]";
    }
}
