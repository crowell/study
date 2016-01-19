package com.mycompany.ssm.dao;

import com.mycompany.ssm.commons.Condition;
import com.mycompany.ssm.model.User;

import java.util.List;


/**
 * @author JinBingBing
 *
 */
public interface UserDao {
	
	public int insertUser(User user);
	public int deleteUserById(String id);
	public int updateUser(User user);
	public User getUserById(String id);
	public List<User> selectUserList(User user);
	public List<User> selectUserListByKey(String queryCondition);

}
