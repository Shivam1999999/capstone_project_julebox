package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PlayListRepositoryInterface {
    List<Playlist> getAllPlaylist(Connection connection) throws SQLException;

    boolean addPlaylist(Connection connection, Playlist playlist) throws SQLException;

    Playlist getByPlaylistName(Connection connection, String playlist_name) throws SQLException;

    boolean updatePlaylistId(Connection connection, int id) throws SQLException;

    boolean deleteById(Connection connection, int id) throws SQLException;
}
