package com.example.productservice.exceptions;


//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Something is not found")
public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}
