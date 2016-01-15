package com.mycompany.ssm.service.impl;

import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.mycompany.ssm.dao.UserDao;
import com.mycompany.ssm.model.User;
import com.mycompany.ssm.service.UserService;

import javax.annotation.Resource;

/**
 * @author JinBingBing
 *
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
	private UserDao userDao;
	
	public boolean addUser(User user){
		if(user==null)
			return false;
		if (StringUtils.isBlank(user.getName())) {
			return false;
		}
		boolean result = userDao.insertUser(user)>0;
		
		return result;
	}
	
	public List<User> queryUserList(User user){
		if(user==null)
			return null;
		List<User> l = userDao.selectUserList(user);
		return l;
		
	}

}
