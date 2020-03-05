package cn.coolwind.novel.service;

import java.util.Date;

public interface ArticleService {
    Object getByBookId(int bookId);

    Object getById(int id);

    Object setHasRead(int articleId);

    Object getNextArticle(int id, int mark, int bookId);

    void deleteHasSeeBeforeTime(Date time);
}
