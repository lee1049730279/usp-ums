package com.usp.ums.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.usp.framework.cache.springcache.MyRedisCacheManager;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration("myRedisConfig")
@EnableCaching
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.port}")
	private Integer port = 6379;

	@Value("${spring.redis.database}")
	private Integer database = 0;

	@Bean
	public RedisTemplate<String, String> stringRedisTemplate(LettuceConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		return template;
	}

	@Bean
	public RedisTemplate<String, String> jsonRedisTemplate(LettuceConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

	@Bean
	public RedisTemplate<String, Object> zipStringRedisTemplate(LettuceConnectionFactory factory) {
		final RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new JdkSerializationRedisSerializer());
		template.setHashValueSerializer(new JdkSerializationRedisSerializer());
		return template;
	}

	@Bean
	public RedisTemplate<String, Object> objectRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
		final RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}

//	@Bean
//	public RScheduledExecutorService rsExecutorService(RedissonClient redissonClient) {
//		RScheduledExecutorService rsExecutorService = redissonClient.getExecutorService("rs-executor-service");
//		return rsExecutorService;
//	}

	// 缓存管理器
	@Bean
	@Primary
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		MyRedisCacheManager cacheManager = MyRedisCacheManager.create(factory);
		return cacheManager;
	}

	private void setSerializer(StringRedisTemplate template) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
	}
}
