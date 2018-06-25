package com.item.reservation.tool.exceptions;

public class SaveItemImageException extends Exception {
    public SaveItemImageException() {
    }

    public SaveItemImageException(String message) {
        super(message);
    }

    public SaveItemImageException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveItemImageException(Throwable cause) {
        super(cause);
    }
}
