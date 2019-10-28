package com.newer.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.newer.util.MD5;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.newer.domain.User;
import com.newer.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

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
        }else if(action.equals("login")) {
            Xianshi(req, resp);
        }else if(action.equals("logout")) {
            Logout(req,resp);
        }else if(action.equals("changePwd")) {
            UpDate(req, resp);
        }else if(action.equals("findAll")) {
            findAll(req,resp);
        }else if (action.equals("delete")) {
            Delete(req, resp);
        }else if (action.equals("insert")) {
            Insert(req, resp);
        }
    }

    private void Insert(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        User user = new User();
        try {
            BeanUtils.populate(user, req.getParameterMap());
            PrintWriter out = resp.getWriter();
            int count = userService.insert(user);
            out.print(count);
            out.close();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void Delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("userid"));
        PrintWriter out = resp.getWriter();
        if(userService.delete(id)>0) {
            out.print(1);
            out.close();
        }
    }
    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> list = userService.findAll();
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJsonTree(list));
        out.close();
    }
    private void UpDate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user =(User) session.getAttribute("loginer");
        JsonObject jo = new JsonObject();
        String pwd = MD5.getInstance().getMD5ofStr(req.getParameter("pwd"));
        String password =req.getParameter("password");
        if(user.getPwd().equals(pwd)) {
            if(userService.upDate(password, user.getUserId())>0) {
                jo.addProperty("changed", true);
                //	更新session中保存的用户原始密码
                user.setPwd(MD5.getInstance().getMD5ofStr(password));
            }
        }else {
            jo.addProperty("msg", "您输入的原密码错误 ");
        }
        PrintWriter out = resp.getWriter();
        out.print(jo);
        out.close();
    }
    private void Logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        session.invalidate();
        out.print(1);
        out.close();
    }
    private void Xianshi(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        User user = (User) session.getAttribute("loginer");
        if (user != null) {
            Gson gson = new Gson();
            out.print(gson.toJson(user));
        }else {
            out.print(0);
        }
        out.close();
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
