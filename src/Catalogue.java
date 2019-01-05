import java.util.ArrayList;

/**
 * Created	by Vinny Tsoi on 04/01/2019
 */
public class Catalogue {

    private String catalogueName;
    private ArrayList<Album> albumCatalogue;

    public Catalogue(String catalogueName) {
        this.catalogueName = catalogueName;
        this.albumCatalogue = new ArrayList<>();
    }

    public boolean addAlbum (Album album){
        String albumTitle = album.getAlbumTitle();
        if (findAlbum(albumTitle) == null) {
            this.albumCatalogue.add(album);
            return true;
        }

        return false;
    }

    private Album findAlbum(String albumTitle){
        for(int i=0; i < this.albumCatalogue.size(); i++){
            Album searchAlbum = this.albumCatalogue.get(i);
            if (searchAlbum.getAlbumTitle().equals(albumTitle)){
                return searchAlbum;
            }
        }

        return null;
    }

    public String getCatalogueName() {
        return catalogueName;
    }

    public ArrayList<Album> getAlbumCatalogue() {
        return albumCatalogue;
    }
} //class