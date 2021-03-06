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
    @RequestMapping(value="/update",produces ={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object updateUser(Friend friend){
        JsonReport jr = new JsonReport();
        try{
            boolean result = friendService.updateFriend(friend);
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
    @RequestMapping(value="/delete",produces ={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object deleteUserById(String id){
        JsonReport jr = new JsonReport();
        String s = "删除成功";
        try{
            boolean result = friendService.deleteFriendById(id);
            if(!result){
                jr.setSuccess(false);
                jr.setErrorMsg("连接数据库失败");
            }
            jr.setData(s);
        }catch(Exception e){
            jr.setSuccess(false);
            jr.setData("error");
            jr.setErrorMsg(e.getMessage());
        }
        return jr;
    }
    @RequestMapping(value="/getUser",produces ={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object getUserById(String id){
        JsonReport jsonReport = new JsonReport();
        Friend friend;
        try{
            friend = friendService.getFriendById(id);
            jsonReport.setData(friend);
        }catch(Exception e){
            jsonReport.setSuccess(false);
            jsonReport.setData("error");
            jsonReport.setErrorMsg(e.getMessage());
        }
        return jsonReport;
    }
    @RequestMapping(value="/queryFriend",produces ={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object queryUserList(String relation_id){
        JsonReport jr = new JsonReport();
        try{
            jr.setData(friendService.queryFriendByRelation_id(relation_id));
        }catch(Exception e){
            jr.setSuccess(false);
            jr.setData("error");
            jr.setErrorMsg(e.getMessage());
        }
        return jr;
    }
}
