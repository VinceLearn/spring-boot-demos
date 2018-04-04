package xin.keepmoving.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.keepmoving.web.domain.User;
import xin.keepmoving.web.domain.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018/3/30
 */
@RestController
public class HelloWorldController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setUsername("小明2");
        user.setPassword("1234");
        user.setEmail("xiaoming@163.com");
        return user;
    }

    @RequestMapping(value = "/getUser1", method = RequestMethod.GET)
    @Cacheable(value = "user-key")
    public User getUser1(@RequestParam() String username) {
        User user = userRepository.findByUsername(username);
        System.out.println("没有打印当前内容说明调用缓存成功！！！");
        return user;
    }

    @RequestMapping("/save")
    public String save() {
        User user = new User();
        user.setUsername("小明_ " + System.currentTimeMillis());
        user.setPassword("1234");
        user.setEmail("xiaoming@163.com");
        User save = userRepository.save(user);

        String msg = "success";
        if (save.getId() == null) {
            msg = "fail";
        }
        return msg;
    }

    @RequestMapping("/uid")
    public String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }

        session.setAttribute("uid", uid);
        return session.getId();
    }
}
