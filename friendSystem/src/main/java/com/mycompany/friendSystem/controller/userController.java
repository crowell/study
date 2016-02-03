package com.mycompany.friendSystem.controller;

import com.mycompany.friendSystem.service.FriendService;
import com.mycompany.friendSystem.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.friendSystem.commons.JsonReport;
import com.mycompany.friendSystem.model.User;
import com.mycompany.friendSystem.service.UserService;

/**
 * Created by JinBingBing on 2016/1/27.
 */
@Controller
@RequestMapping("/userSystem")
public class userController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/add",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object addUser(User user){
		JsonReport jr = new JsonReport();
		try{
			boolean result = userService.addUser(user);
			if(!result){
				jr.setSuccess(false);
				jr.setErrorMsg("连接数据库失败");
			}
			jr.setData(userService.getUser(user.getId()));
		}catch(Exception e){
			jr.setSuccess(false);
		jr.setData("error");
		jr.setErrorMsg(e.getMessage());
		}
		return jr;
    }
    @RequestMapping(value="/update",produces ={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object updateUser(User user){
    	JsonReport jr = new JsonReport();
    	try{
    		boolean result = userService.updateUser(user);
			if(!result){
				jr.setSuccess(false);
				jr.setErrorMsg("连接数据库失败");
			}
    		jr.setData(userService.getUser(user.getId()));
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
    		boolean result = userService.deleteUser(id);
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
    @RequestMapping(value="/queryUser",produces ={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object queryUserList(User user){
    	JsonReport jr = new JsonReport();
    	try{
    		jr.setData(userService.queryUserList(user));
    	}catch(Exception e){
    		jr.setSuccess(false);
    		jr.setData("error");
    		jr.setErrorMsg(e.getMessage());
    	}
    	return jr;
    }
    @RequestMapping(value="/queryUserByCondition",produces ={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object queryUserListByKey(String queryCondition){
    	JsonReport jsonReport = new JsonReport();
    	try{
    		jsonReport.setData(userService.queryUserByCondition(queryCondition));
    	}catch(Exception e){
    		jsonReport.setSuccess(false);
    		jsonReport.setData("error");
    		jsonReport.setErrorMsg(e.getMessage());
    	}
    	return jsonReport;
    }
    @RequestMapping(value="/getUser",produces ={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object getUserById(String id){
    	JsonReport jsonReport = new JsonReport();
    	User user;
    	try{
    		user = userService.getUser(id);
    		jsonReport.setData(user);
    	}catch(Exception e){
    		jsonReport.setSuccess(false);
    		jsonReport.setData("error");
    		jsonReport.setErrorMsg(e.getMessage());
    	}
    	return jsonReport;
    }
    @RequestMapping(value="/getUserByUserName",produces ={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object getUserByUsername(String username){
    	JsonReport jsonReport = new JsonReport();
    	User user;
    	try{
    		user = userService.getUserByUsername(username);
    		jsonReport.setData(user);
    	}catch(Exception e){
    		jsonReport.setSuccess(false);
    		jsonReport.setData("error");
    		jsonReport.setErrorMsg(e.getMessage());
    	}
    	return jsonReport;
    	
    }

}
