package Jsoap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LinkToTheNextPage {
    public static String symbolForParsing = ">";

    public static String linkToPage(Document docGoods) {
        String linkToPage = "";
        Elements listPages = docGoods.getElementsByClass("pagination");
        for (Element e : listPages) {
            Elements linkPage = e.select("a[href]");
            for (Element link : linkPage) {
                linkToPage = link.attr("abs:href");
                String textPage = link.text();
                if (findLinkNextPage(linkToPage, textPage)) break;
            }
        }
        return linkToPage;
    }

    public static boolean findLinkNextPage(String linkToNextPage, String textPage) {
        if (textPage.equals(symbolForParsing)) {
            System.out.println(linkToNextPage + " " + textPage);
            return true;
        }
        return false;
    }

}