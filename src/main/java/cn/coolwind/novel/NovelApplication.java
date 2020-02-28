package cn.coolwind.novel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NovelApplication {
   static Logger log = LoggerFactory.getLogger(NovelApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(NovelApplication.class, args);
        log.info("################系统启动成功！");
    }

}
