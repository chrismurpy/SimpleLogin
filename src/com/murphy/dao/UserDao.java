package com.murphy.dao;

import com.murphy.bean.User;

/**
 * @author murphy
 */
public interface UserDao {
    /**
     * 根据账号查询用户
     * @param username
     * @return user
     */
    public User findByUser(String username);
}
