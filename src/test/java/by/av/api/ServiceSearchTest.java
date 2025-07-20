package by.av.api;

import by.av.api.servicesearch.ServiceSearch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static by.av.utils.Utils.getRandomService;

public class ServiceSearchTest {

    ServiceSearch serviceSearch = new ServiceSearch();

    @Test
    public void checkServicePageContainServices() {
        Map.Entry<String, String> randomService = getRandomService(serviceSearch.getServiceMap());
        String url = ServiceSearch.URL_COMPANY + "/" + randomService.getKey();
        String html = serviceSearch.sendRequest(url).getBody().asString();

        boolean result = serviceSearch.allCompaniesContainService(html, randomService.getValue());

        Assertions.assertTrue(result, "Не все компании содержат услугу: " + randomService.getValue());
    }
}
