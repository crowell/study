package com.mycompany.friendSystem.model;

import java.io.Serializable;

/**
 * Created by JinBingBing on 2016/1/27.
 */
public class Friend implements Serializable {
    private String id;
    private String friend_id;
    private String relation_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }

    public String getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(String relation_id) {
        this.relation_id = relation_id;
    }
}
