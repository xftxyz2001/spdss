package com.xftxyz.smms.exception;

public class ExIllegalParameter extends Exception {

    public ExIllegalParameter() {
    }

    public ExIllegalParameter(String message) {
        super(message);
    }

    public ExIllegalParameter(Throwable cause) {
        super(cause);
    }

    public ExIllegalParameter(String message, Throwable cause) {
        super(message, cause);
    }

    public ExIllegalParameter(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
