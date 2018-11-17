package com.springboot.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.Role;

@RestController
@RequestMapping("/roles")
public class RoleController {
	private final JdbcTemplate jdbcTemplate;
	@Autowired
	public RoleController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@GetMapping
	public List<Role> queryRoles(){
		String sql = "select * from roles";
		return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(Role.class));
	}
}
