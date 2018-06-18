package webcrawler.processor.response;

import webcrawler.model.Book;
import webcrawler.model.Movie;
import webcrawler.model.Music;

import java.util.List;

public class WholeSiteResponse extends BaseResponse {

        public List<Book> booksList ;
        public List<Movie> movieList;
        public List<Music> musicList;

        public WholeSiteResponse(int id ,String timeStamp , String secondsTaken , String pagesExplored , String searchDepth , List<Book> booksList , List<Movie> movieList , List<Music> musicList) {
            super(id , timeStamp , secondsTaken , pagesExplored , searchDepth);
            this.booksList = booksList;
            this.movieList = movieList;
            this.musicList = musicList;
        }
}
