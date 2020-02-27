package cn.coolwind.novel.controller;

import cn.coolwind.novel.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("list")
    public Object book() {
        return bookService.getAll();
    }

    @GetMapping("load/{id}")
    public Object load(@PathVariable(name = "id") int id) {
        bookService.loadBook(id);
        return "ok!";
    }
}
