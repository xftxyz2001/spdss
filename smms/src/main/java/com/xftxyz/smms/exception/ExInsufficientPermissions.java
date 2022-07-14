package com.xftxyz.smms.exception;

public class ExInsufficientPermissions extends Exception {

    public ExInsufficientPermissions() {
    }

    public ExInsufficientPermissions(String message) {
        super(message);
    }

    public ExInsufficientPermissions(Throwable cause) {
        super(cause);
    }

    public ExInsufficientPermissions(String message, Throwable cause) {
        super(message, cause);
    }

    public ExInsufficientPermissions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
