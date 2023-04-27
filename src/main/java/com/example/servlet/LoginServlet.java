package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null){
            try {
                resp.sendRedirect("/login.jsp");
            }catch (IOException e){
                e.printStackTrace();
            }
        }else {
            try {
                resp.sendRedirect("/user/hello.jsp");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (password==null){
            try {
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }catch (ServletException | IOException e){
                e.printStackTrace();
            }
        }
        List<String> users = Users.getInstance().getUsers();
        for (String user : users){
            if (user.equals(login) || password != null){
                try {
                    resp.sendRedirect("/user/hello.jsp");
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else {
                try {
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                }catch (ServletException | IOException e){
                    e.printStackTrace();
                }
            }
        }


    }
}
