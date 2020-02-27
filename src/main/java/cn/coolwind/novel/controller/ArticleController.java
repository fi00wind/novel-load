package cn.coolwind.novel.controller;

import cn.coolwind.novel.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("list/{id}")
    public Object list(@PathVariable(name = "id") int bookId) {
        return articleService.getByBookId(bookId);
    }

    @GetMapping("read/{id}")
    public Object read(@PathVariable(name = "id") int articleId) {
        return articleService.setHasRead(articleId);
    }

    @GetMapping("{id}")
    public Object showArticle(@PathVariable(name = "id") int id){
        Object res = articleService.getById(id);
        return res;
    }

    @GetMapping("next")
    public Object next(int id, int mark) {
        return articleService.getNextArticle(id,mark);
    }
}
