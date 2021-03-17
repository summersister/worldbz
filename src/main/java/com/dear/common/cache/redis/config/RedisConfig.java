//package com.dear.common.cache.redis.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Slf4j
//@Configuration
//public class RedisConfig extends CachingConfigurerSupport {
//
//    @Value("${redis.host}")
//    private String host;
//
//    @Value("${redis.port}")
//    private Integer port;
//
//    @Value("${redis.pwd}")
//    private String pwd;
//
//    @Value("${redis.maxIdle}")
//    private Integer maxIdle;
//
//    @Value("${redis.minIdle}")
//    private Integer minIdle;
//
//    @Value("${redis.maxTotal}")
//    private Integer maxTotal;
//
//    @Value("${redis.maxWaitMillis}")
//    private Long maxWaitMillis;
//
//    @Value("${redis.testOnBorrow}")
//    private Boolean testOnBorrow;
//
//    @Value("${redis.testOnReturn}")
//    private Boolean testOnReturn;
//
//    @Value("${redis.timeOut}")
//    private int timeOut;
//
//    @Bean
//    public JedisPool redisPoolFactory() {
//
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//
//        jedisPoolConfig.setMaxIdle(maxIdle);
//
//        jedisPoolConfig.setMinIdle(minIdle);
//
//        jedisPoolConfig.setMaxTotal(maxTotal);
//
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//
//        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
//
//        jedisPoolConfig.setTestOnReturn(testOnReturn);
//
//        JedisPool jedisPool;
//
//        if(StringUtils.isBlank(pwd)){
//
//            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeOut);
//
//        } else {
//
//            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeOut, pwd);
//        }
//
//        log.info("JedisPool注册成功！！！" + "redis地址：" + host + ":" + port);
//
//        return jedisPool;
//    }
//}
