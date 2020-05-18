package cn.coolwind.novel.job;

import cn.coolwind.novel.entity.BookEntity;
import cn.coolwind.novel.service.ArticleService;
import cn.coolwind.novel.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class AutoJob {
    final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ArticleService articleService;

    @Autowired
    private BookService bookService;

    @Scheduled(cron = "0 10 15 * *  ?")
    public void deleteJob() {
        log.info("---------------开始删除已读文章");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-2);
        articleService.deleteHasSeeBeforeTime(cal.getTime());
        log.info("---------------删除已读文章成功！");
    }

    @Scheduled(cron = "0 0 2 * *  ?")
    public void loadLatest() {
            bookService.getAutoLoadBooks()
                    .stream()
                    .map(BookEntity::getId)
                    .forEach(id-> {
                        try {
                            bookService.loadBook(id);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
        }

}
