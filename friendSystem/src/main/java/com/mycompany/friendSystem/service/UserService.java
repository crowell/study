package com.mycompany.friendSystem.service;

import com.mycompany.friendSystem.model.User;

import java.util.List;

/**
 * Created by JinBingBing on 2016/1/27.
 */
public interface UserService {
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(String id);
    public User getUser(String id);
    public User getUserByUsername(String username);
    public List<User> queryUserList(User user);
    public List<User> queryUserByCondition(String queryCondition);
}
