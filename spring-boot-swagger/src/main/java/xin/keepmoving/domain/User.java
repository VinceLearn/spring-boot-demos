package xin.keepmoving.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018-04-08 9:43
 */
public class User implements Serializable {
    // ID
    private Long id;
    // 用户名
    private String username;
    // 用户密码
    private String password;
    // 年龄
    private int age;
    // 创建时间
    private Date createTime;

    public User() {
        super();
    }

    public User(String username, String password, int age, Date createTime) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
