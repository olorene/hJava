package Jsoap;

import org.jsoup.*;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import org.jsoup.nodes.Element;

public class LoginToTortugaAtt2 {
    public static void main(String[] args) throws IOException {
        String token = "";
        Connection.Response response = Jsoup
                .connect("https://publisher.assetstore.unity3d.com/")
                .execute();
        Document doc = response.parse();
        for (Element meta : doc.select("meta")) {
            if (meta.attr("name").equals("csrf-token")) {
                token = meta.attr("content");
            }
        }

        System.out.println(token);

       /* response = Jsoup
                .connect("https://id.unity.com" + doc.select("form").attr("action"))
                .method(Connection.Method.POST)
                .data("utf8", "%E2%9C%93")
                .data("_method", "put")
                .data("authenticity_token", token)
                .data("conversations_create_session_form[email]", "***@***.***")
                .data("conversations_create_session_form[password]", "********")
                .data("conversations_create_session_form[remember_me]", "true")
                .data("commit", "Log in")
                .cookies(response.cookies())
                .execute();

// С этого места мы уже авторизированны и работаем с сайтом (не забывая вставлять куки).
// Для проверки запросил страницу редактирования аккаунта.

        response = Jsoup
                .connect("https://id.unity.com/account/edit")
                .cookies(response.cookies())
                .execute();

        System.out.println(response.body());*/
    }

}
