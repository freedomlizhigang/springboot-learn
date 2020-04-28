package com.coins;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class})
// restapi
@RestController
//利用@EnableAsync注解开启异步任务支持
@EnableAsync(proxyTargetClass=true)
//事务
@EnableTransactionManagement
// 扫描mapper
@MapperScan("com.coins.rbac.mapper")
@MapperScan("com.coins.home.mapper")
public class CoinsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoinsApplication.class, args);
	}
}