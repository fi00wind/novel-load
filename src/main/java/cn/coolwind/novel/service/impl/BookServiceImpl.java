package cn.coolwind.novel.service.impl;

import cn.coolwind.novel.entity.ArticleEntity;
import cn.coolwind.novel.entity.BookEntity;
import cn.coolwind.novel.repository.ArticleRepository;
import cn.coolwind.novel.repository.BookRepository;
import cn.coolwind.novel.service.BookService;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Object add(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    @Override
    public List<BookEntity> getAutoLoadBooks() {
        return bookRepository.getAllByAutoLoad(1);
    }

    @Override
    public void loadBook(int id) throws Exception {
        BookEntity book = bookRepository.getById(id);
        String title = book.getLastTitle();
        JSONObject last = loadBook(book.getUrl(), title, id,book.getMark());
        String lastTitle = last.getString("title");
        Integer lastMark = last.getInteger("mark");
        if (!title.equals(lastTitle) && book.getMark() != lastMark) {
            book.setLastTitle(lastTitle);
            book.setMark(lastMark);
            bookRepository.save(book);
        }
    }

    @Override
    public Object getAll() {
        return bookRepository.findAll();
    }

    private JSONObject loadBook(String url, String max, int bookId, int mark) throws Exception{
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setActiveXNative(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setPrintContentOnFailingStatusCode(false);
            webClient.getOptions().setTimeout(90000);
            HtmlPage htmlPage = webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(20000);
            String htmlString = htmlPage.asXml();
            Document document = Jsoup.parse(htmlString);
            Elements elements = document.getElementsByTag("a");
            List<Element> titles = elements.stream()
                    .filter(element -> element.hasAttr("title"))
                    .collect(Collectors.toList());
            int index =0;
            for (int i = 0; i < titles.size(); i++) {
                if (titles.get(i).attr("title").equals(max)) {
                    index = i;
                    break;
                }
            }
            if (index != 0 && index+1 <titles.size()) {
                titles = titles.subList(index+1, titles.size());
                for (Element title : titles) {
                    String href = title.attr("href");
                    htmlPage = webClient.getPage(url+href);
                    webClient.waitForBackgroundJavaScript(10000);
                    htmlString = htmlPage.asXml();
                    Document doc = Jsoup.parse(htmlString);
                    Element content = doc.getElementById("content");
                    String html = content.html();
                    max = title.attr("title");
                    ArticleEntity articleEntity = new ArticleEntity();
                    articleEntity.setBookId(bookId);
                    articleEntity.setContent(html);
                    articleEntity.setTitle(max);
                    articleEntity.setMark(++mark);
                    articleEntity.setLastTime(new Date());
                    articleRepository.save(articleEntity);
                }
            }

            JSONObject res = new JSONObject();
            res.put("title",max);
            res.put("mark",mark);
            return res;
    }
}
