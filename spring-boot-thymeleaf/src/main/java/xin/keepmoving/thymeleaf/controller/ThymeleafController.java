package xin.keepmoving.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import xin.keepmoving.thymeleaf.domain.User;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018/4/5
 */
@Controller
public class ThymeleafController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        // 变量表达式
        session.setAttribute("user", getUser());

        // 传递集合
        List<String> vals = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            vals.add("val_" + i);
        }
        model.addAttribute("vals", vals);

        model.addAttribute("user", getUser());

        Random r = new Random();

        model.addAttribute("id", r.nextInt(10));
        return "helloThymeleaf";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(@RequestParam Long id, Model model) {
        model.addAttribute("id", id);
        return "detail.html";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail1(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "detail.html";
    }

    private User getUser() {
        User user = new User();
        Random r = new Random();
        user.setId(Long.valueOf(r.nextInt(10000)));
        user.setUsername("User_" + System.currentTimeMillis() / 10000000);
        user.setPassword("Pwd_" + System.currentTimeMillis() / 10000000);

        user.setAdmin(r.nextBoolean());
        if (r.nextBoolean()) {
            user.setType("Type_" + System.currentTimeMillis() / 10000000);
        }
        return user;
    }
}
