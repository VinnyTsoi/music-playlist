import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created	by Vinny Tsoi on 04/01/2019
 */
public class Album {

    private String albumTitle;
    private String albumArtist;
    private ArrayList<Song> albumSongs;

    public Album(String albumTitle, String albumArtist) {
        this.albumTitle = albumTitle;
        this.albumArtist = albumArtist;
        this.albumSongs = new ArrayList<>();
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public ArrayList<Song> getAlbumSongs() {
        return albumSongs;
    }

    public boolean addSongToAlbum(String songTitle, double songDuration){
        if (findSong(songTitle) == null){
            this.albumSongs.add(new Song(songTitle,songDuration));
            return true;
        }

        return false;
    }

    private Song findSong(String songTitle){
        for(int i=0; i < this.albumSongs.size(); i++){
            Song checkedSong = this.albumSongs.get(i);
            if (checkedSong.getTitle().equals(songTitle)){
                return checkedSong;
            }
        }

        return null;
    }

    public boolean addSongToPlaylist(int trackNumber, LinkedList<Song> playlist){
        int index = trackNumber - 1;
        if ((index >= 0) && (index < this.albumSongs.size())) {
            playlist.add(this.albumSongs.get(index));
            return true;
        }

        System.out.println("No track " + trackNumber + " in " + this.albumTitle);
        return false;
    }

    public boolean addSongToPlaylist(String songTitle, LinkedList<Song> playlist){
        Song searchedSong = findSong(songTitle);
        if (searchedSong != null){
            playlist.add(searchedSong);
            return true;
        }

        System.out.println("No song " + songTitle + " in " + this.albumTitle);
        return false;
    }

} //class