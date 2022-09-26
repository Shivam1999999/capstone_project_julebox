package com.niit.jdp.repository;

import com.niit.jdp.exception.SongNotFoundException;
import com.niit.jdp.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * It's a class that implements the Repository interface and provides a concrete implementation of the methods defined in
 * the interface
 */
public class SongRepository<S> implements Repository<Song> {
    @Override
    // A method that is used to get all the songs from the database.
    public List<Song> getAll(Connection connection) throws SQLException {
        String readQuery = "SELECT * FROM `jukebox`.`song`;";
        List<Song> songsList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet songsResultSet = statement.executeQuery(readQuery);
            while (songsResultSet.next()) {
                int songId = songsResultSet.getInt("song_id");
                String songName = songsResultSet.getString("song_name");
                String artistName = songsResultSet.getString("artist_name");
                String genre = songsResultSet.getString("genre");
                String duration = songsResultSet.getString("duration");
                String songPath = songsResultSet.getString("Song path");
                System.out.format("%s      %n%s      %n%s      %n%s%n", "Song ID :" + songsResultSet.getInt(1) + " ", "Song Name :" + songsResultSet.getString(2) + " ", "Artist Name :" + songsResultSet.getString(3) + " ", "Genre :" + songsResultSet.getString(4));
                System.out.println();
                Song songs = new Song(songId, songName, artistName, genre, duration, songPath);
                songsList.add(songs);
            }
        }
        return songsList;
    }

    @Override
    // A method that is used to get the song by its id from the database.
    public Song getById(Connection connection, int id) throws SQLException {

        String searchQuery = "SELECT * FROM `jukebox`.`song` WHERE(`song_id` = ?);";
        Song songs = new Song();
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet songsResultSet = preparedStatement.executeQuery();
            while (songsResultSet.next()) {
                int songId = songsResultSet.getInt("song_id");
                String songName = songsResultSet.getString("song_name");
                String artistName = songsResultSet.getString("artist_name");
                String genre = songsResultSet.getString("genre");
                String duration = songsResultSet.getString("duration");
                String songPath = songsResultSet.getString("Song path");
                songs = new Song(songId, songName, artistName, genre, duration, songPath);
                if (songId == 0) {
                    throw new SongNotFoundException("Song expected song Is not in the Playlist Please Check it an Try again with correct song name");
                }
            }
        } catch (SongNotFoundException e) {
            throw new RuntimeException(e);
        }
        return songs;
    }

    @Override
    public boolean deleteById(Connection connection, int id) throws SQLException {
        String deleteQuery = "DELETE FROM `jukebox`.`song` WHERE (`song_id` = ?);";
        int numberOfRowsAffected;
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            numberOfRowsAffected = preparedStatement.executeUpdate();
        }
        return numberOfRowsAffected > 0;
    }

}
