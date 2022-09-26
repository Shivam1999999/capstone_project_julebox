package com.niit.jdp.model;

import java.util.Objects;

public class Playlist {
    private int playlistId;
    private String playlistName;
    private int songId;
    private String songName;

    public Playlist() {
    }

    public Playlist(int playlistId, String playlistName, int songId, String songName) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.songId = songId;
        this.songName = songName;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Override
    // Checking if the object is equal to the current object.
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return playlistId == playlist.playlistId && songId == playlist.songId && Objects.equals(playlistName, playlist.playlistName) && Objects.equals(songName, playlist.songName);
    }

    @Override
    // A method that returns a hash code value for the object. This method is supported for the benefit of hash tables such
    // as those provided by HashMap.
    public int hashCode() {
        return Objects.hash(playlistId, playlistName, songId, songName);
    }

    @Override
    // A method that returns a string representation of the object. In general, the toString method returns a string that
    // "textually represents" this object. The result should be a concise but informative representation that is easy for a
    // person to read. It is recommended that all subclasses override this method.
    public String toString() {
        return "Playlist{" +
                "playlistId=" + playlistId +
                ", playlistName='" + playlistName + '\'' +
                ", songId=" + songId +
                ", songName='" + songName + '\'' +
                '}';
    }
}
