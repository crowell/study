package com.mycompany.friendSystem.service.impl;

import com.mycompany.friendSystem.commons.SerializingUtil;
import com.mycompany.friendSystem.commons.UUIDUtil;
import com.mycompany.friendSystem.dao.FriendDao;
import com.mycompany.friendSystem.dao.RelationDao;
import com.mycompany.friendSystem.dao.UserDao;
import com.mycompany.friendSystem.model.Friend;
import com.mycompany.friendSystem.model.Relation;
import com.mycompany.friendSystem.model.User;
import com.mycompany.friendSystem.service.FriendService;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by JinBingBing on 2016/1/29.
 */
public class FriendServiceImpl implements FriendService{

    @Resource
    FriendDao friendDao;
    @Resource
    UserDao userDao;
    @Resource
    RelationDao relationDao;

    @Resource
    JedisPool jedisPool;

    /*
    * 添加好友
    * */
    public boolean insertFriend(Friend friend){
        Assert.notNull(friend.getRelation_id(),"列表信息为空，无法添加好友");
        Assert.notNull(friend.getFriend_id(),"好友信息为空，添加失败");
        String user_id = friend.getFriend_id();
        User user = userDao.getUserById(user_id);
        Assert.notNull(user,"添加好友失败，该用户不存在");
        friend.setId(UUIDUtil.getUUID());
        boolean result = friendDao.insertFriend(friend)>0;

        return result;
    }
    /*
    * 删除好友
    * */
    public boolean deleteFriendById(String id){
        Assert.notNull(id,"信息为空，删除失败");
        Friend friend = friendDao.getFriendById(id);
        Assert.notNull(friend,"删除失败，删除信息错误");
        boolean result = friendDao.deleteFriendById(id)>0;

        return result;
    }
    /*
    * 清空好友列表
    * */
    public boolean deleteFriendByRelation_id(String relation_id){
        Assert.notNull(relation_id,"信息为空，删除失败");
        Relation relation = relationDao.getRelationById(relation_id);
        Assert.notNull(relation,"删除失败，该分组不存在");
        boolean result = friendDao.deleteFriendBuRelation_id(relation_id)>0;
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(SerializingUtil.serialize());

        return result;
    }
    /*
    * 修改好友所在列表
    * */
    public boolean updateFriend(Friend friend){
        Assert.notNull(friend,"信息为空，更新失败");
        String id = friend.getId();
        Assert.notNull(id,"获取好友所在列表失败");
        Friend friend1 = friendDao.getFriendById(id);
        Assert.notNull(friend1,"该好友不存在");
        Relation relation = relationDao.getRelationById(friend.getRelation_id());
        Assert.notNull(relation,"修改好友分组失败，分组不存在");
        Assert.isTrue(FriendSelect.judgeFriend(friend,friend1),"修改内容有误，修改失败");
        boolean result = friendDao.updateFriend(friend)>0;

        return result;
    }
    /*
    * 查询好友
    * */
    public Friend getFriendById(String id){
        Assert.notNull(id,"查询信息不能为空");
        Friend friend;
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(SerializingUtil.serialize(id));
        if(bytes==null) {
            friend = friendDao.getFriendById(id);
            Assert.notNull(friend, "好友不存在");
            jedis.set(SerializingUtil.serialize(id),SerializingUtil.serialize(friend));
        }else {
            friend = (Friend)SerializingUtil.deserialize(bytes);
        }
        return friend;
    }
    /*
    * 显示列表所有好友
    * */
    public List<Friend> queryFriendByRelation_id(String relation_id){
        Assert.notNull(relation_id,"获取分组信息失败");
        Relation relation = relationDao.getRelationById(relation_id);
        Assert.notNull(relation,"该分组不存在");
        List<Friend> friendList = friendDao.queryFriendByRelation_id(relation_id);
        Assert.notNull(friendList,"该分组没有好友");

        return friendList;
    }
}
