package com.mycompany.friendSystem.service.impl;

import com.mycompany.friendSystem.commons.UUIDUtil;
import com.mycompany.friendSystem.dao.RelationDao;
import com.mycompany.friendSystem.model.Relation;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by JinBingBing on 2016/1/28.
 */
public class RelationServiceImpl {

    @Resource
    private RelationDao relationDao;
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
    public boolean deleteRelation(String id){
        Assert.notNull(id,"列表id不能为空");
        Relation relation = relationDao.getRelationById(id);
        Assert.notNull(relation,"列表不存在");
        boolean result = relationDao.deleteRelationById(id)>0;

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
    * 获取用户好友列表
    * */
    public List<Relation> queryRelationList(String user_id){

    }
}
