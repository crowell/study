package com.mycompany.friendSystem.dao;

import com.mycompany.friendSystem.model.Relation;

import java.util.List;

/**
 * Created by JinBingBing on 2016/1/27.
 */
public interface RelationDao {
    public int insertRelation(Relation relation);
    public int updateRelation(Relation relation);
    public int deleteRelationById(String id);
    public List<Relation> getRelationByUser_id(String user_id);
}
