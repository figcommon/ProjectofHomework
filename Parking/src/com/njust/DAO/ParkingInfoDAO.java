package com.njust.DAO;

import com.njust.Bean.ParkingInfo;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ParkingInfoDAO extends DAO {

    public String searchInfo(String cid, String starttime, String endtime){
        String sql = "select * from park.parkinginfo where carid = '"+cid+"' or (intime>='"+starttime+"' and outtime<='"+endtime+"')";
        System.out.println(sql);
        String msg = null;
        try {
            set = executeQuery(sql);
            msg = getParkingInfo(set);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        close();
        return msg;
    }

    public  boolean addParkingInfo(int id, String cid, String intime, String outtime, int typeid, int state, int price, int condition){
        String sql = "insert into price(id,carid,intime, outtime, typeid, ) " +
                "values(" + id +",'"+cid+"','"+intime+"',"+",'"+outtime+"',"+typeid+","+state+","+price+","+condition+")";
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
    public String getAllParkingInfo(){
        String sql = "select * from park.parkinginfo";
        String msg = null;
        try {
            set = executeQuery(sql);
            msg = getParkingInfo(set);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        close();
        return msg;
    }

    private String getParkingInfo(ResultSet set){
    	
    	String prices = new PriceDAO().getAllPrices();
    	String[] types = prices.split(";");
    	String[][] typess = new String[types.length][];
    	for(int i = 0; i < types.length; i++){
//    		System.out.println(types[i]);
    		typess[i] = types[i].split("\\+");
//    		System.out.println(typess[i][1]);
    	}
    	
//    	System.out.println(getKey(1, typess));
        StringBuilder builder = new StringBuilder();
        try {
            while (set.next()){
                builder.append(set.getInt("id")+"+");
                builder.append(set.getString("carid")+"+");
                builder.append(set.getTimestamp("intime")+"+");
                builder.append(set.getTimestamp("outtime")+"+");
                builder.append(getKey(set.getInt("typeid"), typess)+"+");
                int a = set.getInt("state");
                if(a==1){
                	builder.append("已经出库"+"+");
                }
                else{
                    builder.append("在车库中"+"+");
                }
                builder.append(set.getInt("price")+"+");
                int b = set.getInt("conditions");
                if(b==1){
                    builder.append("临时停车"+";");
                }
                else{
                	builder.append("非临时停车"+";");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return builder.toString();
    }
    
    public String getKey(int id, String[][] typess){
    	
    	for(int i = 0; i < typess.length; i++){
    		if(typess[i][0].equals(id+"")){
    			return typess[i][1];
    		}
    	}
    	return "未知车型";
    	
    }

    public static void main(String[] args){
        PriceDAO pd = new PriceDAO();
        System.out.println(pd.getAllPrices());
    	System.out.println("111：："+new ParkingInfoDAO().getAllParkingInfo());
//        System.out.println("222：："+new ParkingInfoDAO().searchInfo("辽A-85B97", "", ""));
//        System.out.println("333：："+new ParkingInfoDAO().searchInfo("", "2018-10-1", "2020-10-1"));
//        System.out.println("222：："+new ParkingInfoDAO().searchInfo("", "2018-10-1", ""));
//        System.out.println("444：："+new ParkingInfoDAO().searchInfo("", "", "2020-10-1"));
//        System.out.println("555：：："+new ParkingInfoDAO().searchInfo("辽A-85B97", "2018-10-1", "2020-10-1"));
    }
    
}
