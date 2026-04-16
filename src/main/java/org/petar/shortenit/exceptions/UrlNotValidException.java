package org.petar.shortenit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UrlNotValidException extends RuntimeException {
    public UrlNotValidException() {
        super("Passed URL is not authentic");
    }
}
