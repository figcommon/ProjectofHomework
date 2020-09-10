import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import dao.ParkingInfoDAO;

public class connect {
    private JFrame f;
    private JList<String> list;
    private JButton start, finish;
    private DefaultListModel<String> listModel;
    private JScrollPane panel;
    public static final Charset MY_COMM_CHARSET= StandardCharsets.UTF_8;
    private receive instance;//创建Receiver类的实例

    public connect() throws IOException {
        Toolkit toolkit=Toolkit.getDefaultToolkit();

        f = new JFrame("通讯机");
        f.setSize(400, 200);
        f.setLocation(200, 200);
        f.setLayout(null);

        list = new JList<>();
        listModel = new DefaultListModel<>();

        panel = new JScrollPane(list);
        panel.setBounds(100, 30, 400, 300);

        //
        //=================================================================
        start = new JButton("启动");
        start.setBounds(150, 100, 80, 40);
        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    System.out.println("开始接收");
                    instance = new receive();
                    String r=instance.getLine();
                    System.out.println(r);
                    listModel.addElement(r);
                    list.setModel(listModel);
                    toolkit.beep();
                    System.out.println("传入数据："+r);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });//开始按钮触发的事件

        finish = new JButton("停止");
        finish.setBounds(350, 370, 80, 40);
        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    instance.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });//关闭出发的事件
        //==================================================================
        f.add(start);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new connect();
    }
}
