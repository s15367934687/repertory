package com.newer.web.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newer.domain.InStorage;
import com.newer.service.InStorageService;
import com.newer.util.JsonTypeAdapter;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/instorage")
public class InStorageServlet extends HttpServlet {
    private InStorageService inStorageService;

    @Override
    public void init() throws ServletException {
        inStorageService = new InStorageService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action==null){
            List<InStorage> list = inStorageService.findAll();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().
                    registerTypeAdapter(String.class, JsonTypeAdapter.STRING).
                    registerTypeAdapter(Date.class, JsonTypeAdapter.DATE).
                    create();
            PrintWriter out = resp.getWriter();
            out.print(gson.toJsonTree(list));
            out.close();
        }else if (action.equals("insert")) {
            Insert(req, resp);
        }
    }

    private void Insert(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        InStorage inStorage = new InStorage();
        try{
            BeanUtils.populate(inStorage, req.getParameterMap());
            PrintWriter out = resp.getWriter();
            int count = inStorageService.insert(inStorage);
            out.print(count);
            out.close();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } /*catch (ParseException e) {
            e.printStackTrace();
        }*/
    }
}
