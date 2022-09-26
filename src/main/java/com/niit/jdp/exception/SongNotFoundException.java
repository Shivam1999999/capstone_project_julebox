package com.niit.jdp.exception;

public class SongNotFoundException extends Throwable {

    private String exceptionMessage;

    public SongNotFoundException(String s) {
        exceptionMessage = "Song not found";
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
