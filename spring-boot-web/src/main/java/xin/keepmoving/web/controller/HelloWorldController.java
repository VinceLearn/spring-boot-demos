package xin.keepmoving.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.keepmoving.web.domain.User;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018/3/30
 */
@RestController
public class HelloWorldController {
    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setUsername("小明2");
        user.setPassword("1234");
        return user;
    }
}
