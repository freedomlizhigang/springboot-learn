package com.coins;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//利用@EnableAsync注解开启异步任务支持
@EnableAsync(proxyTargetClass=true)
//事务
@EnableTransactionManagement
// 扫描mapper
@MapperScan("com.coins.ums.mapper")
@MapperScan("com.coins.cms.mapper")
@MapperScan("com.coins.home.mapper")
// 扫描过滤器
@ServletComponentScan
public class CoinsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoinsApplication.class, args);
	}
}