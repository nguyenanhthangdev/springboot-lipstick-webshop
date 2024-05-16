package com.example.webshopcosmetics.exception;

public class AboutException extends RuntimeException {
    public AboutException() {
        super();
    }
    public AboutException(String message) {
        super(message);
    }
    public AboutException(String message, Throwable cause) {
        super(message, cause);
    }
    public AboutException(Throwable cause) {
        super(cause);
    }
}