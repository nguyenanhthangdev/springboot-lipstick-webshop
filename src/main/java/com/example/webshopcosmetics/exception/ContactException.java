package com.example.webshopcosmetics.exception;

public class ContactException extends RuntimeException {
    public ContactException() {
        super();
    }
    public ContactException(String message) {
        super(message);
    }
    public ContactException(String message, Throwable cause) {
        super(message, cause);
    }
    public ContactException(Throwable cause) {
        super(cause);
    }
}