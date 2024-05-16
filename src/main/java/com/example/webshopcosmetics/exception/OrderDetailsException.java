package com.example.webshopcosmetics.exception;

public class OrderDetailsException extends RuntimeException {
    public OrderDetailsException() {
        super();
    }

    public OrderDetailsException(String message) {
        super(message);
    }

    public OrderDetailsException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderDetailsException(Throwable cause) {
        super(cause);
    }
}