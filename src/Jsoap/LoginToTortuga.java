package Jsoap;

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
        String userAgent = "Mozilla/5.0";
        try {
            String token = "";
            //grab login form page first
            Connection.Response response = Jsoup
                    .connect("http://tortuga-gamestable.top/admin/index.php?route=common/login")
                    .referrer("http://www.google.com.ua/")
                    .userAgent(userAgent)
                    .timeout(10 * 1000)
//                    .followRedirects(true)
                    .execute();

                Document doc = response.parse();
            for (Element meta : doc.select("meta")) {
                if (meta.attr("name").equals("csrf-token")) {
                    token = meta.attr("content");
                }
            }
            System.out.println(token);
//            //get the cookies from the response, which we will post to the action URL
//            Map<String, String> mapLoginPageCookies = response.cookies();
//
//            //lets make data map containing all the parameters and its values found in the form
//            Map<String, String> mapParams = new HashMap<String, String>();
//            mapParams.put("username", "Lordus");
//            mapParams.put("password", "vorlon2258");
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
