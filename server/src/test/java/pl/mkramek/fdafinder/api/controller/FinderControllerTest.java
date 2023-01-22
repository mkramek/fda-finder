package pl.mkramek.fdafinder.api.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import pl.mkramek.fdafinder.api.model.FinderResponse;
import pl.mkramek.fdafinder.http.exception.ExceptionHandlerAdvice;

import java.util.Locale;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebAppConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class FinderControllerTest {

    @Autowired
    private FinderController finderController;

    @Test
    void shouldReturnOpenFDAResponse() {
        final String manufacturer = "teva";
        FinderResponse response = finderController.getDrugsData(manufacturer, null, null);
        then(response).isNotNull();
        then(response.getManufacturer().toLowerCase(Locale.getDefault())).contains(manufacturer);
    }

    @Test
    void shouldThrowHttp400() {
        try {
            finderController.getDrugsData(null, null, null);
        } catch (HttpClientErrorException hcex) {
            then(hcex.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    void shouldThrowHttp404() {
        try {
            finderController.getDrugsData("8syeg9fysgidfsb", null, null);
        } catch (HttpClientErrorException hcex) {
            then(hcex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}