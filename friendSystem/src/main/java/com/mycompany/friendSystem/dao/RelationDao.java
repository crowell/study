package com.mycompany.friendSystem.dao;

import com.mycompany.friendSystem.model.Relation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by JinBingBing on 2016/1/27.
 */
public interface RelationDao {
    public int insertRelation(Relation relation);
    public int updateRelation(Relation relation);
    public int deleteRelationById(String id);
    public int deleteRelationBuUser_id(String uer_id);
    public List<Relation> queryRelationByUser_id(String user_id);
    public Relation getRelationById(String id);
    public Relation queryRelationByCondition(@Param(value = "queryCondition") String queryCondition);
}
