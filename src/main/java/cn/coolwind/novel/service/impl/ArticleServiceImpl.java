package cn.coolwind.novel.service.impl;

import cn.coolwind.novel.entity.ArticleEntity;
import cn.coolwind.novel.entity.ArticleSimple;
import cn.coolwind.novel.repository.ArticleRepository;
import cn.coolwind.novel.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Object getById(int id) {
        return articleRepository.getOne(id);
    }

    @Override
    public Object setHasRead(int articleId) {
        ArticleEntity article = articleRepository.getOne(articleId);
        article.setSee(true);
        articleRepository.save(article);
        return true;
    }

    @Override
    public Object getByBookId(int bookId) {
        return articleRepository.getAllByBookIdOrderByIdDesc(bookId)
                .stream()
                .map(this::articleSimpleConvert);
    }

    private ArticleSimple articleSimpleConvert(ArticleEntity articleEntity) {
        ArticleSimple res = new ArticleSimple();
        BeanUtils.copyProperties(articleEntity,res);
        return res;
    }
}
