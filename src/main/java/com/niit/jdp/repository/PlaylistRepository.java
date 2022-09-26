package com.niit.jdp.repository;

import com.niit.jdp.exception.PlaylistNotFoundException;
import com.niit.jdp.model.Playlist;

import java.sql.*;
import java.util.List;

/**
 * It implements the Repository interface and provides the implementation for the methods defined in the interface
 */
public class PlaylistRepository implements Repository <Playlist> {

    @Override
    public List<Playlist> getAll(Connection connection) throws SQLException {
        String readQuery = "SELECT * FROM `jukebox`.`playlist`;";
        ResultSet resultSet;
        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(readQuery);
            while (resultSet.next()) {
                System.out.format("%s     %n%s     %n%s     %n%s%n", "Playlist ID :" + resultSet.getInt(1) + " ", "Playlist Name :" + resultSet.getString(2) + " ", "Song ID :" + resultSet.getInt(3) + " ", "Song Name :" + resultSet.getString(4));
                System.out.println();
            }
        }
        return List.of(new Playlist());
    }


    public boolean createPlaylist(Connection connection, String playlistName, int songId, String songName) throws SQLException {
        String insertQuery = "INSERT INTO `jukebox`.`playlist` (playlist_name, song_id,song_name) VALUES (?,?,?);";
        int numberOfRowsAffected;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, playlistName);
            preparedStatement.setInt(2, songId);
            preparedStatement.setString(3, songName);
            numberOfRowsAffected = preparedStatement.executeUpdate();
        }
        return numberOfRowsAffected > 0;
    }

    @Override
    public Playlist getById(Connection connection, int id) throws SQLException {
        String searchQuery = "SELECT * FROM `jukebox`.`playlist` WHERE(`playlist_id` = ?);";
        Playlist playlist = new Playlist();
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet playlistResultSet = preparedStatement.executeQuery();
            while (playlistResultSet.next()) {
                int playlistId = playlistResultSet.getInt("playlist_id");
                String playlistName = playlistResultSet.getString("playlist_name");
                int songNumber = playlistResultSet.getInt("song_id");
                String songName = playlistResultSet.getString("song_name");
                playlist = new Playlist(playlistId, playlistName, songNumber, songName);
                if (playlistId == 0) {
                    throw new PlaylistNotFoundException("The playlist is not exists !Please enter a valid playlist id");
                }
            }
        } catch (PlaylistNotFoundException e) {
            throw new RuntimeException(e);
        }
        return playlist;
    }

    @Override
    public boolean deleteById(Connection connection, int id) throws SQLException {
        String deleteQuery = "DELETE FROM `jukebox`.`playlist` WHERE (`playlist_id` = ?);";
        int numberOfRowsAffected;
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            numberOfRowsAffected = preparedStatement.executeUpdate();
        }
        return numberOfRowsAffected > 0;
    }


}