package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ParkingInfoDAO extends DAO {

    public String searchInfo(String cid, String starttime, String endtime){
        String sql = "select * from park.parkinginfo where carid = '"+cid+"' OR (intime>='"+starttime+"' AND  outtime<='"+endtime+"' )";
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

    public boolean isFixedUser(String num) {
    	boolean ans = false;
    	String sql = "select * from park.cardinfo where cid = '" + num + "'";
    	try {
			set = executeQuery(sql);
			if (set.next()) {
				Timestamp endTime = set.getTimestamp("endtime");
				if (endTime.getTime() > System.currentTimeMillis()) {
					ans = true;
				}		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	close();
    	return ans;
    }
    
    public String getInTime(String num) {
    	String time = "";
    	String sql = "select intime from park.parkinginfo where carid = '" + num + "' and state = 0";
    	try {
			set = executeQuery(sql);
			if (set.next())	time = set.getString("intime");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	close();
    	return time;
    }
    
    public int getTypeId(String type) {
    	int ans = 0;
    	String sql = "select * from park.price where typedname = '" + type + "'";
    	try {
			set = executeQuery(sql);
			if (set.next()) ans = set.getInt("typed_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	close();
    	return ans;
    }
    
    public int getPerPrice(String type) {
    	int ans = 0;
    	String sql = "select perprice from park.price where typedname = '" + type + "'";
    	try {
			set = executeQuery(sql);
			if (set.next()) ans = set.getInt("perprice");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	close();
    	return ans;
    }
    
    public  boolean addParkingInfo(int inout, String num, String time, int typeid, int price, int condition){
        String sql;
        if (inout == 0) {
        	sql = "insert into park.parkinginfo(carid, intime, typeid, state) values('" + num + "', '" + time + "', '" + typeid + "', '" + inout + "')";
        } else {
        	sql ="update park.parkinginfo set outtime = '" + time + "', state = '" + inout + "',price = '" + price + "', conditions = '" + condition + "' where carid = '" + num + "' and state = 0";
        }
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
        String sql = "select * from park.parkinginfo ";
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
        StringBuilder builder = new StringBuilder();
        try {
            while (set.next()){
                builder.append(set.getInt("id")+"+");
                builder.append(set.getString("carid")+"+");
                builder.append(set.getTimestamp("intime")+"+");
                builder.append(set.getTimestamp("outtime")+"+");
                builder.append(set.getInt("typeid")+"+");
                builder.append(set.getInt("state")+"+");
                builder.append(set.getInt("price")+"+");
                builder.append(set.getInt("condition")+";");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return builder.toString();
    }

}
