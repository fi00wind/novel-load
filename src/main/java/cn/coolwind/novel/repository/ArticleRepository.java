package cn.coolwind.novel.repository;

import cn.coolwind.novel.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity,Integer>, JpaSpecificationExecutor<ArticleEntity> {

    List<ArticleEntity> getAllByBookIdOrderByLastTimeDesc(int id);
}
