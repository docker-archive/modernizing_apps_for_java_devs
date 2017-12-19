package com.docker.register.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;

import com.docker.register.domain.User;

public class UserRepository implements CrudRepository<User, String> {
	
	public static final String KEY = "users";
	
	private final HashOperations<String, String, User> hashOps;
	
	public UserRepository(RedisTemplate<String, User> redisTemplate) {
		this.hashOps = redisTemplate.opsForHash();
	}
	
	@Override
	public void delete(User user) {
		hashOps.delete(KEY, user.getId().toString());
	}
	
	@Override
	public <S extends User> S save(S user) {
		hashOps.put(KEY, user.getId().toString(), user);
		return user;
	}
	
	@Override
	public User findOne(String username) {
		return hashOps.get(KEY, username);
	}

	@Override
	public long count() {
		return hashOps.keys(KEY).size();
	}

	@Override
	public void delete(String id) {
		hashOps.delete(KEY, id.toString());
		
	}

	@Override
	public void delete(Iterable<? extends User> users) {
		for (User u : users) {
			delete(u);
		}
	}

	@Override
	public void deleteAll() {
		Set<String> ids = hashOps.keys(KEY);
		for (String id : ids) {
			delete(id);
		}
		
	}

	@Override
	public boolean exists(String id) {
		return hashOps.hasKey(KEY, id);
	}

	@Override
	public Iterable<User> findAll() {
		return hashOps.values(KEY);
	}

	@Override
	public Iterable<User> findAll(Iterable<String> ids) {
		return hashOps.multiGet(KEY, convertIterableToList(ids));
	}
	
	@Override
	public <S extends User> Iterable<S> save(Iterable<S> users) {
		List<S> result = new ArrayList<S>();

		for(S entity : users) {
			save(entity);
			result.add(entity);
		}

		return result;
	}
	
	private <T> List<T> convertIterableToList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        for (T object : iterable) {
            list.add(object);
        }
        return list;
    }
}
