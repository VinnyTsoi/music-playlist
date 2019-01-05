import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Created	by Vinny Tsoi on 04/01/2019
 */
public class Main {

    public static void main(String[] args) {

        Catalogue myCatalogue = new Catalogue("My Catalogue");

        Album album = new Album("Bad", "Michael Jackson");

        album.addSongToAlbum("Bad", 4.08);
        album.addSongToAlbum("The Way You Make Me Feel", 4.59);
        album.addSongToAlbum("Speed Demon", 4.03);
        album.addSongToAlbum("Liberian Girl", 3.55);
        album.addSongToAlbum("Just Good Friends", 4.09);
        album.addSongToAlbum("Another Part Of Me", 3.55);

        myCatalogue.addAlbum(album);

        album = new Album("Goodbye Country (Hello Nightclub)", "Grove Armada");

        album.addSongToAlbum("Suntoucher", 6.31);
        album.addSongToAlbum("Superstylin", 6.00);
        album.addSongToAlbum("Drifted", 4.54);
        album.addSongToAlbum("Little By Littel", 5.30);
        album.addSongToAlbum("Fogma", 6.53);
        album.addSongToAlbum("My Friend", 5.00);

        myCatalogue.addAlbum(album);

        LinkedList<Song> myPlaylist = new LinkedList<>();
        myCatalogue.getAlbumCatalogue().get(0).addSongToPlaylist("Bad", myPlaylist);
        myCatalogue.getAlbumCatalogue().get(0).addSongToPlaylist("Liberian Girl", myPlaylist);
        myCatalogue.getAlbumCatalogue().get(0).addSongToPlaylist("Another Part Of Me", myPlaylist);
        myCatalogue.getAlbumCatalogue().get(0).addSongToPlaylist(2, myPlaylist);
        myCatalogue.getAlbumCatalogue().get(0).addSongToPlaylist(5, myPlaylist);
        myCatalogue.getAlbumCatalogue().get(0).addSongToPlaylist("Cotton Eye Joe", myPlaylist); //no such track

        myCatalogue.getAlbumCatalogue().get(1).addSongToPlaylist("Drifted", myPlaylist);
        myCatalogue.getAlbumCatalogue().get(1).addSongToPlaylist("Fogma", myPlaylist);
        myCatalogue.getAlbumCatalogue().get(1).addSongToPlaylist("Superstylin", myPlaylist);
        myCatalogue.getAlbumCatalogue().get(1).addSongToPlaylist(18, myPlaylist); //no such track

        play(myPlaylist);

    } //main




    private static void play(LinkedList<Song> playlist){
        ListIterator<Song> songListIterator = playlist.listIterator();
        Scanner scanner = new Scanner(System.in);
        boolean goingForward = true;
        boolean quit = false;

        String options = "=============\n" +
                "Playlist Options\n" +
                "1 - Forward\n" +
                "2 - Back\n" +
                "3 - Replay\n" +
                "4 - List Songs\n" +
                "5 - Remove Song\n" +
                "6 - List Options\n" +
                "7 - Quit\n" +
                "What would you like to do?";

        if (playlist.size() == 0){
            System.out.println("No songs in playlist");
            return;
        } else {

            if(!goingForward){
                if (songListIterator.hasNext()){
                    songListIterator.next();
                }
                goingForward = true;
            }
            if (songListIterator.hasNext()){
                Song currentSong = songListIterator.next();
                System.out.println("\n--> Now playing - " + currentSong.toString());
            } else {
                System.out.println("\nNo more songs in playlist");
            }

            printOptions(options);

            while (!quit){
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice){

                    case 1: { //forward
                        if(!goingForward){
                            if (songListIterator.hasNext()){
                                songListIterator.next();
                            }
                            goingForward = true;
                        }
                        if (songListIterator.hasNext()){
                            Song currentSong = songListIterator.next();
                            System.out.println("--> Now playing - " + currentSong.toString());
                        } else {
                            System.out.println("No more songs in playlist");
                            goingForward = false;
                        }
                        break;
                    }

                    case 2: { //back
                        if(goingForward){
                            if(songListIterator.hasPrevious()){
                                songListIterator.previous();
                            }
                            goingForward = false;
                        }
                        if (songListIterator.hasPrevious()){
                            Song currentSong = songListIterator.previous();
                            System.out.println("--> Now playing - " + currentSong.toString());
                        } else {
                            System.out.println("No more previous songs in playlist");
                            goingForward = true;
                        }
                        break;
                        }

                        case 3: { //replay
                            if(goingForward == false){
                                if(songListIterator.hasNext()){
                                    songListIterator.next();
                                    goingForward = true;
                                }
                            }
                            if(goingForward == true){
                                if(songListIterator.hasPrevious()){
                                    songListIterator.previous();
                                    goingForward = true;
                                }
                            }
                            if (songListIterator.hasNext()){
                                Song currentSong = songListIterator.next();
                                System.out.println("--> Now replaying - " + currentSong.toString());
                            } else {
                                System.out.println("No more previous songs in playlist");
                            }
                            break;
                        }

                         case 4: { //List songs
                             listSongs(playlist);
                             break;
                         }

                        case 5: { //remove
                            if(playlist.size()>0){
                                songListIterator.remove();
                                System.out.println("Song removed from playlist");

                                if(songListIterator.hasNext()) {
                                    System.out.println("--> Now replaying - " + songListIterator.next());
                                    goingForward = true;
                                } else if(songListIterator.hasPrevious()){
                                    System.out.println("--> Now replaying - " + songListIterator.previous());
                                    goingForward = false;
                                }
                            }
                            break;
                        }

                        case 6: { //list options
                            printOptions(options);
                            break;
                        }

                        case 7: { //quit
                            quit = true;
                            System.out.println("Goodbye");
                            break;
                        }

                        default: {
                            System.out.println("Invalid choice");
                            break;
                        }
                    } //switch
                }
            }
        }

    private static void listSongs(LinkedList<Song> playlist) {
        if (playlist.size() == 0) {
            System.out.println("No songs in playlist");
            return;
        } else {
            System.out.println("PLAYLIST\n========");
            for(int i = 0; i < playlist.size(); i++){
                Song currentSong = playlist.get(i);
                System.out.println("--> " + currentSong.toString());
            }
            System.out.println("========");
            return;
        }
    }

    private static void printOptions(String options) {
        System.out.println(options);
    }
}
