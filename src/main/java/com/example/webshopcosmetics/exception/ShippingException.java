package com.example.webshopcosmetics.exception;

public class ShippingException extends RuntimeException {
    public ShippingException() {
        super();
    }

    public ShippingException(String message) {
        super(message);
    }

    public ShippingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShippingException(Throwable cause) {
        super(cause);
    }
}