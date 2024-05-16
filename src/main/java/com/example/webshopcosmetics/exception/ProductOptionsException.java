package com.example.webshopcosmetics.exception;

public class ProductOptionsException extends RuntimeException {
    public ProductOptionsException() {
        super();
    }
    public ProductOptionsException(String message) {
        super(message);
    }
    public ProductOptionsException(String message, Throwable cause) {
        super(message, cause);
    }
    public ProductOptionsException(Throwable cause) {
        super(cause);
    }
}
