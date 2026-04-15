package org.petar.shortenit.exceptions;

public class UrlNotValidException extends RuntimeException {
    public UrlNotValidException() {
        super("Passed URL is not authentic");
    }
}
