package Jsoap;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginToTortuga {
    public static void main(String[] args) {
        Map<String, String> cookies;
        Connection.Response response;
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36";
        String login = "lordus";
        String password = "vorlon2258";
        try {
            String token = "";
            //grab login form page first
            response = Jsoup.connect("https://tortuga-gamestable.top/admin/")
                    .referrer("http://www.google.com.ua/")
                    .userAgent(userAgent)
                    .timeout(10 * 1000)
                    .execute();
            cookies = response.cookies();

            Connection.Response homePage = Jsoup.connect("https://tortuga-gamestable.top/admin/")
                    .referrer("http://www.google.com.ua/")
                    .data("username", login)
                    .data("password", password)
                    .userAgent(userAgent)
                    .timeout(10 * 1000)
                    .cookies(cookies)
                    .method(Connection.Method.POST)
//                    .followRedirects(true)
                    .execute();

            Document doc = homePage.parse();
//            Print title of page
//            String title = doc.title();
//            System.out.println(title);

//            ###############################
            Element menu = doc.getElementById("menu").getElementById("catalog");
//            System.out.println(menu.html());
//            System.out.println("================================");
            Elements liList = menu.getElementsByTag("li");

            String linkToPageGoods = "";
            for (Element e : liList) {
                if (e.text().equals("Товары") ) {
//                    System.out.println(e.text());
                    Elements linkGoods = e.select("a[href]");
                    for (Element link : linkGoods) {
//                        System.out.println(link.attr("abs:href")/*+ " " + link.text()*/);
                        linkToPageGoods = link.attr("abs:href");
                        break;
                    }
                }
            }
            JsoupConnectToPage connectToPageGoods = new JsoupConnectToPage();
            Document docGoods = connectToPageGoods.connectToPage(linkToPageGoods, cookies, userAgent);

//            Pars page with goods/
/*            Element table = docGoods.select("table").get(0); //select the first table.
            Elements rows = table.select("tr");
            for (int i = 0; i < rows.size(); i++) { //first row is the col names so skip it.
                Element row = rows.get(i);
                Elements cols = row.select("td");
                System.out.println(cols.get(0).text());
                System.out.println(cols.get(1).text());
                System.out.println(cols.get(2).text());
                System.out.println(cols.get(3).text());
                System.out.println(cols.get(4).text());
                System.out.println(cols.get(5).text());
                System.out.println(cols.get(6).text());
                System.out.println(cols.get(7).text());
            }*/
//            ###########################################
            String linkToNextPage = "";
            Elements listPages = docGoods.getElementsByClass("pagination");
            for (Element e : listPages) {
                Elements linkPage = e.select("a[href]");
                for (Element link : linkPage) {
                    linkToNextPage = link.attr("abs:href");
                    String textPage = link.text();
                    if (textPage.equals(">")){
                        System.out.println(linkToNextPage + " " + textPage);
                        break;
                    }
                }
            }

            Document docGoodsNextPage = connectToPageGoods.connectToPage(linkToNextPage, cookies, userAgent);

            //            Pars page with goods/
            Element table = docGoodsNextPage.select("table").get(0); //select the first table.
            Elements rows = table.select("tr");
            for (int i = 0; i < rows.size(); i++) { //first row is the col names so skip it.
                Element row = rows.get(i);
                Elements cols = row.select("td");
                System.out.println(cols.get(0).text());
                System.out.println(cols.get(1).select("[src]").attr("abs:src"));
                System.out.println(cols.get(2).text());
                System.out.println(cols.get(3).text());
                System.out.println(cols.get(4).text());
                System.out.println(cols.get(5).text());
                System.out.println(cols.get(6).text());
                System.out.println(cols.get(7).text());
            }
//            ###########################################

//            Print title of page
//            String title = docGoods.title();
//            System.out.println(title);

//              Print all tag of page
//            System.out.println(docGoods.html());
//            System.out.println("============================================");






        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
