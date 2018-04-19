package xin.keepmoving.test.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xin.keepmoving.SpringBootJpaApplication;
import xin.keepmoving.domain.LogInfo;
import xin.keepmoving.domain.LogInfoRepository;

import java.util.Date;
import java.util.List;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018-04-18 16:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootJpaApplication.class)
public class LogInfoRepositoryTests {
    @Autowired
    private LogInfoRepository logInfoRepository;

    @Test
    public void testSave() {
        // insert
        LogInfo logInfo = new LogInfo();
        logInfo.setLevel("error");
        logInfo.setInfo("严重错误");
        logInfo.setInsertDateTime(new Date());

        logInfoRepository.save(logInfo);

        long count = logInfoRepository.count();
//        Assert.assertEquals(1, count);
    }

    @Test
    public void testQuery() {
        List<LogInfo> logInfos = logInfoRepository.findAll();

        for (LogInfo logInfo: logInfos) {
            System.out.println(logInfo.toString());
        }
    }
}
