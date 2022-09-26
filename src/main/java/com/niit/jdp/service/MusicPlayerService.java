package com.niit.jdp.service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * It plays a song from the playlist
 */
public class MusicPlayerService {
    /**
     * This function is used to play the song from the playlist
     *
     * @param //connection The connection object to the database.
     * @param //songPath   The path of the song to be played.
     */
    public void play(Connection connection, String songPath) throws SQLException {

        int songID;
        try (Statement statement = connection.createStatement()) {
            Scanner input = new Scanner(System.in);
            System.out.println();
            System.out.println("Select Song ID  from the list to play the song  : ");
            songID = input.nextInt();
            statement.executeQuery("SELECT * FROM `playlist` WHERE song_id = '" + songID + "'");
        }
        if (songID == 101) {
            songPath = "src/main/resources/Song1.wav";
        } else if (songID == 102) {
            songPath = "src/main/resources/Song2.wav";
        } else if (songID == 3) {
            songPath = "src/main/resources/songs/song3.wav";
        } else if (songID == 4) {
            songPath = "src/main/resources/songs/song4.wav";
        } else if (songID == 5) {
            songPath = "src/main/resources/songs/song5.wav";
        }
        File file = new File(songPath);

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            Scanner scanner = new Scanner(System.in);
            int input;
            int temp = 0;
            long Clip = 0;
            while (temp == 0) {
                System.out.println("Enter your choice");
                System.out.println("PRESS --2-- to PAUSE");
                System.out.println("PRESS --4-- to RESUME");
                System.out.println("PRESS --6-- to RESTART");
                System.out.println("PRESS --8-- to QUIT");

                input = scanner.nextInt();
                switch (input) {
                    case 2: {
                        Clip = clip.getMicrosecondPosition();
                        clip.stop();
                        System.out.println("<<=================================>>");
                        System.out.println("Song is Paused");
                        System.out.println("<<=================================>>");
                        break;
                    }
                    case 4: {
                        clip.setMicrosecondPosition(Clip);
                        clip.start();
                        System.out.println("<<======================================>>");
                        System.out.println("Song is Resumed");
                        System.out.println("<<=======================================>>");
                        break;
                    }
                    case 6: {
                        clip.setMicrosecondPosition(0);
                        clip.start();
                        System.out.println("<<=======================================>>");
                        System.out.println("Song is restarted");
                        System.out.println("<<=======================================>>");
                        break;
                    }
                    case 8: {
                        clip.stop();
                        System.out.println("<<=====================================>>");
                        System.out.println("Exit");
                        System.out.println("<<=====================================>>");
                        break;
                    }
                    default: {
                        System.err.println("!!Try the choice has per the menu!!");
                        break;
                    }


                }


            }


        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException exception) {
            exception.printStackTrace();
        }
    }
}