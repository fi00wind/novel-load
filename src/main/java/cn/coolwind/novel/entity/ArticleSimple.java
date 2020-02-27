package cn.coolwind.novel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class ArticleSimple {

    private Integer id;

    private Integer bookId;

    private String title;

    private Boolean see;

    private Date lastTime;

}
