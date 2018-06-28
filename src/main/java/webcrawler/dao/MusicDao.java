package webcrawler.dao;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;
import webcrawler.model.Music;

@Repository
public class MusicDao extends BaseDao{
    private Music music;

    public MusicDao(){
        music = new Music();
    }

    @Override
    public Music parse(Elements elements) {

        String title = this.getContent(".media-details>h1", elements);
        Elements rows = elements.select(".media-details>table tr");

        music.setName(title);

        for (int i = 0; i < rows.size(); i++) {
            Element row = rows.get(i);
            String key = row.select("th").text();
            String stringValue = row.select("td").text();

            switch (key) {
                case "year":
                    music.setYear(stringValue);
                case "format":
                    music.setFormat(stringValue);
                case "genre":
                    music.setGenre(stringValue);
                case "artist":
                    music.setArtist(stringValue);
            }
        }
        return music;
    }

    @Override
    public String getContent(String tag, Elements elements) {
        Elements e = elements.select(tag);
        return e.text();

    }
}
