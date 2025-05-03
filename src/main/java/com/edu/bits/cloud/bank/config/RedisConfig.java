package com.edu.bits.cloud.bank.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

//@Configuration
public class RedisConfig {

	private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

	@Value("${spring.data.redis.host}")
	private String redisHost;

	@Value("${spring.data.redis.port}")
	private int redisPort;

	/*
	 * @Value("${spring.data.redis.password:}") private String redisPassword;
	 */

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		logger.info("Connecting to Redis at {}:{}", redisHost, redisPort);
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);

		/*
		 * if (!redisPassword.isEmpty()) { config.setPassword(redisPassword); }
		 */
		LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().useSsl().build();
		LettuceConnectionFactory factory = new LettuceConnectionFactory(config, clientConfig);

		factory.afterPropertiesSet();
		try {
			factory.getConnection().ping();
			logger.info("Connected to Redis successfully!");
		} catch (Exception e) {
			logger.error("Unable to connect to Redis", e);
		}
		return factory;
	}
}
