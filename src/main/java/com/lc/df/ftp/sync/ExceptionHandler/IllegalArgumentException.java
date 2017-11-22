package com.lc.df.ftp.sync.ExceptionHandler;

public class IllegalArgumentException extends RuntimeException {

    public IllegalArgumentException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
