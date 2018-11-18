package com.coins.service;

import com.coins.entity.Role;

public interface RoleService {
	Role saveOrUpdate(Role role);
	Role get(Integer id);
	void delete(Integer id);
}
