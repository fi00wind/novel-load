package cn.coolwind.novel.job;

import cn.coolwind.novel.service.ArticleService;
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

    @Scheduled(cron = "0 10 15 * *  ?")
    public void deleteJob() {
        log.info("---------------开始删除已读文章");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-2);
        articleService.deleteHasSeeBeforeTime(cal.getTime());
        log.info("---------------删除已读文章成功！");
    }
}
