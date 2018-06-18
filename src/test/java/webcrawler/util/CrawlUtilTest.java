package webcrawler.util;

        import org.junit.Test;
        import org.mockito.InjectMocks;

        import static org.junit.Assert.*;

public class CrawlUtilTest {

    private final String WEB_SITE_URL ="http:/localhost:8000";

    @InjectMocks
    private CrawlUtil mockUtil;

    @Test
    public void pageIsCrawled() {
        assertTrue(mockUtil.crawl(WEB_SITE_URL));
    }
}