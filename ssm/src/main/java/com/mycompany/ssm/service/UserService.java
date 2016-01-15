package com.mycompany.ssm.service;

import java.util.List;

import com.mycompany.ssm.model.User;

/**
 * @author JinBingBing
 *
 */
public interface UserService {
	
	public boolean addUser(User user);
	public List<User> queryUserList(User user);

}
