package Jsoap;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginToTortuga {
    public static void main(String[] args) {
        Map<String, String> cookies;
        Connection.Response response;
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36";
        try {
            String token = "";
            //grab login form page first
            response = Jsoup
                    .connect("http://tortuga-gamestable.top/admin/index.php?route=common/login")
                    .referrer("http://www.google.com.ua/")
                    .userAgent(userAgent)
                    .timeout(10 * 1000)
                    .execute();



            cookies = response.cookies();
            response = Jsoup
                    .connect("http://tortuga-gamestable.top/admin/index.php?route=common/login")
                    .referrer("http://www.google.com.ua/")
                    .data("username", "!!!!!")
                    .data("password", "!!!!!")
                    .userAgent(userAgent)
                    .timeout(10 * 1000)
                    .cookies(cookies)
                    .method(Connection.Method.POST)
//                    .followRedirects(true)
                    .execute();

            Document doc = response.parse();
            System.out.println(doc.title());
//            cookies.putAll(response.cookies());
            System.out.println(cookies);






//            for (Element meta : doc.select("meta")) {
//                if (meta.attr("name").equals("csrf-token")) {
//                    token = meta.attr("content");
//                }
//            }
//            System.out.println(token);
//            //get the cookies from the response, which we will post to the action URL
//            Map<String, String> mapLoginPageCookies = response.cookies();
//
//            //lets make data map containing all the parameters and its values found in the form
//            Map<String, String> mapParams = new HashMap<String, String>();
//            mapParams.put("username", "!!!!!");
//            mapParams.put("password", "!!!!!!");
//            mapParams.put("redirect", "http://tortuga-gamestable.top/admin/index.php?route=common/login");
//           //URL found in form's action attribute
//            String strActionURL = "http://tortuga-gamestable.top/admin/index.php?route=common/login";
//
//            response = Jsoup.connect(strActionURL)
//                    .referrer("http://tortuga-gamestable.top/admin/index.php?route=common/login")
//                    .userAgent(userAgent)
//                    .timeout(10 * 1000)
//                    .data(mapParams)
//                    .cookies(mapLoginPageCookies)
//                    .followRedirects(true)
//                    .execute();
//            String redirectUrl = response.url().toExternalForm();
//            System.out.println(redirectUrl);
//
//            System.out.println("HTTP Status Code: " + response.statusCode());
//            //get the cookies
//            Map<String, String> mapLoggedInCookies = response.cookies();
//
//            Document doc = Jsoup.connect(redirectUrl).userAgent(userAgent).cookies(mapLoggedInCookies).get();
//            System.out.println(doc);





//            parse the document from response
//            Elements document = doc.getElementsByTag("h1");
//            String someText = document.text();
//            System.out.println(someText);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
