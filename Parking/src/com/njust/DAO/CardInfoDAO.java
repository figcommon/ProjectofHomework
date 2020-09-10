package com.njust.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardInfoDAO extends DAO {

    public String getAllCardInfo(){
        String sql = "select * from park.cardinfo ";
        String msg = null;
        try {
            set = executeQuery(sql);
            msg = getCardInfo(set);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        close();
        return msg;
    }
    
    public boolean SelectCardInfoById(String id){
        String sql = "select * from park.cardinfo where id='"+ id+"';";
        String msg = null;
        try {
            set = executeQuery(sql);
            if(set.next())
            	return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        close();
        return false;
    }
    
    
    public boolean addCardInfo(String id, String name, String telnum, String password, String cid, String endtime){
    	String sql = "insert into park.cardinfo(id,name,telnum,password,cid,endtime) " +
                "values('" + id +"','"+name+"','"+telnum+"','"+password+"','"+cid+"','"+endtime+"')";
        int r = 0;
        try {
            r = executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        close();
        if(r != 0) return true;
    	return false;
    }
    
    public boolean modifyCardInfo(String id, String name, String telnum, String password, String cid, String endtime){
    	String sql = "update park.cardinfo set id ='"+id+"', name='"+name+"', telnum='"+telnum+"',password='"+password+"',cid='"+cid+"',endtime='"+endtime+"' where id='"+id+"';";
        int r = 0;
        try {
            r = executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        close();
        if(r != 0) return true;
    	return false;
    }

    private String getCardInfo(ResultSet set){
        StringBuilder builder = new StringBuilder();
        try {
            while (set.next()){
                builder.append(set.getString("id")+"+");
                builder.append(set.getString("name")+"+");
                builder.append(set.getString("telnum")+"+");
                builder.append(set.getString("password")+"+");
                builder.append(set.getString("cid")+"+");
                builder.append(set.getTimestamp("endtime")+";");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void main(String[] args){
        System.out.println(new CardInfoDAO().getAllCardInfo());
    }
}
