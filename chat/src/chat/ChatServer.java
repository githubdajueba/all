package chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

/** 中文乱码问题:
 * 修改idea配置文件
 *
 * IDEA安装目录，将2个idea64.exe.vmoptions、idea.exe.vmoptions文件，
 * 在最后一行追加：   -Dfile.encoding=UTF-8
 */
public class ChatServer {
    //用来收集通信线程对象
    static ArrayList<TongXinThread> list = new ArrayList<TongXinThread>();

    //启动服务线程
    static class ServerThread extends Thread {
        @Override
        public void run() {
            try {
                System.out.println(
                        "聊天室服务器正在启动...");
                ServerSocket ss = new ServerSocket(8000);
                System.out.println("服务已经在8000端口上启动");
                while (true) {
                    Socket s = ss.accept();
                    TongXinThread t = new TongXinThread(s);
                    t.start();
                }
            } catch (Exception e) {
                System.out.println("服务无法在 8000 端口上启动，或服务已经停止");
                e.printStackTrace();
            }
        }
    }

    //ͨ通信线程
    static class TongXinThread extends Thread {
        private Socket s;
        BufferedReader in;
        PrintWriter out;
        private String name;
        private String ip;

        public TongXinThread(Socket s) {
            this.s = s;
        }

        public void send(String msg) {
            out.println(msg); //ע注意:必须有ln
            out.flush();
        }

        public void sendAll(String msg) {
            synchronized (list) {
                for (TongXinThread t : list) {
                    t.send(msg);
                }
            }
        }

        /*
         * 1. UTF-8
         * 2. 换行
         */
        @Override
        public void run() {
            try {
                //获取客户端的ip地址
                this.ip = ((InetSocketAddress)s.getRemoteSocketAddress()).getHostString();

                this.in = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
                this.out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"));
                //1. 接收客户端昵称
                this.name = in.readLine();
                //2. 发送欢迎消息
                send(name+"," +
                        " 欢迎进入聊天室!");

                //设置超时时间
                s.setSoTimeout(5000);

                //3. 把当前通信线程加入集合list
                synchronized (list) {
                    list.add(this);
                }
                //4. 群发上线消息
                sendAll(name+"进入了聊天室,在线人数: "+list.size());
                //开始聊天
                // 超时的计数变量
                int count = 0;
                String line;
                while (true) {
                    try {
                        // 超时会出现SocketTimeoutException
                        line = in.readLine();
                        count = 0;
                    } catch (SocketTimeoutException e) {
                        count++;
                        if (count == 4) {
                            send("*** 您已经被踢出了聊天室***");
                            s.close();//断开连接
                            break;
                        }
                        send("*** 请积极参与连天("+count+"/3) ***");
                        continue;
                    }

                    if (line == null) {
                        break;
                    }

                    sendAll(name+"说: "+line);
                    System.out.println(ip+" - "+name+"说: "+line);
                }
                //null
            } catch (Exception e) {
                //异常
            }
            //移除当前通信线程
            synchronized (list) {
                list.remove(this);
            }
            //群发离线消息
            sendAll(name+"离开了聊天室,在线 "+list.size());
        }
    }

    public static void main(String[] args) {
        ServerThread s = new ServerThread();
        s.start();
    }
}

