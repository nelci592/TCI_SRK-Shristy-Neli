package webcrawler.dao;

import org.jsoup.select.Elements;
import webcrawler.model.BaseModel;

public interface BaseDAO {
    BaseModel parse(Elements elements);
    String getContent(String tag , Elements elements);
}
