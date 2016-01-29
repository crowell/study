package com.mycompany.friendSystem.service.impl;

import com.mycompany.friendSystem.model.Friend;

/**
 * Created by JinBingBing on 2016/1/29.
 */
public class FriendSelect {
    public static boolean judgeFriend(Friend friend1,Friend friend2){
        if(friend1==null||friend2==null)
            return true;
        else{
            String friend_id1 = friend1.getFriend_id();
            String friend_id2 = friend2.getFriend_id();
            if (friend_id1.equals(friend_id2))
                return true;
            return false;
        }

    }
}
