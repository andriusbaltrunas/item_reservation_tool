package com.item.reservation.tool.exceptions;

public class UserCreateException extends Exception {
    public UserCreateException() {
        super();
    }

    public UserCreateException(String message) {
        super(message);
    }

    public UserCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserCreateException(Throwable cause) {
        super(cause);
    }
}
