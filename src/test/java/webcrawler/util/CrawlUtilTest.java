package webcrawler.util;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import webcrawler.exceptions.CrawlFirstException;

import java.io.IOException;

import static org.junit.Assert.*;


@RunWith(JUnitParamsRunner.class)
public class CrawlUtilTest {

    private final String WEB_SITE_URL ="http:/localhost:8000";

    @Rule
    public ExpectedException exception = ExpectedException.none();



    private static Object[] getSearchWords() {
        return new Object[] {
                "Forrest Gump",
                "elvis",

        };
    }

    @InjectMocks
    private CrawlUtil mockUtil;

    @Before
    public void init() {
        mockUtil = new CrawlUtil();
    }

    @Test
    public void pageIsCrawled() {
        assertTrue(mockUtil.crawl(WEB_SITE_URL));
    }

    @Test(expected = CrawlFirstException.class)
    public void isWebsiteCrawledCrawlExceptionTest() {
            mockUtil.searchForWord("elvis");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isUrlNotValidThrowExceptionTest() {
        try {
            mockUtil.crawl("");
        }
        catch (IllegalArgumentException e) {
            Assert.fail(String.format("THere was an unexpected %1$s thrown. Exception message: %2$s", e.getClass(), e.getMessage()));
        }
    }


    @Test
    @Parameters(method = "getSearchWords")
    public void isSearchwordPresentTest(String searchword ){
        mockUtil.crawl(WEB_SITE_URL);
        assertTrue(mockUtil.searchForWord(searchword));
    }
}