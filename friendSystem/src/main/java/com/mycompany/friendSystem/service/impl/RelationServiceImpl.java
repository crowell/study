package com.mycompany.friendSystem.service.impl;

import com.mycompany.friendSystem.commons.SerializingUtil;
import com.mycompany.friendSystem.commons.UUIDUtil;
import com.mycompany.friendSystem.dao.FriendDao;
import com.mycompany.friendSystem.dao.RelationDao;
import com.mycompany.friendSystem.dao.UserDao;
import com.mycompany.friendSystem.model.Friend;
import com.mycompany.friendSystem.model.Relation;
import com.mycompany.friendSystem.model.User;
import com.mycompany.friendSystem.service.RelationService;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by JinBingBing on 2016/1/28.
 */
@Service
public class RelationServiceImpl implements RelationService{

    @Resource
    RelationDao relationDao;

    @Resource
    UserDao userDao;

    @Resource
    FriendDao friendDao;

    @Resource
    JedisPool jedisPool;
    /*
    * 新建好友列表
    * */
    public boolean insertRelation(Relation relation){
        String user_id = relation.getId();
        Assert.notNull(user_id,"创建列表失败");
        String relationship =  relation.getRelationship();
        List<Relation> list = relationDao.queryRelationByUser_id(user_id);
        List<String> relationshipList = new ArrayList<String>();
        UserFriendList.getRelationship(list,relationshipList);
        Assert.isNull(relationshipList.contains(relationship),"好友列表已存在");
        relation.setId(UUIDUtil.getUUID());
        boolean result = relationDao.insertRelation(relation)>0;

        return result;
    }
    /*
    * 删除好友列表
    * */
    public boolean deleteRelationById(String id){
        Assert.notNull(id,"列表id不能为空");
        Relation relation = relationDao.getRelationById(id);
        Assert.notNull(relation,"列表不存在");
        List<Friend> friendList = friendDao.queryFriendByRelation_id(id);
        Assert.isNull(friendList,"分组不为空，无法删除");
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(SerializingUtil.serialize(id));
        byte[] bytes1 = jedis.get(SerializingUtil.serialize(relation.getUser_id()+"_list"));
        boolean result = relationDao.deleteRelationById(id)>0;
        if (bytes!=null&&result){
            jedis.del(SerializingUtil.serialize(id));
        }
        if(bytes1!=null&&result){
            List<Relation> relationList = (List<Relation>) SerializingUtil.deserialize(bytes1);

        }

        return result;
    }
    /*
    * 清空用户好友列表
    * */
    public boolean deleteRelationByUser_id(String user_id){
        Assert.notNull(user_id,"用户不能为空");
        User user = userDao.getUserById(user_id);
        Assert.notNull(user,"用户不存在");
        boolean result = relationDao.deleteRelationBuUser_id(user_id)>0;
        List<Relation> relationList = queryRelationList(user_id);
        for (int i = 0;i<relationList.size();i++){
            friendDao.deleteFriendBuRelation_id(relationList.get(i).getId());
        }
        return result;
    }
    /*
    * 修改好友列表
    * */
    public boolean updateRelation(Relation relation){
        Assert.notNull(relation,"更新好友列表失败");
        String id = relation.getId();
        Assert.notNull(id,"找不到好友列表");
        Relation relation1 = relationDao.getRelationById(id);
        Assert.notNull(relation1,"好友列表不存在");
        boolean result = relationDao.updateRelation(relation)>0;

        return  result;
    }
    /*
    * 获取好友列表
    * */
    public Relation getRelation(String id){
        Assert.notNull(id,"查询id不能为空");
        Relation relation = relationDao.getRelationById(id);
        Assert.notNull(relation,"没有这张列表");

        return  relation;
    }
    /*
    * 获取用户所有好友列表
    * */
    public List<Relation> queryRelationList(String user_id){
        Assert.notNull(user_id,"找不到用户");
        List<Relation> list = relationDao.queryRelationByUser_id(user_id);
        Assert.notNull(list ,"该用户不存在");

        return list;
    }
}
