package com.example.webshopcosmetics.exception;

public class BannerException extends RuntimeException {
    public BannerException() {
        super();
    }
    public BannerException(String message) {
        super(message);
    }
    public BannerException(String message, Throwable cause) {
        super(message, cause);
    }
    public BannerException(Throwable cause) {
        super(cause);
    }
}