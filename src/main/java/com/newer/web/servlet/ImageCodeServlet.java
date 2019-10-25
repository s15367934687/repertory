package com.newer.web.servlet;

import com.newer.util.SecurityCode;
import com.newer.util.SecurityImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/imageCode")
public class ImageCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = SecurityCode.getSecurityCode();
        HttpSession session = req.getSession();
        //	将随机验证码存入session
        session.setAttribute("code", code);
        //	设置响应内容类型
        resp.setContentType("image/jpeg");
        //	设置不缓存相应内容（通知浏览器）
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        //	设置响应失效时间
        resp.setDateHeader("Expires", 0);
        //	使用响应对象输出流输出验证码图片
        ImageIO.write(SecurityImage.getImage(code), "jpeg", resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
