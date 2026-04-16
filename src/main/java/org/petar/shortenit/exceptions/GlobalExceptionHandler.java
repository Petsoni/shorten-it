package org.petar.shortenit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlNotValidException.class)
    public ResponseEntity<String> handleBadUrl(UrlNotValidException urlNotValidException) {
        return new ResponseEntity<>(urlNotValidException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
