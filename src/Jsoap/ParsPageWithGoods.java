package Jsoap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParsPageWithGoods {
    public static void parsPageWithGoods(Document docGoods) {
        Element table = docGoods.select("table").get(0); //select the first table.
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
        }
    }
}