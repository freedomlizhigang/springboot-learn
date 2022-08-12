package com.coins.cms.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coins.cms.entity.Config;
import com.coins.cms.mapper.ConfigMapper;
import com.coins.cms.service.ConfigService;
import org.springframework.stereotype.Service;

/**
 * (Config)表服务实现类
 *
 * @author makejava
 * @since 2022-08-02 08:58:53
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {
}

