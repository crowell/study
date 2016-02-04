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

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by JinBingBing on 2016/1/29.
 */
@Service
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
        String relation_id = friend.getRelation_id();
        List<Friend> friendList = friendDao.queryFriendByRelation_id(relation_id);
        List<String> friend_idlist = FriendTools.getFriend_idList(friendList);
        Assert.isTrue(!friend_idlist.contains(user_id),"好友已存在，无需添加");
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
            Assert.notNull(friend, "删除失败，删除信息错误");
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(SerializingUtil.serialize(id));
        byte[] bytes1 = jedis.get(SerializingUtil.serialize(friend.getRelation_id()+"_list"));
        boolean result = friendDao.deleteFriendById(id)>0;
        if (bytes != null&&result) {
            jedis.del(SerializingUtil.serialize(id));
        }
        if(bytes1!=null&&result) {
            List<Friend> friendList = (List<Friend>) SerializingUtil.deserialize(bytes1);
            friendList.remove(friend);
            jedis.set(SerializingUtil.serialize(friend.getRelation_id()+"_list"),SerializingUtil.serialize(friendList));
        }

        return result;
    }
    /*
    * 清空好友列表
    * */
    public boolean deleteFriendByRelation_id(String relation_id){
        Assert.notNull(relation_id,"信息为空，删除失败");
        Relation relation = relationDao.getRelationById(relation_id);
        Assert.notNull(relation,"删除失败，该分组不存在");
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(SerializingUtil.serialize(relation.getId()+"_list"));
        boolean result = friendDao.deleteFriendBuRelation_id(relation_id)>0;
        if(bytes!=null&&result){
            jedis.del(SerializingUtil.serialize(relation.getId()+"_list"));
        }

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
        Assert.isTrue(UpdateFriend.judgeFriend(friend,friend1),"修改内容有误，修改失败");
        Relation relation1 = relationDao.getRelationById(friend.getRelation_id());
        Relation relation2 = relationDao.getRelationById(friend1.getRelation_id());
        boolean same = relation1.getUser_id().equals(relation2.getUser_id());
        Assert.isTrue(same,"修改内容有误，不能移至他人好友");
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(SerializingUtil.serialize(friend.getId()));
        boolean result = friendDao.updateFriend(friend)>0;
        if(bytes!=null&&result){
            jedis.del(SerializingUtil.serialize(friend.getId()));
            jedis.set(SerializingUtil.serialize(friend.getId()),SerializingUtil.serialize(friend));
        }

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
        List<Friend> friendList;
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(SerializingUtil.serialize(relation_id+"_list"));
        if (bytes==null) {
            friendList = friendDao.queryFriendByRelation_id(relation_id);
            Assert.notNull(friendList, "该分组没有好友");
            jedis.set(SerializingUtil.serialize(relation_id+"_list"),SerializingUtil.serialize(friendList));
        }else{
            friendList = (List<Friend>) SerializingUtil.deserialize(bytes);
        }
        return friendList;
    }
}
