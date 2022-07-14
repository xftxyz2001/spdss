package com.xftxyz.smms.exception;

public class ExNotLogin extends Exception{

    public ExNotLogin() {
    }

    public ExNotLogin(String message) {
        super(message);
    }

    public ExNotLogin(Throwable cause) {
        super(cause);
    }

    public ExNotLogin(String message, Throwable cause) {
        super(message, cause);
    }

    public ExNotLogin(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
