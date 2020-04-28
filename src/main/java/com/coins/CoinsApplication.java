package com.coins;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class})
@MapperScan("com.coins.rbac.mapper")
@MapperScan("com.coins.home.mapper")
public class CoinsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoinsApplication.class, args);
	}
}