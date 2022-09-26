package com.niit.jdp.exception;

/**
 * SongNotFoundException is a class that extends Throwable and has a constructor that takes a String and a getter for the
 * exceptionMessage.
 */
public class SongNotFoundException extends Throwable {

    private String exceptionMessage;

    public SongNotFoundException(String s) {
        exceptionMessage = "Song not found";
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
