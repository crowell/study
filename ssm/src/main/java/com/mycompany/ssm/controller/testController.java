package com.mycompany.ssm.controller;

import com.mycompany.ssm.commons.JsonReport;
import com.mycompany.ssm.model.User;
import com.mycompany.ssm.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by JinBingBing on 2016/1/14.
 */
@Controller
@RequestMapping("/test")
public class testController {
	@Autowired
	UserService userService;
	
/*    @RequestMapping(value = "/get",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object get(String firstnum,String lastnum) {
    	JsonReport jr = new JsonReport(); 
    	Double f = 0.00;
    	Double l = 0.00;
    	Double result = 0.00;
    	DecimalFormat df = new DecimalFormat("#.##");
    	String s;
       try{
    	   f=Double.parseDouble(firstnum);
    	   l=Double.parseDouble(lastnum);
    	   Assert.isTrue(l!=0, "分母不能为零");
    	   result = f/l;
    	   s = df.format(result);
    	   jr.setData(s);
       }
       catch (Exception e) {
           jr.setSuccess(false);
           jr.setData("error");
           jr.setErrorMsg(e.getMessage());
       }
    	   return jr;
    }*/
    @RequestMapping(value="/add",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object addUser(User user){
		JsonReport jr = new JsonReport();
		try{
			userService.addUser(user);
			jr.setData(userService.getUserById(user.getId()));
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
    		userService.updateUser(user);
    		jr.setData(userService.queryUserList(user));
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
    		userService.deleteUser(id);
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
    	JsonReport jsonReport = new JsonReport();
    	try{
    		jsonReport.setData(userService.queryUserList(user));
    	}catch(Exception e){
    		jsonReport.setSuccess(false);
    		jsonReport.setData("error");
    		jsonReport.setErrorMsg(e.getMessage());
    	}
    	return jsonReport;
    }
    @RequestMapping(value="/queryUserByKey",produces ={"application/json;charset=UTF-8"})
    @ResponseBody
    public Object queryUserListByKey(String queryCondition){
    	JsonReport jsonReport = new JsonReport();
    	try{
    		jsonReport.setData(userService.queryUserListByKey(queryCondition));
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
    	User user = new User();
    	try{
    		user = userService.getUserById(id);
    		jsonReport.setData(user);
    	}catch(Exception e){
    		jsonReport.setSuccess(false);
    		jsonReport.setData("error");
    		jsonReport.setErrorMsg(e.getMessage());
    	}
    	return jsonReport;
    }
}
