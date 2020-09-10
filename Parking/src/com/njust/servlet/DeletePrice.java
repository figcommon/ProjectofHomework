package com.njust.servlet;

import com.njust.DAO.PriceDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeletePrice extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        //接收ajax发送来的信息
        String typeid = req.getParameter("typeid");
        typeid = new String(typeid.getBytes("iso8859-1"), "UTF-8");

        System.out.println(typeid);
        //防返回汉字乱码
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        //写回
        try {
            if (new PriceDAO().deletePrice(Integer.parseInt(typeid))){
                writer.write("删除成功！");
            }
            else {
                writer.write("删除失败！");
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
