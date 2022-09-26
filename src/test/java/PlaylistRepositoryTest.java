import com.niit.jdp.model.Playlist;
import com.niit.jdp.repository.PlaylistRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


class PlaylistRepositoryTest {
    private static final String URL = "jdbc:mysql://localhost:3306/jukebox";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Shivam@1999";
    PlaylistRepository playlistRepository;
    Connection connection;
    Playlist playlist;

    public PlaylistRepositoryTest() {
        this.connection = null;
    }


    @BeforeEach
    void setUp() throws SQLException {
        playlistRepository = new PlaylistRepository();
        playlist = new Playlist();
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);


    }


    @AfterEach
    public void tearDown() {
    }

    @Test
    void displayAllPlayListAndReturnListOfPlayList() throws SQLException {

        List<Playlist> expectedResult = playlistRepository.getAll(connection);
        List<Playlist> actualResult = playlistRepository.getAll(connection);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void failedToDisplayAllPlayList() throws SQLException {

        List<Playlist> expectedResult = new ArrayList<>();
        List<Playlist> actualResult = playlistRepository.getAll(connection);
        Assertions.assertNotEquals(expectedResult, actualResult);
    }
}