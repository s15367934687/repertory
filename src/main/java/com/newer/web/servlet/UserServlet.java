package com.newer.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.newer.domain.User;
import com.newer.service.UserService;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action==null) {
            Login(req, resp);
        }else if(action.equals("changePwd")) {
            //UpDate(req, resp);
        }else if(action.equals("findAll")) {
            //findAll(req,resp);
        }
    }

    private void Login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String co = (String) session.getAttribute("code");
        String code = req.getParameter("code");
        JsonObject jo = new JsonObject();
        if (code.equals(co)) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            User user =userService.findBy(username, password);
            if (user == null) {
                jo.addProperty("msg", "您输入的账号或者密码错误 ");
            } else {
                session.setAttribute("loginer", user);
                jo.addProperty("suc", true);
            }
        } else {
            jo.addProperty("msg", "您输入的验证码错误 ");
        }
        PrintWriter out = resp.getWriter();
        out.print(jo);
        out.close();
    }
}
