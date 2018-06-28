package webcrawler.util;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import webcrawler.exceptions.CrawlFirstException;

import java.io.IOException;

import static org.junit.Assert.*;


@RunWith(JUnitParamsRunner.class)
public class CrawlUtilTest {

    private final String WEB_SITE_URL ="http:/localhost:8000";

    @InjectMocks
    private CrawlUtil mockUtil;

    @Test
    public void pageIsCrawled() {
        assertTrue(mockUtil.crawl(WEB_SITE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isWebsiteCrawledCrawlExceptionTest() {
        try {
            mockUtil.searchForWord("elvis");
        }
        catch (CrawlFirstException e) {
            Assert.fail(String.format("THere was an unexpected %1$s thrown. Exception message: %2$s", e.getClass(), e.getMessage()));
        }
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

}