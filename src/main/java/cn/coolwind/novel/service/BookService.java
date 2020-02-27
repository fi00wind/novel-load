package cn.coolwind.novel.service;

import cn.coolwind.novel.entity.BookEntity;

public interface BookService {
    Object getAll();

    void loadBook(int id);

    Object add(BookEntity bookEntity);
}
