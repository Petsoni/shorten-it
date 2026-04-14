package org.petar.shortenit.exceptions;

public class UrlNotValidException extends RuntimeException {
    public UrlNotValidException(String message) {
        super(message);
    }
}
