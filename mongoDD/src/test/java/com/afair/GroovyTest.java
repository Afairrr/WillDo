package com.afair;

import com.afair.common.utils.GroovyUtils;
import com.afair.common.utils.IPUtils;
import com.afair.pojo.entity.People;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WangBing
 * @date 2021/1/7 10:53
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroovyTest {
    @Test
    public void loadScript() {
        String script = "data.age>10";
        String fullScript = GroovyUtils.packageGroovyScript(script);
        People people = People.builder().age(11).build();
        Object o = GroovyUtils.invokeMethod(fullScript, people);
        System.out.println(o);
    }

    @Test
    public void dymical() {
        System.out.println(new IPUtils().ipLocation("114.243.33.225"));
    }
}
