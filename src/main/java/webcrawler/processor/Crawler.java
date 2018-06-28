package webcrawler.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import webcrawler.dao.BaseDAO;
import webcrawler.model.BaseModel;
import webcrawler.util.CrawlUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Crawler {
    private static final int MAX_PAGES_TO_SEARCH = 5;
    private Set<String> pagesVisited = new HashSet<>();
    private List<String> pageToVist = new ArrayList<>();
    public int pagesExplored;
    public int searchDepth;

    @Autowired
    private BaseDAO mainDao;

    @Autowired
    private CrawlUtil util;
    public List<BaseModel> search(String url , String searchWord) {

        List<BaseModel> entities=null;
        String currentUrl = url;

        this.pageToVist.add(currentUrl);

        while (this.pageToVist.size() >0) {
            String nextUrl = pageToVist.remove(0);

            if(pagesVisited.contains(currentUrl)){
                pagesVisited.add(nextUrl);
            }
            boolean success = util.searchForWord(searchWord);

            currentUrl = nextUrl;
            if (success) {
                BaseModel entity = this.mainDao.parse(util.getDivWithMediaDetails());
                if(entity!= null) {
                    entities.add(entity);
                    if(entities.size()>0 && searchWord !=null){
                        return entities;
                    }
                }
            }
            util.crawl(currentUrl);
            this.pageToVist.addAll(util.getLinks());
        }
        return entities;
    }
}
