package com.njust.servlet;

import com.njust.DAO.PriceDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddPrice extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        //接收ajax发送来的信息
        String typeid = req.getParameter("typeid");
        typeid = new String(typeid.getBytes("iso8859-1"), "UTF-8");
        String typename = req.getParameter("typename");
        typename = new String(typename.getBytes("iso8859-1"), "UTF-8");
        String perprice = req.getParameter("perprice");
        perprice = new String(perprice.getBytes("iso8859-1"), "UTF-8");

        System.out.println(typeid+"&"+typename+"&"+perprice);
        //防返回汉字乱码
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        //写回
        try {
             if (new PriceDAO().addPrice(Integer.parseInt(typeid), typename, Integer.parseInt(perprice))){
                 writer.write("添加成功！");
             }
             else {
                 writer.write("添加失败！");
             }
        }catch (Exception e){
            writer.write("数据格式错误！");
        }

        //关闭
        writer.close();
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        doGet(req,resp);
    }
}
