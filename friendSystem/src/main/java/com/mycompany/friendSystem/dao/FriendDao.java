package com.mycompany.friendSystem.dao;

import com.mycompany.friendSystem.model.Friend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by JinBingBing on 2016/1/27.
 */
public interface FriendDao {
    public int insertFriend(Friend friend);
    public int updateFriend(Friend friend);
    public int deleteFriendById(String id);
    public int deleteFriendBuRelation_id(String relation_id);
    public Friend getFriendById(String id);
    public List<Friend> queryFriendByRelation_id( String relation_id);
}
