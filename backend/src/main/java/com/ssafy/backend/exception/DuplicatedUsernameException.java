package com.ssafy.backend.exception;

public class DuplicatedUsernameException extends RuntimeException{
    public DuplicatedUsernameException() {
        super();
    }

    public DuplicatedUsernameException(String message) {
        super(message);
    }

    public DuplicatedUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedUsernameException(Throwable cause) {
        super(cause);
    }
}
