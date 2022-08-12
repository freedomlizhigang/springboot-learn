package com.coins.ums.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coins.ums.entity.Log;
import com.coins.ums.mapper.LogMapper;
import com.coins.ums.request.LogRequest;
import com.coins.ums.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coins.utils.LocalDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {
    // 获取列表
    public Map<String, Object> getList(LogRequest logRequest) {
        QueryWrapper<Log> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(logRequest.adminId != null, "admin_id", logRequest.adminId);
        Long count = this.count(queryWrapper);
        queryWrapper.last("limit " + logRequest.offset + "," + logRequest.pageSize);
        List<Log> all = this.list(queryWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        map.put("list", all);
        return map;
    }
    public Boolean postClear(){
        Boolean result = true;
        QueryWrapper<Log> queryWrapper = new QueryWrapper<>();
        LocalDateTime day = LocalDateUtils.minus(LocalDateTime.now(),30, ChronoUnit.SECONDS);
        queryWrapper.lt("created_at", day);
        result = this.remove(queryWrapper);
        return result;
    }
}
