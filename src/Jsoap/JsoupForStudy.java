package Jsoap;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupForStudy {
    public static void main(String[] args) {
        String html =
                "<html>" +
                    "<head>" +
                        "<title>First parse</title>" +
                    "</head>" +
                    "<body>" +
                        "<p>Parsed HTML into a doc.</p>" +
                        "<table>" +
                            "<tr>" +
                                "<td> " +
                                    "<input type=\"checkbox\"> " +
                                "</td>" +
                                "<td> Dark Angel </td>" +
                                "<td> Blood Angel </td>" +
                            "</tr>" +
                                "<tr>" +
                                "<td> " +
                                    "<input type=\"checkbox\"> " +
                                "</td>" +
                                "<td> Dark Angel2 </td>" +
                                "<td> Blood Angel2 </td>" +
                            "</tr>" +
                        "</table>" +
                    "</body>" +
                "</html>";
        Document doc = Jsoup.parse(html);
        Elements table = doc.select("table");
        Elements td = table.select("td");
//        System.out.println(td);
        for (int i = 0; i < td.size(); i++) {
            Element tdElement = td.get(i);
            System.out.print(tdElement.text() + "= ");
        }
//        Elements rows = table.select("tr");
//
//        for(int i = 0; i < rows.size(); i++) {
//            String forPrint = rows.outerHtml() + " = ";
//            System.out.println(forPrint);
//        }

//        System.out.println(doc);
    }
}
