package com.example.webshopcosmetics.exception;

public class ManufacturerException extends RuntimeException {
    public ManufacturerException() {
        super();
    }

    public ManufacturerException(String message) {
        super(message);
    }

    public ManufacturerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManufacturerException(Throwable cause) {
        super(cause);
    }
}