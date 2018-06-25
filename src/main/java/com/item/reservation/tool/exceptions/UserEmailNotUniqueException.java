package com.item.reservation.tool.exceptions;

public class UserEmailNotUniqueException extends Exception {
    public UserEmailNotUniqueException() {
        super();
    }

    public UserEmailNotUniqueException(String message) {
        super(message);
    }

    public UserEmailNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEmailNotUniqueException(Throwable cause) {
        super(cause);
    }
}
