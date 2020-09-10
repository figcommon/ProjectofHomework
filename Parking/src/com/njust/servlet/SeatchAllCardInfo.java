package com.njust.servlet;

import com.njust.DAO.CardInfoDAO;
import com.njust.DAO.PriceDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SeatchAllCardInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        //接收ajax发送来的信息
        String callback = req.getParameter("code");
        callback = new String(callback.getBytes("iso8859-1"), "UTF-8");

        //防返回汉字乱码
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        //写回
        String msg = new CardInfoDAO().getAllCardInfo();
        writer.write(msg);

        //关闭
        writer.close();
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        doGet(req, resp);
    }
}
