import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MyJsoap {
    public static void main( String[] args ) throws IOException{
//        Document doc = Jsoup.connect("https://google.com.ua/").get();
        Document doc = Jsoup.connect("https://www.eclipse.org/").get();
        String title = doc.title();
        System.out.println("Title : " + title);

    }
}
