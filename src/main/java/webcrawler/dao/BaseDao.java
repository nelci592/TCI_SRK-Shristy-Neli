package webcrawler.dao;

import org.jsoup.select.Elements;
import webcrawler.model.BaseModel;

public abstract  class BaseDao implements BaseDAO {
    @Override
    public BaseModel parse(Elements elements) {
        return null;
    }

    @Override
    public String getContent(String tag, Elements elements) {
        return null;
    }
}
