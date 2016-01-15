package com.mycompany.ssm.controller;

import com.mycompany.ssm.commons.JsonReport;
import com.mycompany.ssm.model.User;
import com.mycompany.ssm.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by JinBingBing on 2016/1/14.
 */
@Controller
@RequestMapping("/test")
public class testController {
	@Autowired
	UserService userService;
	
    @RequestMapping(value = "/get",produces = {"application/json;charset=UTF-8"})
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
    }
    @RequestMapping(value="/add",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object addUser(User user){
   	
    }
}