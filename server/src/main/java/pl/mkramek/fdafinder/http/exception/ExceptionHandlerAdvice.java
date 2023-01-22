package pl.mkramek.fdafinder.http.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(HttpClientErrorException.class)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExceptionWrapper> handleClientError(HttpClientErrorException hcex) {
        String message = switch (hcex.getStatusCode().value()) {
            case 400 -> "Bad request";
            case 404 -> "No matching results";
            default -> "Error while fetching data";
        };
        return ResponseEntity.status(hcex.getStatusCode()).body(new ExceptionWrapper(hcex, message));
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExceptionWrapper> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionWrapper(e, "Non-HTTP error while fetching data"));
    }
}
