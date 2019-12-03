package Jsoap;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
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
//            printTitleOfPage(doc);

//            ###############################
            String linkToPageGoods = GoToTheMenuGoods.goToTheMenuGoods(doc);

            JsoupConnectToPage connectToPageGoods = new JsoupConnectToPage();
            Document docGoods = connectToPageGoods.connectToPage(linkToPageGoods, cookies, userAgent);

//            Pars page with goods - TO DO
//            ParsPageWithGoods.parsPageWithGoods(docGoods);
//            ###########################################
            String linkToNextPage = LinkToTheNextPage.linkToPage(docGoods);
//            System.out.println(linkToNextPage);


            //            Pars page with goods/
            Document docGoodsNextPage = connectToPageGoods.connectToPage(linkToNextPage, cookies, userAgent);
//            Element table = docGoodsNextPage.select("table").get(0); //select the first table.
//            Elements rows = table.select("tr");
//            for (int i = 0; i < rows.size(); i++) { //first row is the col names so skip it.
//                Element row = rows.get(i);
//                Elements cols = row.select("td");
//                System.out.println(cols.get(0).text());
//                System.out.println(cols.get(1).select("[src]").attr("abs:src"));
//                System.out.println(cols.get(2).text());
//                System.out.println(cols.get(3).text());
//                System.out.println(cols.get(4).text());
//                System.out.println(cols.get(5).text());
//                System.out.println(cols.get(6).text());
//                System.out.println(cols.get(7).text());
//            }
//            ###########################################


//              Print all tag of page
//            System.out.println(docGoods.html());
//            System.out.println("============================================");


/*            JsoapJdbc jsoapJdbc = new JsoapJdbc();
            try {
                jsoapJdbc.connectToDb();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }*/


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public static void printTitleOfPage(Document doc) {
        String title = doc.title();
        System.out.println(title);
    }

}
