package com.example.webshopcosmetics.exception;

public class WebsiteSettingException extends RuntimeException {
    public WebsiteSettingException() {
        super();
    }

    public WebsiteSettingException(String message) {
        super(message);
    }

    public WebsiteSettingException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebsiteSettingException(Throwable cause) {
        super(cause);
    }
}