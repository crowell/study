package com.mycompany.ssm.dao;

import com.mycompany.ssm.model.User;

import java.util.List;


/**
 * @author JinBingBing
 *
 */
public interface UserDao {
	
	public int insertUser(User user);
	public int deleteUserById(int id);
	public int updateUser(User user);
	public User getUserById(int id);
	public List<User> selectUserList(User user);

}
