package pl.mkramek.fdafinder.http.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebAppConfiguration
@ContextConfiguration(classes = {
        ExceptionHandlerAdvice.class,
        TestConfiguration.class,
        ExceptionHandlerAdviceTest.ExceptionThrowingController.class })
public class ExceptionHandlerAdviceTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(ExceptionThrowingController.class)
                .setControllerAdvice(new ExceptionHandlerAdvice())
                .build();
    }

    @Configuration
    @EnableWebMvc
    public static class TestConfiguration { }

    @Controller
    @RequestMapping("/tests")
    public static class ExceptionThrowingController {
        @GetMapping(value = "/exc/400")
        public @ResponseBody String badRequest() {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        @GetMapping(value = "/exc/404")
        public @ResponseBody String notFound() {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        @GetMapping(value = "/exc/418")
        public @ResponseBody String iAmATeapot() {
            throw new HttpClientErrorException(HttpStatus.I_AM_A_TEAPOT);
        }

        @GetMapping("/exc/other")
        public @ResponseBody String otherError() {
            throw new IllegalStateException();
        }
    }

    @Test
    public void shouldHandleBadRequest() throws Exception {
        MvcResult result = mockMvc.perform(get("/tests/exc/400"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        then(result.getResponse().getContentAsString().toLowerCase()).contains("bad request");
    }

    @Test
    public void shouldHandleNotFound() throws Exception {
        MvcResult result = mockMvc.perform(get("/tests/exc/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        then(result.getResponse().getContentAsString().toLowerCase()).contains("no matching results");
    }

    @Test
    public void shouldHandleTeapot() throws Exception {
        MvcResult result = mockMvc.perform(get("/tests/exc/418"))
                .andExpect(status().isIAmATeapot())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        then(result.getResponse().getContentAsString().toLowerCase()).contains("error while fetching data");
    }

    @Test
    public void shouldHandleInternalServerError() throws Exception {
        MvcResult result = mockMvc.perform(get("/tests/exc/other"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        then(result.getResponse().getContentAsString().toLowerCase()).contains("non-http error while fetching data");
    }
}