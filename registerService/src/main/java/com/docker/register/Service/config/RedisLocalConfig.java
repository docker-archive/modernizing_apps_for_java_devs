package com.docker.register.Service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.docker.register.repository.UserRepository;
import com.docker.register.domain.User;

@Configuration
@Profile("default")
public class RedisLocalConfig {
	
	@Bean
	public RedisConnectionFactory redisConnection() {
		return new JedisConnectionFactory();
	}
}
