package com.mycompany.friendSystem.service;

import com.mycompany.friendSystem.model.Relation;

import java.util.List;

/**
 * Created by JinBingBing on 2016/1/28.
 */
public interface RelationService {
    public boolean insertRelation(Relation relation);
    public boolean deleteRelationById(String id);
    public boolean deleteRelationByUser_id(String user_id);
    public boolean updateRelation(Relation relation);
    public Relation getRelation(String id);
    public List<Relation> queryRelationList(String user_id);

}