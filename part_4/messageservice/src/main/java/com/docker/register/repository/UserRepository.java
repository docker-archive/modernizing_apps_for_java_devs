package com.docker.register.repository;


import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.docker.register.domain.User;

public class UserRepository {	
	public static final String KEY = "user";
	
	private final ListOperations<String, User> listOps;
	
	public UserRepository(RedisTemplate<String, User> redisTemplate) {
		this.listOps = redisTemplate.opsForList();
	}

	public void delete(User user) {
		listOps.remove(KEY, 1, user);
	}
	
	public <S extends User> S save(S user) {
		listOps.leftPush(KEY, user);
		return null;
	}
}
