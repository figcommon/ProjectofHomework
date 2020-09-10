package com.njust.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PriceDAO extends DAO{

	
    public boolean deletePrice(int typeid){
        String sql="delete from park.price where typed_id="+typeid+";";
        try {
            if(executeUpdate(sql)==1)return true;
            else return false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        close();
        return false;
    }

    public  boolean addPrice(int typeid, String typename, int perprice){
        String sql = "insert into park.price(typed_id,typedname,perprice) " +
                "values(" + typeid +",'"+typename+"',"+perprice+")";
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
    public String getAllPrices(){
        String sql = "select * from park.price";
        String msg = null;
        try {
            set = executeQuery(sql);
            msg = getPrices(set);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        close();
        return msg;
    }

    private String getPrices(ResultSet set){
        StringBuilder builder = new StringBuilder();
        try {
            while (set.next()){
                builder.append(set.getInt("typed_id")+"+");
                builder.append(set.getString("typedname")+"+");
                builder.append(set.getInt("perprice")+";");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void main(String[] args){
        System.out.println(new PriceDAO().getAllPrices());
    }
}
