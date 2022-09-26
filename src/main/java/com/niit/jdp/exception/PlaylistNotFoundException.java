package com.niit.jdp.exception;


/**
 * This class is a custom exception class that extends the Exception class. It has a constructor that takes in a String and
 * sets the exceptionMessage to a custom message. It also has a getter method for the exceptionMessage
 */
public class PlaylistNotFoundException extends Exception {
    private String exceptionMessage;

    public PlaylistNotFoundException(String s) {
        exceptionMessage = "Playlist is not found in the Database";
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
