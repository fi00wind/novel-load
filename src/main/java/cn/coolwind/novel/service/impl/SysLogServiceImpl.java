package cn.coolwind.novel.service.impl;

import cn.coolwind.novel.entity.SysAccessLog;
import cn.coolwind.novel.repository.SysAccessLogRepository;
import cn.coolwind.novel.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysAccessLogRepository sysAccessLogRepository;

    @Override
    public void saveAccessLog(SysAccessLog sysAccessLog) {
        sysAccessLogRepository.save(sysAccessLog);
    }
}
