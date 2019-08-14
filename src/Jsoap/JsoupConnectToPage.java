package Jsoap;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

public class JsoupConnectToPage {
    public Document connectToPage(String link, Map<String, String> cookies, String userAgent) throws IOException {
        Document doc = null;

        Connection.Response homePage = Jsoup.connect(link)
                .userAgent(userAgent)
                .timeout(10 * 1000)
                .cookies(cookies)
                .method(Connection.Method.POST)
                .execute();
        doc = homePage.parse();
        return doc;
    }
}
