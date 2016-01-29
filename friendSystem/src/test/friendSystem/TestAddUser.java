package friendSystem;

import com.mycompany.friendSystem.dao.UserDao;
import com.mycompany.friendSystem.model.User;
import com.mycompany.friendSystem.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by JinBingBing on 2016/1/27.
 */
public class TestAddUser extends BaseJunit4Test{

    @Resource
    UserService userService;

    @Test   //标明是测试方法
    @Transactional   //标明此方法需使用事务
    @Rollback(false) //标明使用完此方法后事务不回滚,true时为回滚
    public void addTest(){
        User user = new User();
        user.setUsername("a123456");
        user.setPassword("a123456");
        user.setName("呵呵");
        user.setSex("男");
        user.setAge(15);

    }

}
