package webcrawler.dao;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;
import webcrawler.model.Movie;

import java.util.ArrayList;

@Repository
public class MovieDao extends BaseDao {

    Movie movie;

    public MovieDao() {
        movie = new Movie();

    }
    @Override
    public Movie parse(Elements elements) {
        String title = this.getContent(".media-details>h1", elements);
        Elements rows = elements.select(".media-details>table tr");

       // movie.setName(title);

        for (int i = 0; i < rows.size(); i++) {
            Element row = rows.get(i);
            String key = row.select("th").text();
            String stringValue = row.select("td").text();

            switch (key) {
                case "year":
             //       movie.setYear(stringValue);
                case "format":
             //       movie.setFormat(stringValue);
                case "genre":
             //       movie.setGenre(stringValue);
                case "director":
                    movie.setDirector(stringValue);

                case "stars":
                    ArrayList<String> stars = new ArrayList<>();
                    String[] starsOf = stringValue.split(",");
                    for (String star : starsOf)
                        stars.add(star);
                    movie.setStars(stars);

                case "writers":
                    ArrayList<String> writers = new ArrayList<>();
                    String[] writersOf = stringValue.split(",");
                    for (String writer : writersOf)
                        writers.add(writer);
                    movie.setStars(writers);
            }
        }
        return movie;
    }

    @Override
    public String getContent(String tag, Elements elements) {
        Elements e = elements.select(tag);
        return e.text();
    }
}
