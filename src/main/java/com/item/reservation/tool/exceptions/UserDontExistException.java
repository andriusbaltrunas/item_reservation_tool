package com.item.reservation.tool.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserDontExistException extends AuthenticationException {

    public UserDontExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserDontExistException(String msg) {
        super(msg);
    }
}