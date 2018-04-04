package xin.keepmoving.web.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xin.keepmoving.web.SpringBootWebApplication;
import xin.keepmoving.web.domain.User;

import java.util.concurrent.TimeUnit;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018/3/31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
public class RedisTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        stringRedisTemplate.opsForValue().set("bbb111", "222");
        Assert.assertEquals("222", stringRedisTemplate.opsForValue().get("bbb"));
    }

    @Test
    public void testObj() throws InterruptedException {
        User user = new User("alijj", "123456", "alijj@163.com");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("xin.keepmoving", user);
        operations.set("xin.keepmoving.f", user, 1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        Boolean exists = redisTemplate.hasKey("xin.keepmoving.f");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
        Assert.assertEquals("alijj", operations.get("xin.keepmoving").getUsername());
    }
}
