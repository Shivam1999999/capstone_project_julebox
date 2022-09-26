package com.niit.jdp.exception;


public class PlaylistNotFoundException extends Exception {
    private String exceptionMessage;

    public PlaylistNotFoundException(String s) {
        exceptionMessage = "Playlist is not found in the Database";
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
