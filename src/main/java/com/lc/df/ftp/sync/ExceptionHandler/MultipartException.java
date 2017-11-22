package com.lc.df.ftp.sync.ExceptionHandler;

public class MultipartException extends RuntimeException {

    public MultipartException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
