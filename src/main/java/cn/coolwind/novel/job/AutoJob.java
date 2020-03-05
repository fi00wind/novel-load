package cn.coolwind.novel.job;

import cn.coolwind.novel.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class AutoJob {

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0 0 * *  ?")
    public void deleteJob() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-2);
        articleService.deleteHasSeeBeforeTime(cal.getTime());
    }
}
