package com.mycompany.ssm.service.impl;

import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.mycompany.ssm.commons.JsonReport;
import com.mycompany.ssm.commons.UUIDUtil;
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
		Assert.notNull(user,"用户添加失败");
		user.setId(UUIDUtil.getUUID());
		boolean result = userDao.insertUser(user)>0;
		return result;
	}
	
	public List<User> queryUserList(User user){
		Assert.notNull(user,"查询失败，查询条件不能为空");
		List<User> l = userDao.selectUserList(user);
		return l;
		
	}
	
	public boolean updateUser(User user){
		Assert.notNull(user, "更新内容不能为空");
		Assert.notNull(user.getId(),"更新id不能为空");
		boolean result = userDao.updateUser(user)>0;
		return result;
	}
	
	public boolean deleteUser(int id){
		Assert.notNull(userDao.getUserById(id),"该用户不存在");
		boolean result = userDao.deleteUserById(id)>0;
		return result;
	}
	
	public List<User> queryUserListByKey(String key){
		Assert.notNull(key, "没有找到用户");
		List<User> l = userDao.selectUserListByKey(key);
		return l;
	}

}
