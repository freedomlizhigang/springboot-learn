package com.coins.rbac.service.lmpl;

import com.coins.rbac.entity.Logs;
import com.coins.rbac.mapper.LogsMapper;
import com.coins.rbac.service.ILogsService;
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
