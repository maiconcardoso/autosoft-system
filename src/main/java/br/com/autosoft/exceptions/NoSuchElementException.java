package br.com.autosoft.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchElementException extends RuntimeException{
    
    public static final String MESSAGE = "Element Not Found in Search";

    public NoSuchElementException(String message) {
        super(message);
    }
}
