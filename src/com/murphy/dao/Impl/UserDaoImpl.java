package com.murphy.dao.Impl;

import com.murphy.bean.User;
import com.murphy.dao.UserDao;
import com.murphy.utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author murphy
 */
public class UserDaoImpl extends DbUtils implements UserDao {
    @Override
    public User findByUser(String username) {
        User user = new User();
        try {
            String sql = "select * from user where user_name = ?";
            List list = new ArrayList();
            list.add(username);
            ResultSet rs = query(sql,list);
            while (rs.next()){
                user.setUserName(rs.getString(1));
                user.setUserPass(rs.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return user;
    }
}
