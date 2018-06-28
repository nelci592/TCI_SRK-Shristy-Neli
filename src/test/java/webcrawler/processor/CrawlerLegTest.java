package webcrawler.processor;

import junitparams.JUnitParamsRunner;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.apache.tomcat.util.modeler.BaseModelMBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import webcrawler.model.BaseModel;
import webcrawler.processor.response.SpecificResponse;
import webcrawler.processor.response.WholeSiteResponse;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(JUnitParamsRunner.class)
public class CrawlerLegTest {

    @InjectMocks
    private CrawlerLeg injectedMockCrawlerLeg;

    @Mock
    private Crawler mockCrawler;

    @Mock
    private WholeSiteResponse wholeSiteResponse;

    @Mock
    private List<BaseModel> specificResponse;


    @Test
    public void isCrawlerNotNull() {
        assertNotNull(mockCrawler);
    }

}