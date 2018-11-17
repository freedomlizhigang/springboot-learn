package com.springboot.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springboot.entity.Role;
import com.springboot.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	private static final Map<Integer, Role> DATABASES = new HashMap<>();
	
	static {
        DATABASES.put(4, new Role(4,"s4",1));
        DATABASES.put(5, new Role(5,"s5",1));
        DATABASES.put(6, new Role(6,"s6",1));
    }
	
	private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@CachePut(value = "role", key = "#role.id")
	@Override
	public Role saveOrUpdate(Role role) {
		DATABASES.put(role.getId(), role);
        log.info("进入 saveOrUpdate 方法");
        return role;
	}
	@Cacheable(value="role",key="#id")
	@Override
	public Role get(Integer id) {
		log.info("进入 get 方法");
        return DATABASES.get(id);
	}
	@CacheEvict(value = "role", key = "#id")
	@Override
	public void delete(Integer id) {
		DATABASES.remove(id);
        log.info("进入 delete 方法");

	}

}
