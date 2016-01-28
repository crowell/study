package com.mycompany.friendSystem.dao;

import com.mycompany.friendSystem.model.Friend;

import java.util.List;

/**
 * Created by JinBingBing on 2016/1/27.
 */
public interface FriendDao {
    public int insertFriend(Friend friend);
    public int updateFriend(Friend friend);
    public int deleteFriendById(String id);
    public List<Friend> getFriendByRelation_id(String relation_id);
}
