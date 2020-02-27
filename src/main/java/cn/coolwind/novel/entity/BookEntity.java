package cn.coolwind.novel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "book", schema = "novel")
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class BookEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = true, length = 50)
    private String name;

    @Column(name = "nick", nullable = true, length = 20)
    private String nick;

    @Column(name = "source")
    private String source;

    @Column(name = "url")
    private String url;

    @Column(name = "last_title")
    private String lastTitle;

}
