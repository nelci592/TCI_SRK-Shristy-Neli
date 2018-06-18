package webcrawler.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class CrawlUtil {
    private final String div = "media-details";

    // Constant fake user agent :
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<>();
    private Document htmlDocument;

    public boolean crawl (String url) {
        try {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;

            // Check if the connection was successful:
            if (connection.response().statusCode() == 200) {
                System.out.println("\n**Visiting** Received web page at " + url);
            }

            // Check if the response is the html :
            if (!connection.response().contentType().contains("text/html")) {
                System.out.println("**Failure** Retrieved something other than html");
                return false;
            }

            Elements linksOnpage = htmlDocument.select("a[href]");
            System.out.println("Found (" + linksOnpage.size() + ") links");

            // Add all the links on the page to the list of the links :
            for (Element link : linksOnpage) {
                this.links.add(link.absUrl("href"));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Function to search for specific word:
     *
     */
    public boolean searchForWord(String searchWord){
        // Defensive coding . This method should be usede only after successful crawl.
        if (this.htmlDocument == null) {
            System.out.println("ERROR! call crawl before performing analysis on the document.");
            return false;
        }

        System.out.println("Searching for the word");
        String bodyText = this.htmlDocument.body().text();
        return bodyText.toLowerCase().contains(searchWord.toLowerCase());
    }

    /**
     * Function to get all the links:
     */
    public List<String> getLinks() {
        return this.links;

    }

    public Elements getDivWithMediaDetails() {
        return this.htmlDocument.body().select("div." + this.div);
    }



    public String getBody() {
        return  this.htmlDocument.body().text();
    }

}
