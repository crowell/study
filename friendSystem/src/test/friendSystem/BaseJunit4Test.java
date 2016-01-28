package friendSystem;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by JinBingBing on 2016/1/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration
        ({"/*-all.xml","/*-redis.xml","/*-mybatis.xml","/*mvc.xml"}) //加载配置文件
public class BaseJunit4Test {
}
