package com.item.reservation.tool.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserIsBlockedException extends AuthenticationException {
    public UserIsBlockedException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserIsBlockedException(String msg) {
        super(msg);
    }
}
