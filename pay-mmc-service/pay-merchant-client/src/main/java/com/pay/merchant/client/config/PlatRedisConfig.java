package com.pay.merchant.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class PlatRedisConfig {

    @Value("${plat.redis.hostName}")
    private String masterHost;
    @Value("${plat.redis.port}")
    private int masterPort;

    private RedisConnectionFactory generateReleaseConnectionFactory() {
        RedisNode master = new RedisNode(masterHost, masterPort);
        JedisPoolConfig poolConfig = generatePoolConfig();
        JedisConnectionFactory factory = new JedisConnectionFactory(poolConfig);
        factory.setHostName(masterHost);
        factory.setPort(masterPort);
        factory.setTimeout(10000);
        factory.setUsePool(true);
        factory.setConvertPipelineAndTxResults(true);
        factory.afterPropertiesSet();
        return factory;
    }

    private JedisPoolConfig generatePoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(20);
        poolConfig.setMaxTotal(300);
        poolConfig.setMaxWaitMillis(5000);
        poolConfig.setTestOnBorrow(true);
        return poolConfig;
    }

    @Bean(name = "redisConnectionFactory")
    RedisConnectionFactory factory() {
        return generateReleaseConnectionFactory();
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory factory) {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();

        template.setEnableTransactionSupport(false);
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jdkSerializationRedisSerializer);
        template.setDefaultSerializer(jdkSerializationRedisSerializer);
        template.setConnectionFactory(factory);
        return template;
    }

    @Bean(name = "stringRedisTemplate")
    @Primary
    public RedisTemplate<String, String> stringRedisTemplate(
            RedisConnectionFactory factory) {
        final RedisTemplate<String, String> template = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setEnableTransactionSupport(true);
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.setDefaultSerializer(stringRedisSerializer);
        template.setConnectionFactory(factory);
        return template;
    }
}
