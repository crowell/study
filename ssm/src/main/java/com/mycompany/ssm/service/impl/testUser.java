package com.mycompany.ssm.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.ssm.model.User;
import com.mycompany.ssm.service.UserService;

//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring-mybatis.xml", "classpath:springmvc.xml" })
public class testUser {

	// @Test
	public void insertUser() {
		UserService userService = new UserServiceImpl();
		userService.addUser(new User("ahaha"));
		System.out.println("1");
	}

}
