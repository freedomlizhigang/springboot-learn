package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RestController;

import tk.mybatis.spring.annotation.MapperScan;

@RestController
@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class})
@EnableCaching
@MapperScan("com.springboot.mapper")
public class SpringbootWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebApplication.class, args);
	}
}
