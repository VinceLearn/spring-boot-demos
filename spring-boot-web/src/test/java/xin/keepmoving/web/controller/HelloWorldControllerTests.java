package xin.keepmoving.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import xin.keepmoving.web.SpringBootWebApplication;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018/4/4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
public class HelloWorldControllerTests {
    MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        // 设置上下文
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void cacheTest() throws Exception {
        String ret = mvc.perform(MockMvcRequestBuilders.get("/getUser1").accept(MediaType.APPLICATION_JSON)
                .param("username", "中文"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(ret);
    }

    @Test
    public void saveTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/save").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));
    }
}
