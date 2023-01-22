package pl.mkramek.fdafinder.http.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import pl.mkramek.fdafinder.api.model.FinderResponse;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class FinderServiceTest {

    @Autowired
    private FinderService finderService;

    @Test
    void expectHttpStatus400() {
        try {
            finderService.handleRequest(null, null, null);
        } catch (HttpClientErrorException hcex) {
            then(hcex.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    void expectHttpStatus200() {
        String manufacturer = "teva";
        String brand = "";
        try {
            FinderResponse response = finderService.handleRequest(manufacturer, brand, null);
            then(response).isNotNull();
            then(response.getProducts()).hasSizeGreaterThan(0);
        } catch (HttpClientErrorException hcex) {
            fail();
        }
    }

    @Test
    void expectHttpStatus404() {
        String manufacturer = "alknelfnaekkdja";
        String brand = "";
        try {
            finderService.handleRequest(manufacturer, brand, null);
        } catch (HttpClientErrorException hcex) {
            then(hcex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}
