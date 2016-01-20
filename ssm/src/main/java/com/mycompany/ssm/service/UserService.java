package com.mycompany.ssm.service;

import java.util.List;

import com.mycompany.ssm.model.User;

/**
 * @author JinBingBing
 *
 */
public interface UserService {
	
	public boolean addUser(User user);
	public boolean deleteUser(String id);
	public boolean updateUser(User user);
	public User getUserById(String id);
	public List<User> queryUserList(User user);
	public List<User> queryUserListByKey(String queryCondition);

}
