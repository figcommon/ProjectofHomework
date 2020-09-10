package dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PriceDAO extends DAO {

	public List<String> getAllType() {
		List<String> list = new ArrayList<>();
		String sql = "select * from park.price";
		try {
			set = executeQuery(sql);
			while (set.next()) {
				String str = set.getString("typedname");
				list.add(str);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return list;
	}
	 public int getPrice(String type) {
	    	int ans = 0;
	    	String sql = "select price from park.parkinginfo where carid = '" + type + "'";
	    	try {
				set = executeQuery(sql);
				if (set.next()) ans = set.getInt("price");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	close();
	    	return ans;
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
}
