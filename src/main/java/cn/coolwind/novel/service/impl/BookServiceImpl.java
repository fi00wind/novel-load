package cn.coolwind.novel.service.impl;

import cn.coolwind.novel.entity.ArticleEntity;
import cn.coolwind.novel.entity.BookEntity;
import cn.coolwind.novel.repository.ArticleRepository;
import cn.coolwind.novel.repository.BookRepository;
import cn.coolwind.novel.service.BookService;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Object add(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    @Override
    public void loadBook(int id) {
        BookEntity book = bookRepository.getOne(id);
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

    private JSONObject loadBook(String url, String max, int bookId, int mark) {
        try {
            Document document = Jsoup.connect(url).get();
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
                    Document doc = Jsoup.connect(url + href).get();
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
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            JSONObject res = new JSONObject();
            res.put("title",max);
            res.put("mark",mark);
            return res;
        }
    }
}
