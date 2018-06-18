package webcrawler.model;

import webcrawler.dao.BaseDAO;

public class Music extends BaseModel {
    private String artist;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
