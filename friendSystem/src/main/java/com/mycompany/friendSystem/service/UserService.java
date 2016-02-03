package com.mycompany.friendSystem.service;

import com.mycompany.friendSystem.model.User;

import java.util.List;

/**
 * Created by JinBingBing on 2016/1/27.
 */
public interface UserService {
    /*
   * 创建新用户
   * */
    public boolean addUser(User user);
    /*
    * 修改用户信息
    * */
    public boolean updateUser(User user);
    /*
    * 删除用户
    * */
    public boolean deleteUser(String id);
    /*
    * 通过id获取用户信息
    * */
    public User getUser(String id);
    /*
    * 通过username获取用户信息
    * */
    public User getUserByUsername(String username);
    /*
    * 通过部分信息查找用户
    * */
    public List<User> queryUserList(User user);
    /*
    * 通过部分信息的部分内容查找对应用户群
    * */
    public List<User> queryUserByCondition(String queryCondition);
}
