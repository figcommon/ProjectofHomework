package com.njust.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njust.DAO.CardInfoDAO;

public class Modify extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        //接收ajax发送来的信息
        String id = req.getParameter("id");
        id = new String(id.getBytes("iso8859-1"), "UTF-8");
        String name = req.getParameter("name");
        name = new String(name.getBytes("iso8859-1"), "UTF-8");
        String telnum = req.getParameter("telnum");
        telnum = new String(telnum.getBytes("iso8859-1"), "UTF-8");
        String password = req.getParameter("password");
        password = new String(password.getBytes("iso8859-1"), "UTF-8");
        String cid = req.getParameter("cid");
        cid = new String(cid.getBytes("iso8859-1"), "UTF-8");
        String endtime = req.getParameter("endtime");
        endtime = new String(endtime.getBytes("iso8859-1"), "UTF-8");
        
        //防返回汉字乱码
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        //写回
        CardInfoDAO cd = new CardInfoDAO();
        String msg;
        if(cd.SelectCardInfoById(id)){
        	if(cd.modifyCardInfo(id, name, telnum, password, cid, endtime)){
        		msg = "修改成功！";
        	}
        	else{
        		msg = "修改失败！";
        	}
        }
        else{
        	if(cd.addCardInfo(id, name, telnum, password, cid, endtime)){
        		msg = "添加成功！";
        	}
        	else{
        		msg = "添加失败！";
        	}
        }
//        String msg = new CardInfoDAO().modify();
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
