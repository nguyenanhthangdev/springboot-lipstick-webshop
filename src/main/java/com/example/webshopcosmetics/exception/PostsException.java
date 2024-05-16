package com.example.webshopcosmetics.exception;

public class PostsException extends RuntimeException {
    public PostsException() {
        super();
    }

    public PostsException(String message) {
        super(message);
    }

    public PostsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostsException(Throwable cause) {
        super(cause);
    }
}