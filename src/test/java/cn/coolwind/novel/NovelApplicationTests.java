package cn.coolwind.novel;

import cn.coolwind.novel.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "prod")
class NovelApplicationTests {
    @Autowired
    private BookService bookService;

    @Test
    void contextLoads() {
        try {
            bookService.loadBook(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
