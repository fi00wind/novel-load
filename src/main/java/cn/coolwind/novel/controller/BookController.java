package cn.coolwind.novel.controller;

import cn.coolwind.novel.entity.BookEntity;
import cn.coolwind.novel.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/novel/service/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("list")
    public Object book() {
        return bookService.getAll();
    }

    @GetMapping("load/{id}")
    public Object load(@PathVariable(name = "id") int id) {
        try {
            bookService.loadBook(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "err";
        }
        return "ok";
    }

    @PostMapping("add")
    public Object add(@RequestBody BookEntity bookEntity) {
        return bookService.add(bookEntity);
    }
}
