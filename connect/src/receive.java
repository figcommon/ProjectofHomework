import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JScrollBar;

import dao.ParkingInfoDAO;

public class receive {
    public static final int MY_SERVER_PORT =55555;
    public static final Charset MY_COMM_CHARSET= StandardCharsets.UTF_8;
    public static String line = null;

    public void updateUI(int inout, String num, String type, String time, int price, boolean isFixed) {	
		String inoutStr;
		if (inout == 0) {
			inoutStr = "进";
		} else {
			inoutStr = "出";
		}
		String msg, log = inoutStr + " " + num + " " + type + " " + time;
		if (price < 0) {
			msg = "-";
		}
		else if (isFixed) {
			msg = "固定用户，免费通行(" + price + "元)";
			log = log + " " + msg;
		} else {
			msg = "" + price + "元";
			log = log + " " + msg;
		}

	}
    
    public void insertData(String str) {
		String[] s = str.split("\\+");
		int inout;
		if (s[0].equals("0")) inout = 0;
		else inout = 1;
		String num = s[1];
		String time = s[3];
		String type = s[2];
		int price = -1;
		ParkingInfoDAO dao = new ParkingInfoDAO();
		int typeid = dao.getTypeId(type);
		int condition = 0;
		if (inout == 1) {
			String intime = dao.getInTime(num);
			int perprice = dao.getPerPrice(type);
			SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date inDate = sFormat.parse(intime);
				Date outDate = sFormat.parse(time);
				long from = inDate.getTime();
				long to = outDate.getTime();
				int hours = (int)((to - from) / (1000 * 60 * 60));//计算费用
				price = perprice * hours;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		boolean isFixed = dao.isFixedUser(num);
		if (isFixed) {condition = 1;price=5;}
		dao.addParkingInfo(inout, num, time, typeid, price, condition);
		
		updateUI(inout, num, type, time, price, isFixed);
	}
    
    ServerSocket ss=new ServerSocket(MY_SERVER_PORT);
    Socket s=new Socket();
    public receive() throws IOException {

        while (true){
            s=ss.accept();
            System.out.println("开始监听");
            try(BufferedReader br =new BufferedReader(
                    new InputStreamReader(s.getInputStream(),MY_COMM_CHARSET)
            );){
                line =br.readLine();///////line 为接收到的数据

                insertData(line);
                
                System.out.println(line);////

            }catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("通讯机开始启动，在端口"+MY_SERVER_PORT+"监听");
        }

    }

    public void close() throws IOException {
        ss.close();
        s.close();
    }
	public String getLine(){
		return line;
	}
}
