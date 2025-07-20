package by.av.api.servicesearch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class ServiceSearch {

    protected final Logger logger = LogManager.getLogger(getClass());
    public static final String URL_COMPANY = "https://av.by/company";
    private static final String REG_EXP_FOR_SERVICES = "<script id=\"__NEXT_DATA__\"[^>]*>(.*?)</script>";


    public Response sendRequest(String url) {
        logger.info("Переход на страницу: {}", url);
        return given()
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36")
                .when().get(url);
    }

    public Map<String, String> getServiceMap() {
        String html = sendRequest(URL_COMPANY).getBody().asString();

        Matcher matcher = Pattern.compile(REG_EXP_FOR_SERVICES, Pattern.DOTALL).matcher(html);

        if(!matcher.find()) {
            throw new RuntimeException("JSON с id=__NEXT_DATA__ не найден");
        }

        try {

            JsonNode services = new ObjectMapper()
                    .readTree(matcher.group(1))
                    .path("props")
                    .path("initialState")
                    .path("companies")
                    .path("options")
                    .path("services");

            Map<String, String> serviceMap = new HashMap<>();

            for (JsonNode service : services) {
                String serviceSlug = service.path("slug").asText();
                String companySlug = service.path("companyType").path("slug").asText();
                String name = service.path("name").asText();

                if (!serviceSlug.isEmpty() && !companySlug.isEmpty() && !name.isEmpty()) {
                    serviceMap.put(companySlug + "_" + serviceSlug, name);
                }
            }

            return serviceMap;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при разборе JSON", e);
        }
    }

    public boolean allCompaniesContainService(String html, String expectedText) {
        Document doc = Jsoup.parse(html);
        Elements companies = doc.select(".companies-item");

        if (companies.isEmpty()) {
            logger.warn("Компании не найдены в HTML!");
            return false;
        }

        for (Element company : companies) {
            String companyText = company.text().toLowerCase();
            if (!companyText.contains(expectedText.toLowerCase())) {
                logger.warn("Компания не содержит услугу '{}': {}", expectedText, companyText);
                return false;
            }
        }

        return true;
    }
}
