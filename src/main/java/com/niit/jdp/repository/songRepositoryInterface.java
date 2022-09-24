package com.niit.jdp.repository;

import com.niit.jdp.model.Song;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface songRepositoryInterface {
    List<Song> getAllSongs(Connection connection) throws SQLException;

    Song searchByName(Connection connection, String name) throws SQLException;

    Song getById(Connection connection, int songId) throws SQLException;

    Song searchByArtist(Connection connection, String artist) throws SQLException;

    Song searchByGenre(Connection connection, String genre) throws SQLException;
}
