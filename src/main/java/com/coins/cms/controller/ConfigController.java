package com.coins.cms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coins.cms.entity.Config;
import com.coins.cms.service.ConfigService;
import com.coins.ums.controller.UmsApiRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

/**
 * (Config)表控制层
 *
 * @author makejava
 * @since 2022-08-02 08:57:45
 */
@UmsApiRestController("/config")
public class ConfigController {
    @Autowired
    private ConfigService configService;

    @GetMapping("/get")
    public Object getDetail() {
        Config detail = configService.getById(1);
        return detail;
    }

    @PostMapping("/update")
    public Object postDetail(@Validated(Config.updateConfig.class) Config config) {
        config.setUpdatedAt(LocalDateTime.now());
        Boolean result = configService.updateById(config);
        return result;
    }
}

