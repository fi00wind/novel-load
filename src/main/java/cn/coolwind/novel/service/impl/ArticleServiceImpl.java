package cn.coolwind.novel.service.impl;

import cn.coolwind.novel.entity.ArticleEntity;
import cn.coolwind.novel.entity.ArticleSimple;
import cn.coolwind.novel.repository.ArticleRepository;
import cn.coolwind.novel.service.ArticleService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Object getById(int id) {
        return articleRepository.getOne(id);
    }

    @Override
    public Object getNextArticle(int id, int mark, int bookId) {
        JSONObject res = new JSONObject();
        setHasRead(id);
        ArticleEntity article = articleRepository.getByBookIdAndMark(bookId, mark);
        if (article != null) {
            res.put("code", "0000");
            res.put("id",article.getId());
            res.put("data",article);
        }else {
            res.put("code", "1111");
        }
        return res;
    }

    @Override
    public Object setHasRead(int articleId) {
        ArticleEntity article = articleRepository.getOne(articleId);
        if (article.getSee()==null || !article.getSee()) {
            article.setSee(true);
            articleRepository.save(article);
        }
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

    @Override
    @Transactional
    public void deleteHasSeeBeforeTime(Date time) {
        articleRepository.deleteBySeeAndLastTimeBefore(true,time);
    }
}
