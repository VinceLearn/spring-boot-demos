package xin.keepmoving.test.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xin.keepmoving.SpringBootJpaApplication;
import xin.keepmoving.domain.User;
import xin.keepmoving.domain.UserRepository;

import java.util.*;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018-04-18 10:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootJpaApplication.class)
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;
    @Test
    public void testBaseQuery() {
        User user = null;
        List<User> saveUsers = new ArrayList<>();
        Random r = new Random();
        for (int i=0; i < 10; i++) {
            user = new User();
            user.setUsername("username_" + i + "_" + r.nextInt(100) * r.nextInt(100));
            user.setPassword(UUID.randomUUID().toString());
            user.setAge(r.nextInt(20) + 10);
            user.setCreateDateTime(new Date());
            user.setEmail(user.getUsername() + "@163.com");
            user.setIsAdmin(r.nextBoolean());
            saveUsers.add(user);
        }

        // save list
        userRepository.saveAll(saveUsers);

        // save one
        User aUser = new User();
        aUser.setUsername("中文_小米_" + r.nextInt(10) * r.nextInt(100));
        aUser.setPassword(UUID.randomUUID().toString());
        aUser.setAge(r.nextInt(20) + 10);
        aUser.setCreateDateTime(new Date());
        aUser.setEmail(user.getUsername() + "@163.com");
        aUser.setIsAdmin(r.nextBoolean());
        // 保存后，返回一个新对象，会把新id重新返回回来
        User nUser = userRepository.save(aUser);

        System.out.println("New User Id:" + nUser.getId());

        List<User> users = userRepository.findAll();

        // delete one
        userRepository.delete(nUser);

        long count = userRepository.count();
        System.out.println("Count:" + count);

        boolean b = userRepository.existsById(nUser.getId());
        System.out.println("exist:" + b);
    }

    @Test
    public void testCustomQuery() {
        List<User> users1 = userRepository.findByAgeBetween(20, 25);
        System.out.println("CountAge:" + users1.size());

        List<User> users2 = userRepository.findByIsAdminFalse();
        System.out.println("CountIsAdmin:" + users2.size());

        List<User> users3 = userRepository.findByUsernameOrEmail("中文_小米", "username_9_1892@163.com");
        System.out.println("CountUsernameOrEmail:" + users3.size());
    }

    @Test
    public void testPageable() {
        int page = 0, size = 2;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        List<User> users = userRepository.findByUsernameLike("小明", pageRequest);
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void testLimitQuery() {
        User user = userRepository.findTopByOrderByCreateDateTimeDesc();
        System.out.println(user.toString());

        Sort sort = new Sort(Sort.Direction.DESC, "createDateTime");
        List<User> users = userRepository.findFirst10ByEmailLike("username%", sort);

        System.out.println("10 == " + users.size());
    }

    @Test
    public void testCustomSqlQuery() {
        int modifyCount = userRepository.modifyById("zzz@163.com", 100L);
        Assert.assertEquals(1, modifyCount);

        User user = userRepository.findByEmail("zzz@163.com");
        Assert.assertEquals(100L, user.getId().longValue());
    }
}
