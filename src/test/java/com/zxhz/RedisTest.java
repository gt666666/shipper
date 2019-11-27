package com.zxhz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 类描述：
 * 创建作者：gt
 * 创建日期 ： 2019/11/20
 */
@SpringBootTest(classes = ShipperApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {
    @Autowired
    private RedisTemplate<String ,String >  redisTemplate;
    @Test
    public void setRedis(){
        this.redisTemplate.opsForValue().set("gt","cns");
        System.out.println(this.redisTemplate.opsForValue().get("gt"));
    }
}
