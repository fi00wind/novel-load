package cn.coolwind.novel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MyTest {

    @Test
    public void test() {
        String url = "http://www.biquge.info/12_12696/";
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByTag("a");
            String href = elements.stream()
                    .filter(element -> element.hasAttr("title"))
                    .filter(element -> element.attr("title").contains("897"))
                    .findFirst()
                    .get()
                    .attr("href");
            Document doc = Jsoup.connect(url + href).get();
            Element content = doc.getElementById("content");
            String html = content.html();
            System.out.println(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
