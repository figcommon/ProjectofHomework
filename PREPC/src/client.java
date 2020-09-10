import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import dao.PriceDAO;
public class client {
    public static final Charset MY_COMM_CHARSET= StandardCharsets.UTF_8;
    private JFrame f;		//	f=创建前置机的对话框
    private JLabel numLabel, typeLabel, timeLabel,inoutLabel, priceLabel, price;
    private JTextField num, time;	//文本框 ：车牌号 时间
    private JComboBox<String> inout, type;
    private JToggleButton confirm;
    private send instance;		//发送的数据实例 Sender类
    public String sendStr;
    public int flag;
    private String cid;

    public client() throws IOException {

        f = new JFrame("前置机");
        f.setSize(600, 580);
        f.setLocation(200, 200);
        f.setLayout(null);

        numLabel = new JLabel("CarID:");//车牌号
        numLabel.setBounds(150, 50, 80, 40);
        num = new JTextField();
        num.setBounds(250, 50, 180, 40);

        PriceDAO dao = new PriceDAO();
        List<String> list = dao.getAllType();
        typeLabel = new JLabel("Type:");//车的类型
        typeLabel.setBounds(150, 130, 80, 40);
        String[] typeStr = {"-"};
        type = new JComboBox<>(typeStr);
        for (String str : list) {
            type.addItem(str);
        }
        type.setBounds(250, 130, 180, 40);

        timeLabel = new JLabel("Time:");//时间
        timeLabel.setBounds(150, 210, 80, 40);
        time = new JTextField();
        time.setBounds(250, 210, 180, 40);

        inoutLabel = new JLabel("In/Out:");//进/出
        inoutLabel.setBounds(150, 290, 80, 40);
        String[] inoutStr = {"In", "Out"};
        inout = new JComboBox<>(inoutStr);
        inout.setBounds(250, 290, 180, 40);

        priceLabel = new JLabel("Price:");//价格
        priceLabel.setBounds(150, 370, 80, 40);
        price = new JLabel("-");//******************价格实时更新待实现***********************
        price.setBounds(250, 370, 180, 40);

        instance = new send();

        confirm = new JToggleButton("Yes");//确定
        confirm.setBounds(250, 450, 120, 40);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                String numStr = num.getText();						//获得车牌号
                cid=numStr;
                boolean flag2=dao.isFixedUser(cid);
                String typeStr = (String) type.getSelectedItem();	//获得类型
                String timeStr = time.getText();					//获得时间
                int inoutIndex = inout.getSelectedIndex();			//获得车辆是进还是出
                flag=inoutIndex;
                sendStr = "" + inoutIndex + "+" + numStr + "+" + typeStr + "+" + timeStr; //要发送给通信机的数据
                try {
                    instance.sendStr(sendStr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(flag==1&&flag2==true) {
                	int Nowprice=dao.getPrice(cid);
                	String priceifo=Integer.toString(Nowprice);
                	price.setText("固定用户，"+priceifo);
                	f.repaint();
                }
                else if(flag==1) {
                	int Nowprice=dao.getPrice(cid);
                	String priceifo=Integer.toString(Nowprice);
                	price.setText(priceifo);
                	f.repaint();
                }
                	
            }

//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                // TODO Auto-generated method stub
//
//                String numStr = num.getText();						//获得车牌号
//                String typeStr = (String) type.getSelectedItem();	//获得类型
//                String timeStr = time.getText();					//获得时间
//                int inoutIndex = inout.getSelectedIndex();			//？？？？？？？？？？？？？？
//                sendStr = "" + inoutIndex + "+" + numStr + "+" +typeStr+ "+" + timeStr; //要发送给通信机的数据
//
//                //AbstractButton ab=(AbstractButton)arg0.getSource();
//                //if (ab.isSelected()){
//                    //System.out.println("kaishi "+sendStr);///////////////ceshi
//                    try {
//                        instance.sendStr(sendStr);
//                        instance.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    //System.out.println("send later");//////////////ceshi
//                    //confirm.setText("next one");
//                //}else{
//                    //confirm.setText("yes");
//                //}
//            }
        });//确定按钮触发的数据传送

        f.add(numLabel);
        f.add(num);
        f.add(timeLabel);
        f.add(time);
        f.add(inoutLabel);
        f.add(inout);
        f.add(type);
        f.add(typeLabel);
        f.add(price);
        f.add(priceLabel);
        f.add(confirm);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

//    public void updateUI(String str) {
//        price.setText(str);
//    }

    public static void main(String[] args) throws IOException {
        new client();
    }
}
