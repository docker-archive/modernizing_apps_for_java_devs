package com.docker.register.Service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.docker.register.domain.User;
import com.docker.register.repository.UserRepository;

@Configuration
public class RedisConfig {

	@Bean
	public UserRepository repository(RedisTemplate<String, User> redisTemplate) {
		return new UserRepository(redisTemplate);
	}

	@Bean
	public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, User> template = new RedisTemplate();

		template.setConnectionFactory(redisConnectionFactory);

		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		RedisSerializer<User> personSerializer = new Jackson2JsonRedisSerializer<>(User.class);

		template.setKeySerializer(stringSerializer);
		template.setValueSerializer(personSerializer);
		template.setHashKeySerializer(stringSerializer);
		template.setHashValueSerializer(personSerializer);

		return template;
	}
}