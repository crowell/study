package com.mycompany.friendSystem.dao;

import com.mycompany.friendSystem.model.User;

import java.util.List;

/**
 * Created by JinBingBing on 2016/1/27.
 */
public interface UserDao {

    public int insertUser(User user);
    public int updateUser(User user);
    public int deleteUserById(String id);
    public User getUserById(String id);
    public User getUserByUsername(String username);
    public List<User> selectUserList(User user);
    public List<User> selectUserByCondition(String queryCondition);
}
