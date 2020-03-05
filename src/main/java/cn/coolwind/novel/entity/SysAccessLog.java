package cn.coolwind.novel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sys_access_log")
@Data
public class SysAccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ip")
    private String ip;

    @Column(name = "url")
    private String url;

    @Column(name = "last_time")
    private Date lastTime;
}
