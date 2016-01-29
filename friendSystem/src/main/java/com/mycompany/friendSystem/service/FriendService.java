package com.mycompany.friendSystem.service;

import com.mycompany.friendSystem.model.Friend;

import java.util.List;

/**
 * Created by JinBingBing on 2016/1/29.
 */
public interface FriendService {
    /*
    * 添加好友
    * */
    public boolean insertFriend(Friend friend);
    /*
    * 删除好友
    * */
    public boolean deleteFriendById(String id);
    /*
    * 删除清空好友列表
    * */
    public boolean deleteFriendByRelation_id(String relation_id);
    /*
    * 修改好友所在列表
    * */
    public boolean updateFriend(Friend friend);
    /*
    * 查询好友
    * */
    public Friend getFriendById(String id);
    /*
    * 显示列表所有好友
    * */
    public List<Friend> queryFriendByRelation_id(String relation_id);
}
