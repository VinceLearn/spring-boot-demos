package xin.keepmoving.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018-04-18 16:45
 */
public interface LogInfoRepository extends MongoRepository<LogInfo, String> {
    
}
