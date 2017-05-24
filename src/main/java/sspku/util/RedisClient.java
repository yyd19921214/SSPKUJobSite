package sspku.util;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import sspku.dao.JobExample;
import sspku.dao.JobWithBLOBs;
import sspku.mapper.JobMapper;
@Component
public class RedisClient {
	private static final int expire_time=60; 

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 以某个值作为key来赋值
     * @param key
     * @param value
     */
    public synchronized void set(String key, String value) {
    	redisTemplate.opsForValue().set(key, value);
    	redisTemplate.expire(key, expire_time, TimeUnit.SECONDS);
    }

    /**
     * 获取某个key的值
     * @param key
     * @return
     */
    public synchronized String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    
    /**
     * 判断某个key值是否存在
     * @param key
     * @return
     */
    public synchronized  Boolean existsKey(String key){
    	return redisTemplate.hasKey(key);
    }
    
    public static void main(String[] args) {
    	ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring.xml",
				"classpath:spring-mybatis.xml", "classpath:spring-mvc.xml");
		RedisClient redis = (RedisClient) ac.getBean("redisClient");
		redis.set("hello", "world");
		System.out.println(redis.get("hello"));
	}
}
