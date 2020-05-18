package cn.coolwind.novel.repository;

import cn.coolwind.novel.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer>, JpaSpecificationExecutor<BookEntity> {
    List<BookEntity> getAllByAntoLoad(Integer load);
}
