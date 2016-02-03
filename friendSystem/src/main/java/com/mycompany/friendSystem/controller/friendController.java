package com.mycompany.friendSystem.controller;

import com.mycompany.friendSystem.commons.JsonReport;
import com.mycompany.friendSystem.model.Friend;
import com.mycompany.friendSystem.model.User;
import com.mycompany.friendSystem.service.FriendService;
import com.mycompany.friendSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by JinBingBing on 2016/2/1.
 */
@Controller
@RequestMapping("/friendSystem")
public class friendController {

    @Autowired
    FriendService friendService;

    @RequestMapping(value="/add",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object addUser(Friend friend){
        JsonReport jr = new JsonReport();
        try{
            boolean result = friendService.insertFriend(friend);
            if(!result){
                jr.setSuccess(false);
                jr.setErrorMsg("连接数据库失败");
            }
            jr.setData(friendService.getFriendById(friend.getId()));
        }catch(Exception e){
            jr.setSuccess(false);
            jr.setData("error");
            jr.setErrorMsg(e.getMessage());
        }
        return jr;
    }




}
