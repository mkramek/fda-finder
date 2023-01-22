package pl.mkramek.fdafinder.http.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.HttpClientErrorException;
import pl.mkramek.fdafinder.api.model.openfda.OpenFDAResponse;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource("classpath:application.properties")
public class OpenFDAServiceTest {

    @Autowired
    private OpenFDAService openFDAService;

    @Test
    void expectHttp200Response() {
        final String manufacturer = "teva";
        final String brand = "";
        ResponseEntity<OpenFDAResponse> response = openFDAService.getFDAResponse(manufacturer, brand, null);
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void expectHttp200ResponseWithBrand() {
        final String manufacturer = "teva";
        final String brand = "forte";
        ResponseEntity<OpenFDAResponse> response = openFDAService.getFDAResponse(manufacturer, brand, null);
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void expectHttp400Response() {
        final String manufacturer = "";
        final String brand = "";
        try {
            openFDAService.getFDAResponse(manufacturer, brand, null);
        } catch (HttpClientErrorException hcex) {
            then(hcex.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    void expectHttp404Response() {
        final String manufacturer = "iabduavckugavydvaywkda";
        final String brand = "";
        try {
            openFDAService.getFDAResponse(manufacturer, brand, null);
        } catch (HttpClientErrorException hcex) {
            then(hcex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}
