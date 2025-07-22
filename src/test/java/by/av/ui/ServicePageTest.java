package by.av.ui;

import by.av.ui.expectedMessages.ExpectedMessages;
import by.av.ui.pages.service.ServicePageLocator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.av.ui.driver.Waiter.waitOfNumberOfElements;
import static by.av.utils.Utils.generateInvalidInputData;
import static by.av.utils.Utils.getRandomService;
import static org.junit.jupiter.api.Assertions.*;

@Epic("UI")
@Feature("Поиск компаний по услуге")
@DisplayName("Тесты поиска компаний по услуге")
public class ServicePageTest extends WithServicePageSetUp{

    @BeforeEach
    public void setUp() {
        openServicePage();
    }

    @Test
    @DisplayName("Проверка поиска компаний по услуге")
    public void checkSearchResultAfterInputValidService() {
        waitOfNumberOfElements(ServicePageLocator.LIST_OF_SERVICES, ServicePageLocator.DEFAULT_NUMBER_OF_SERVICES);
        String service = getRandomService(servicePage.getAllServiceText());
        servicePage.inputSearchInfoAndSubmit(service);

        List<String> allCompaniesText = servicePage.getAllCompaniesText();
        assertFalse(allCompaniesText.isEmpty(), "Список компаний пуст");

        for (String companyText : allCompaniesText) {
            assertTrue(companyText.toLowerCase().contains(service.toLowerCase()), "Компания не содержит искомый текст, текст компании: \n" + companyText);
        }
    }

    @Test
    @DisplayName("Проверка отсутствия компаний по невалидной услуге")
    public void checkSearchResultAfterInputInvalidService() {
        servicePage.inputSearchInfo(generateInvalidInputData());

        assertEquals(servicePage.getSearchResultText(), ExpectedMessages.TEXT_FAILED_SEARCH);
    }
}
