package com.jpdev.collaborative_list.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ListNotFound extends RuntimeException {
    
    public ListNotFound(String message) {
        super(message);
    }
    
    public ListNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
