package cn.coolwind.novel.repository;

import cn.coolwind.novel.entity.SysAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysAccessLogRepository extends JpaRepository<SysAccessLog,Integer>, JpaSpecificationExecutor<SysAccessLog> {
}
