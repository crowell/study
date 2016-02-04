package com.mycompany.friendSystem.service.impl;

import com.mycompany.friendSystem.model.Friend;

import java.util.*;

/**
 * Created by JinBingBing on 2016/2/4.
 */
public class FriendTools {
    public static List<String> getFriend_idList(List<Friend> friendList){
        List<String> list = new ArrayList<String>();
        for(int i=0;i<friendList.size();i++){
            list.add(friendList.get(i).getFriend_id());
        }

        return list;
    }
}
