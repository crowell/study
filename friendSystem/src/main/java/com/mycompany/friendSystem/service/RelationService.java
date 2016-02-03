package com.mycompany.friendSystem.service;

import com.mycompany.friendSystem.model.Relation;

import java.util.List;

/**
 * Created by JinBingBing on 2016/1/28.
 */
public interface RelationService {
    /*
    * 新建好友列表
    * */
    public boolean insertRelation(Relation relation);
    /*
    * 删除好友列表
    * */
    public boolean deleteRelationById(String id);
    /*
    * 清空用户好友列表
    * */
    public boolean deleteRelationByUser_id(String user_id);
    /*
    * 修改好友列表
    * */
    public boolean updateRelation(Relation relation);
    /*
    * 获取好友列表
    * */
    public Relation getRelation(String id);
    /*
    * 获取用户所有好友列表
    * */
    public List<Relation> queryRelationList(String user_id);

}
