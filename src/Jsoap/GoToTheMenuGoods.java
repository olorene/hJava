package Jsoap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoToTheMenuGoods {
    public static String goToTheMenuGoods(Document doc) {
        Element menu = doc.getElementById("menu").getElementById("catalog");
//            System.out.println(menu.html());
//            System.out.println("================================");
        Elements liList = menu.getElementsByTag("li");

        String linkToPageGoods = "";
        for (Element e : liList) {
            if (e.text().equals("Товары")) {
//                    System.out.println(e.text());
                Elements linkGoods = e.select("a[href]");
                for (Element link : linkGoods) {
//                        System.out.println(link.attr("abs:href")/*+ " " + link.text()*/);
                    linkToPageGoods = link.attr("abs:href");
                    break;
                }
            }
        }
        return linkToPageGoods;
    }
}