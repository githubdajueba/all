package serverandclient;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(8000);

        System.out.println(

                "服务已经在 8000 端口上启动");

        //暂停等待客户端发起连接

        System.out.println("等待客户端连接");

        Socket s = ss.accept();

        System.out.println("客户端已连接");

        //从插头对象，获取输入流和输出流

        InputStream in = s.getInputStream();

        OutputStream out = s.getOutputStream();

        /*

         * 通信协议，通信流程，数据格式

         *

         *     服务器端：

         *       1. 接收 "hello"

         *       2. 发送 "world"

         */

        for (int i = 0; i < 5; i++) {

            char c = (char) in.read();

            System.out.print(c);

        }



        out.write("world".getBytes());

        out.flush(); //刷出内存缓存



        s.close();//断开连接

        ss.close();//停止服务，释放端口



    }
}
