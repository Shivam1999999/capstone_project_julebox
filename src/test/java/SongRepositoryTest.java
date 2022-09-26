import com.niit.jdp.model.Song;
import com.niit.jdp.repository.SongRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class SongRepositoryTest {

    private static final String URL = "jdbc:mysql://localhost:3306/jukebox";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Shivam@1999";
    SongRepository songRepository;
    Connection connection;

    public SongRepositoryTest() {
        this.connection = null;
    }


    @BeforeEach
    void setUp() throws SQLException {
        songRepository = new SongRepository();
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);


    }


    @AfterEach
    public void tearDown() {
    }

    @Test
    void givenSongDetailsAndReturnListOfSong() throws SQLException {


        List<Song> expectedResult = songRepository.getAll(connection);

        List<Song> actualResult = songRepository.getAll(connection);
        Assertions.assertEquals(expectedResult, actualResult);

    }

    @Test
    void givenSongDetailsAndFailToReturnListOfSong() throws SQLException {

        List<Song> expectedResult = new ArrayList<>();

        List<Song> actualResult = songRepository.getAll(connection);
        Assertions.assertNotEquals(expectedResult, actualResult);

    }

    @Test
    void givenNameOfArtistAndReturnListOfSong() throws SQLException {

        String expectedResult = "[Song{songId=101, songName='SoftPiano', artistName=Capstone',genre='Instru', duration='02:56',  songPath='src/main/resources/Song1.wav'}, Song{songId=102, songName='Musically', artistName='Juke', genre='Instr', duration='1:59', songPath='src/main/resources/Song2.wav'}]";
        String actualResult = songRepository.getAll(connection).toString();
        Assertions.assertEquals(expectedResult, actualResult);


    }

    @Test
    void givenNameOfArtistAndFailedToReturnListOfSong() throws SQLException {

        String expectedResult = "[Song{id=101, name='SoftPiano', artist='Song2', duration='02:16', album='Capstone', genre='Ambience', filepath='src/main/resources/Song1.wav'}, Song{id=102, name='SoftPianoMusic', artist='Song3', duration='02:16', album='Capstone', genre='Ambience', filepath='src/main/resources/Song2.wav'}]";

        String actualResult = songRepository.getById(connection, 101).toString();
        Assertions.assertNotEquals(expectedResult, actualResult);


    }

}
