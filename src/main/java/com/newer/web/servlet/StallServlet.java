package com.newer.web.servlet;



import com.google.gson.Gson;
import com.newer.domain.Stall;
import com.newer.service.StallService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/stall")
public class StallServlet extends HttpServlet {
    private StallService stallService;

    @Override
    public void init() throws ServletException {
        stallService = new StallService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action==null){
            findAll(req,resp);
        }else if (action.equals("state")) {
            State(req, resp);
        }
    }

    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Stall> list = stallService.findAll();
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJsonTree(list));
        out.close();
    }

    private void State(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int state = Integer.parseInt(req.getParameter("state"));
        PrintWriter out = resp.getWriter();
        int count = stallService.upDate(state,id);
        out.print(count);
        out.close();
    }
}
