package com.mycompany.friendSystem.service.impl;

import com.mycompany.friendSystem.model.Relation;
import com.mycompany.friendSystem.model.User;

/**
 * Created by JinBingBing on 2016/1/27.
 */
public class DefaultRelation {
    public static Relation getDefaultRelationShip(String string , User user){
        Relation r = null;
        r.setRelationship(string);
        r.setUser_id(user.getId());
        r.setId(UUIDUtil.getUUID());
        return r;
    }
}
