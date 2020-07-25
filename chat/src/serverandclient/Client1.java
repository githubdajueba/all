package serverandclient;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) throws Exception {

        Socket s = new Socket("127.0.0.1", 8000);

        InputStream in = s.getInputStream();

        OutputStream out = s.getOutputStream();

        /* 客户端：

         *    1. 发送 "hello"

         *    2. 接收 "world"

         */

        out.write("hello".getBytes());

        out.flush(); //刷出内存缓存

        for (int i = 0; i < 5; i++) {

            char c = (char) in.read();

            System.out.print(c);

        }

        s.close();

    }
}
