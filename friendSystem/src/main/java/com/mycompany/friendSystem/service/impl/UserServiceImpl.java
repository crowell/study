package com.mycompany.friendSystem.service.impl;

import com.mycompany.friendSystem.commons.FriendListName;
import com.mycompany.friendSystem.commons.SerializingUtil;
import com.mycompany.friendSystem.commons.UUIDUtil;
import com.mycompany.friendSystem.dao.UserDao;
import com.mycompany.friendSystem.model.Relation;
import com.mycompany.friendSystem.model.User;
import com.mycompany.friendSystem.service.RelationService;
import com.mycompany.friendSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JinBingBing on 2016/1/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Autowired
    RelationService relationService;

    @Resource
    JedisPool jedisPool;
    /*
    * 创建新用户
    * */
    public boolean addUser(User user){
        Assert.notNull(user.getUsername(),"用户名不能为空");
        Assert.notNull(user.getPassword(),"密码不能为空");
        Assert.isNull(userDao.getUserByUsername(user.getUsername()),"用户名已存在");
        user.setId(UUIDUtil.getUUID());
        if(user.getName()==null)
            user.setName(user.getUsername());
        boolean result = userDao.insertUser(user)>0;
        if(result){
        	Relation r1 = new Relation();
        	Relation r2 = new Relation();
        	Relation r3 = new Relation();
        	DefaultRelation.getDefaultRelationShip(FriendListName.getName(1), user, r1);
        	DefaultRelation.getDefaultRelationShip(FriendListName.getName(2), user, r2);
        	DefaultRelation.getDefaultRelationShip(FriendListName.getName(3), user, r3);
            relationService.insertRelation(r1);
            relationService.insertRelation(r2);
            relationService.insertRelation(r3);
        }
        return result;
    }
    /*
    * 修改用户信息
    * */
    public boolean updateUser(User user) {
        Assert.notNull(user, "没有修改信息");
        Assert.isTrue(userDao.getUserById(user.getId()) != null&&userDao.getUserByUsername(user.getUsername()) != null, "该用户不存在");
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(SerializingUtil.serialize(user.getId()));
        byte[] bytes1 = jedis.get(SerializingUtil.serialize(user.getUsername()));
        boolean result = userDao.updateUser(user) > 0;
        if (bytes!=null&&result)
            jedis.del(SerializingUtil.serialize(user.getId()));
        if(bytes1!=null&&result)
            jedis.del(SerializingUtil.serialize(user.getUsername()));

        return result;
    }
    /*
    * 删除用户
    * */
    public boolean deleteUser(String id) {
        Assert.notNull(id, "id不能为空");
        User user = userDao.getUserById(id);
        Assert.notNull(user, "该用户不存在");
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(SerializingUtil.serialize(id));
        byte[] bytes1 = jedis.get(SerializingUtil.serialize(user.getUsername()));
        boolean result = userDao.deleteUserById(id)>0;
        if (bytes != null&&result) {
            jedis.del(SerializingUtil.serialize(id));
        }
        if(bytes1!=null&&result) {
            jedis.del(SerializingUtil.serialize(user.getUsername()));
        }
        if(result){
            result = relationService.deleteRelationByUser_id(id);
        }

        return result;
    }
    /*
    * 通过id获取用户信息
    * */
    public User getUser(String id){
        Assert.notNull(id,"id不能为空");
        User user;
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(SerializingUtil.serialize(id));
        if(bytes==null) {
            user = userDao.getUserById(id);
            Assert.notNull(user,"用户不存在");
        }else {
            user = (User)SerializingUtil.deserialize(bytes);
        }

        return user;
    }
    /*
    * 通过username获取用户信息
    * */
    public User getUserByUsername(String username){
        Assert.notNull(username, "用户名不能为空");
        User user;
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(SerializingUtil.serialize(username));
        if(bytes==null) {
            user = userDao.getUserById(username);
            Assert.notNull(user,"用户不存在");
            jedis.set(SerializingUtil.serialize(username),SerializingUtil.serialize(user));
        }else {
            user = (User)SerializingUtil.deserialize(bytes);
        }

        return user;
    }
    /*
    * 通过部分信息查找用户
    * */
    public List<User> queryUserList(User user){
        Assert.notNull(user,"查询内容不能为空");
        List<User> list = userDao.selectUserList(user);
        Assert.notNull(list,"没有符合条件的用户");

        return  list;
    }
    /*
    * 通过部分信息的部分内容查找对应用户群
    * */
    public List<User> queryUserByCondition(String queryCondition){
        Assert.notNull(queryCondition,"查询内容不能为空");
        List<User> list = userDao.selectUserByCondition(queryCondition);
        Assert.notNull(list,"没有符合条件的用户");
        return list;
    }
}
