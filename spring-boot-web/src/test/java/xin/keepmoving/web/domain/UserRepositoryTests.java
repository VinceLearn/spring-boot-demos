package xin.keepmoving.web.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xin.keepmoving.web.SpringBootWebApplication;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018/3/31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa", "123456", "aa@163.com"));
        userRepository.save(new User("bb", "123456", "bb@163.com"));
        userRepository.save(new User("cc", "123456", "cc@163.com"));

        Assert.assertEquals(3, userRepository.findAll().size());
        userRepository.delete(userRepository.findByUsername("aa"));
    }

    @Test
    public void saveChineseTest() {
        userRepository.save(new User("中文", "123456", "aa@163.com"));
        Assert.assertNotNull(userRepository.findByUsername("中文"));
    }

    @Test
    public void findByUsernameTest() {
        User user = userRepository.findByUsername("bb");
        Assert.assertNotNull(user);
    }
}
