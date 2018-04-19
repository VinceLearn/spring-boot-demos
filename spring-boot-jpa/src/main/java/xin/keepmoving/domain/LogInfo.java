package xin.keepmoving.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018-04-18 16:43
 */
@Document
public class LogInfo {
    @Id
    private String id;
    @Column(nullable = false)
    private String level;
    @Column
    private String info;
    @Column
    private Date insertDateTime;

    public LogInfo() {

    }

    public LogInfo(String level, String info, Date insertDateTime) {
        this.level = level;
        this.info = info;
        this.insertDateTime = insertDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getInsertDateTime() {
        return insertDateTime;
    }

    public void setInsertDateTime(Date insertDateTime) {
        this.insertDateTime = insertDateTime;
    }

    @Override
    public String toString() {
        return "LogInfo{" +
                "id='" + id + '\'' +
                ", level='" + level + '\'' +
                ", info='" + info + '\'' +
                ", insertDateTime=" + insertDateTime +
                '}';
    }
}
