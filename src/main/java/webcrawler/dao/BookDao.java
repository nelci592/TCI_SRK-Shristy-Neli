package webcrawler.dao;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;
import webcrawler.model.Book;

import java.util.ArrayList;
import java.util.Arrays;


@Repository
public class BookDao extends BaseDao {
    Book book;

    public BookDao(){
        this.book = new Book();
    }

    @Override
    public Book parse(Elements elements) {
        String title = this.getContent(".media-details>h1" , elements);
        Elements rows = elements.select(".media-details>table tr");
      //  book.setName(title);

        for (int i = 0; i < rows.size(); i++)
        {
            Element row = rows.get(i);
            String key = row.select("th").text();
            String stringValue = row.select("td").text();

            switch (key) {
                case "year":
              //      book.setYear(stringValue);
                case "format":
             //       book.setFormat(stringValue);
                case "genre":
            //        book.setGenre(stringValue);
                case "publisher":
                    book.setPublisher(stringValue);
                case "isbn":
                    book.setIsbn(stringValue);
                case "authors":

                    ArrayList<String> stars = new ArrayList<>();

                    String[] authors = stringValue.split(",");

                    for (String author : authors)
                        stars.add(author);

                    book.setAuthors(Arrays.asList(authors));
            }
        }
        return book;
    }

    @Override
    public String getContent(String tag, Elements elements) {
        Elements e = elements.select(tag);
        return e.text();
    }


}
