package com.coins.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
//在这里设置Session过期时间，单位：秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 7200)
public class RedisSessionConfig {

}
