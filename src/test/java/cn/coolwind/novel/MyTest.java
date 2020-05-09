package cn.coolwind.novel;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MyTest {

    @Test
    public void test() {
        String url = "http://www.biquge.info/74_74132/";
        try {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setActiveXNative(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setTimeout(60000);
            webClient.getOptions().setPrintContentOnFailingStatusCode(false);
            HtmlPage htmlPage = webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(20000);
            String htmlString = htmlPage.asXml();
            Document document = Jsoup.parse(htmlString);
            Elements elements = document.getElementsByTag("a");
            String href = elements.stream()
                    .filter(element -> element.hasAttr("title"))
                    .filter(element -> element.attr("title").contains("第四百四十一章 三百嫦娥总教习【中章求票】"))
                    .findFirst()
                    .get()
                    .attr("href");
            htmlPage = webClient.getPage(url+href);
            webClient.waitForBackgroundJavaScript(10000);
            htmlString = htmlPage.asXml();
            Document doc = Jsoup.parse(htmlString);
//            Document doc = Jsoup.connect(url + href).get();
            Element content = doc.getElementById("content");
            String html = content.html();
            System.out.println(html);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
