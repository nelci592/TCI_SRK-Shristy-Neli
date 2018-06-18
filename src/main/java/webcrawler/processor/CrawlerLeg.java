package webcrawler.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.model.BaseModel;
import webcrawler.model.Book;
import webcrawler.model.Movie;
import webcrawler.model.Music;
import webcrawler.processor.response.BaseResponse;
import webcrawler.processor.response.SpecificResponse;
import webcrawler.processor.response.WholeSiteResponse;


import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlerLeg {
    private final String WEBSITE_URL = "http//localhost:8000";
    private int id = 0;

    @Autowired
    private Crawler entityCrawler;

    public WholeSiteResponse crawlWholeSite(String keyword , String type ) {
        if (type == null || type.isEmpty()) {
            type = "all";
        }
        String timestamp = new Timestamp(System.currentTimeMillis()).getTime() + "";
        Instant beginTime = Instant.now();

        List<BaseModel> entities = entityCrawler.search(WEBSITE_URL, keyword);
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Movie> movies = new ArrayList();
        ArrayList<Music> musics = new ArrayList<>();

        if (type.equals("all")) {
            for (BaseModel entity : entities) {
                if (entity instanceof Book) {
                    books.add((Book) entity);
                } else if (entity instanceof Music) {
                    musics.add((Music) entity);
                } else if (entity instanceof Movie) {
                    movies.add((Movie) entity);
                } }
        }
        return new WholeSiteResponse(id++, timestamp, Duration.between(beginTime, Instant.now()).getSeconds() + " ", entityCrawler.pagesExplored + " ", entityCrawler.searchDepth + " ", books, movies, musics);
    }


    public SpecificResponse findSpecific (String searchWord) throws Exception {
        String timestamp = new Timestamp(System.currentTimeMillis()).getTime() + "";
        Instant beginTime = Instant.now();

        List<BaseModel> results = entityCrawler.search(WEBSITE_URL, searchWord);

        if (results.size() == 0)
            return new SpecificResponse(id++, timestamp, Duration.between(beginTime, Instant.now()).getSeconds() + "", entityCrawler.pagesExplored + "", entityCrawler.searchDepth + "", null);
        if (results.size() != 1)
            throw new Exception("Too many results obtained"); // should never happen, defensive check

        BaseModel result = results.get(0);
        return new SpecificResponse(id++, timestamp, Duration.between(beginTime, Instant.now()).getSeconds() + "", entityCrawler.pagesExplored + "", entityCrawler.searchDepth + "", result);
    }
}
