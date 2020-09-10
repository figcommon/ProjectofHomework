import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class send {
    public static final int MY_SERVER_PORT =55555;
    public static final Charset MY_COMM_CHARSET = StandardCharsets.UTF_8;

    Socket s = new Socket("localhost", MY_SERVER_PORT);//创建针对指定地址的socket


    public send() throws IOException {
        System.out.println("前端机开始连接至通信机");
        System.out.println(s.getRemoteSocketAddress());
    }

    public void close() throws IOException {//关闭通信
        s.close();
    }

    public void sendStr(String sendStr) throws IOException {
        try (PrintWriter pw = new PrintWriter(
                new OutputStreamWriter(s.getOutputStream(), MY_COMM_CHARSET)
        );) {
            //while (true) {
                pw.println(sendStr);
                pw.flush();
            //}
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



