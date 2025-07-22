package by.av.api;

import by.av.api.servicesearch.ServiceSearch;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static by.av.utils.Utils.getRandomService;

@Epic("API")
@Feature("Поиск компаний по услуге")
@DisplayName("Тест поиска компаний по услуге")
public class ServiceSearchTest {

    ServiceSearch serviceSearch = new ServiceSearch();

    @Test
    @DisplayName("Проверка поиска компаний по услуге")
    public void checkServicePageContainServices() {
        Map.Entry<String, String> randomService = getRandomService(serviceSearch.getServiceMap());
        String url = ServiceSearch.URL_COMPANY + "/" + randomService.getKey();
        String html = serviceSearch.sendRequest(url).getBody().asString();

        boolean result = serviceSearch.allCompaniesContainService(html, randomService.getValue());

        Assertions.assertTrue(result, "Не все компании содержат услугу: " + randomService.getValue());
    }
}
