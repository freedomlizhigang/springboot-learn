package com.coins.service.lmpl.rbac;

import com.coins.entity.rbac.Logs;
import com.coins.mapper.rbac.LogsMapper;
import com.coins.service.rbac.ILogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@Service
public class LogsServiceImpl extends ServiceImpl<LogsMapper, Logs> implements ILogsService {

}
