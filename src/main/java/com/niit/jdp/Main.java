package com.niit.jdp;

import com.niit.jdp.repository.PlaylistRepository;
import com.niit.jdp.repository.SongRepository;
import com.niit.jdp.exception.PlaylistNotFoundException;
import com.niit.jdp.exception.SongNotFoundException;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;
import com.niit.jdp.service.MusicPlayerService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * The above function is the main function of the program. It is the entry point of the program.
     */
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("<<======================================>>");
            System.out.println("Welcome to the JukeBox Music Player");
            System.out.println("*<<====================================>>*");
            System.out.println("1.View the existing playlist");
            System.out.println("2.Search a Song using Song_ID");
            System.out.println("3.Play a song from playlist by use the Song_ID");
            System.out.println("4.Create a new playlist to store the songs");
            System.out.println("5.Delete the playlist by using the Playlist_ID");
            System.out.println("6.If you want exist the press 6");
            System.out.println("*<---------------------------------------->*");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Please choose according your wish: ");
            choice = scanner.nextInt();

            DatabaseService databaseService = new DatabaseService();
            try {
                boolean connect = databaseService.connect();
                System.out.println(connect);
                databaseService.printConnectionStatus();
                Connection connection = databaseService.getConnection();
                SongRepository<Song> songRepository = new SongRepository<Song>();
                PlaylistRepository playlistRepository = new PlaylistRepository();
                MusicPlayerService musicPlayerService = new MusicPlayerService();

                switch (choice) {
                    case 1: {
                        System.out.println();
                        System.out.println("View all of the exists Playlist");
                        System.out.println("===========================");
                        playlistRepository.getAll(connection);
                        break;
                    }
                    case 2: {

                        System.out.println();
                        playlistRepository.getAll(connection);
                        System.out.println("-------------------------------------------------------");
                        System.out.println("Enter Song ID to describe the  full song and its details :");
                        int id = scanner.nextInt();
                        Song byId = songRepository.getById(connection, id);
                        if (byId.getSongId() == 0) {
                            throw new SongNotFoundException("The song is not present in the list!! Sorry Please try again.");
                        } else {
                            System.out.println(byId);
                        }
                        break;
                    }
                    case 3: {
                        System.out.println();
                        System.out.println("playlist of which are you selected ID");
                        System.out.println();
                        playlistRepository.getAll(connection);
                        musicPlayerService.play(connection, null);
                        break;
                    }
                    case 4: {
                        System.out.println();
                        System.out.println("Please enter the Playlist Name as your wish : ");
                        String playlistName = scanner.next();
                        System.out.println("Playlist is created : " + playlistName);
                        System.out.println("Enter 1 to add song to your playlist by using song id  : ");
                        int next = scanner.nextInt();
                        if (1 == next) {
                            List<Song> all = songRepository.getAll(connection);
                            System.out.println(all);
                            System.out.println("Please enter Song_ID to add in the playlist :");
                            int songId = scanner.nextInt();
                            System.out.println("Please enter Song Name to add in the playlist : ");
                            String songName = scanner.next();
                            boolean playlist = playlistRepository.createPlaylist(connection, playlistName, songId, songName);
                            System.out.println(playlist);
                            System.out.println("Please Enter 2 to continue adding song in your playlist or 0 to stop adding the song");
                            scanner.nextInt();
                        }
                        break;
                    }
                    case 5: {
                        System.out.println();
                        List<Playlist> all = playlistRepository.getAll(connection);
                        System.out.println(all);
                        System.out.println("Please Enter the playlist ID to delete : ");
                        int playlistId = scanner.nextInt();
                        playlistRepository.deleteById(connection, playlistId);
                        if (playlistId == 0) {
                            throw new PlaylistNotFoundException("The playlist ID is not in the list!! Sorry please Try Again.");
                        }
                        break;
                    }
                    default:
                        System.out.println("Sorry this in not exists!");
                }
            } catch (SQLException | SongNotFoundException | PlaylistNotFoundException exception) {
                System.err.println("Could not connect to the database please check again!");
                exception.printStackTrace();
                choice = 5;
            }
        } while (choice != 5);
    }
}