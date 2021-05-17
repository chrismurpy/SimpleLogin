package com.murphy.servlet;

import com.murphy.bean.User;
import com.murphy.dao.Impl.UserDaoImpl;
import com.murphy.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author murphy
 */
@WebServlet(urlPatterns = "/test")
public class UserServlet extends HttpServlet {

    UserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("userName");
        String password = req.getParameter("userPass");
        User user = userDao.findByUser(username);
        String userPass = user.getUserPass();

        if (user != null) {
            HttpSession session = req.getSession();
            if (password.equals(userPass)) {
                session.setAttribute("uname",username);
                resp.sendRedirect("/success.jsp");
            } else {
                Cookie cookie = new Cookie("uname",username);
                resp.addCookie(cookie);
                resp.sendRedirect("/false.jsp");
            }
        } else {
            resp.sendRedirect("/false.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/false.jsp");
    }
}
