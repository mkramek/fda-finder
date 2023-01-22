package pl.mkramek.fdafinder.http.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ExceptionWrapper implements Serializable {
    private final String exception;
    private final String details;
    private final String message;

    public ExceptionWrapper(Exception e, String message) {
        this.exception = e.getClass().getSimpleName();
        this.message = message;
        this.details = e.getMessage();
    }
}
