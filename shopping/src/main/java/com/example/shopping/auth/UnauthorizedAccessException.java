package com.example.shopping.auth;

public class UnauthorizedAccessException extends RuntimeException {
	
	public UnauthorizedAccessException() {
        super();
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }

    public UnauthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}


