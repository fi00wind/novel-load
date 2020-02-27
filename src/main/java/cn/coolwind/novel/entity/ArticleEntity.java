package cn.coolwind.novel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "article", schema = "novel", catalog = "")
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class ArticleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    @Column(name = "see")
    private Boolean see;

    @Column(name = "last_time")
    private Date lastTime;

}
