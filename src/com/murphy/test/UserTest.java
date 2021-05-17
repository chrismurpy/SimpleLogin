package com.murphy.test;


import com.murphy.bean.User;
import com.murphy.dao.Impl.UserDaoImpl;
import com.murphy.dao.UserDao;
import org.junit.Test;

/**
 * @author murphy
 */
public class UserTest {
    private UserDao userDao = new UserDaoImpl();
    @Test
    public void getUser(){
        User user = new User();
        user = userDao.findByUser("murphymurphy");
        System.out.println(user.getUserName());
        System.out.println(user.getUserPass());
    }
}
