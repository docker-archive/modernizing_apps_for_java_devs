package com.docker.register.Service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.docker.register.repository.UserRepository;
import com.docker.register.domain.User;

@Configuration
@Profile("default")
@PropertySource("application.properties")
public class RedisLocalConfig {
	
	@Value("${redis.hostname}")
	private String redisHostName;

	@Value("${redis.port}")
	private int redisPort;

	@Bean
	public RedisConnectionFactory redisConnection() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(redisHostName);
		factory.setPort(redisPort);
		factory.setUsePool(true);
		return factory;
	}
}
