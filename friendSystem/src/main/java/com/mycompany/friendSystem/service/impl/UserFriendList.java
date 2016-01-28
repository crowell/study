package com.mycompany.friendSystem.service.impl;

import com.mycompany.friendSystem.model.Relation;
import com.mycompany.friendSystem.model.User;

import java.util.List;

/**
 * Created by JinBingBing on 2016/1/28.
 */
public class UserFriendList {
    /*
    * 根据userId获取好友列表
    * */
    public static void getRelationship(List<Relation> relationList, List<String> relationshipList ){
        for(int i=0;i<relationList.size();i++){
            String relationship = relationList.get(i).getRelationship();
            relationshipList.add(relationship);
        }
    }
}
