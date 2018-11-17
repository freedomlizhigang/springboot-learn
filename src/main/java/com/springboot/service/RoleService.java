package com.springboot.service;

import com.springboot.entity.Role;

public interface RoleService {
	Role saveOrUpdate(Role role);
	Role get(Integer id);
	void delete(Integer id);
}
