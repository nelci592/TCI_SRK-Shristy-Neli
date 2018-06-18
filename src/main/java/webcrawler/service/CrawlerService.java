package webcrawler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import webcrawler.processor.CrawlerLeg;
import webcrawler.processor.response.BaseResponse;
import webcrawler.processor.response.SpecificResponse;
import webcrawler.processor.response.WholeSiteResponse;

@RestController
@RequestMapping(value = "/crawler")
public class CrawlerService {

    @Autowired
    private CrawlerLeg crawleg;

    @RequestMapping(method = RequestMethod.GET , produces = {"application/json" })
    public WholeSiteResponse getAll(@PathVariable("type") final String type) {
        return crawleg.crawlWholeSite(" " , type);
    }

    @RequestMapping(value = "/{searchword}" , method = RequestMethod.GET ,  produces = {"application/json"})
    public SpecificResponse get(@PathVariable("searchword") final String searchword) throws Exception {
        return crawleg.findSpecific(searchword);
    }
}
