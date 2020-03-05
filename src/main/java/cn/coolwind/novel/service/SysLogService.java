package cn.coolwind.novel.service;

import cn.coolwind.novel.entity.SysAccessLog;

public interface SysLogService {
    void saveAccessLog(SysAccessLog sysAccessLog);
}
