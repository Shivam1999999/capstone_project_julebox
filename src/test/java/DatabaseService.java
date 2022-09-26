import com.niit.jdp.service.DatabaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class DatabaseServiceTest {

    DatabaseService databaseService;

    @BeforeEach
    void setUp() {
        databaseService = new DatabaseService();

    }


    @AfterEach
    public void tearDown() {
    }


    @Test
    void connectionWithDatabaseSuccessful() throws SQLException, ClassNotFoundException {


        // act
        boolean expectedResult = false;
        // assert
        boolean actualResult = Boolean.parseBoolean(databaseService.toString());
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void connectionWithDatabaseFailed() throws SQLException, ClassNotFoundException {

        boolean expectedResult = true;
        boolean actualResult = Boolean.parseBoolean(databaseService.toString());
        Assertions.assertNotEquals(expectedResult, actualResult);

    }
}


