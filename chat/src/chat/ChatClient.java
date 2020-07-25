package chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ChatClient {
    static BufferedReader in;
    static PrintWriter out;
    static String name;
    static Queue<String> q = new LinkedList<String>();
    static boolean inputFlag = false;//输入标志־

    public static void main(String[] args) throws Exception {
        // 192.168.14.79
        Socket s = new Socket(
                "127.0.0.1", 8000);
        in = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
        out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"));

        System.out.print("起个名字:");
        name = new Scanner(System.in).nextLine();
        out.println(name); //ע注意:必须有lnln
        out.flush();

        //开始聊天

        //接收线程
        Thread t1 = new Thread() {
            @Override
            public void run() {
                receive();
            }
        };

        t1.start();

        //打印线程
        Thread t3 = new Thread() {
            @Override
            public void run() {
                print();
            }
        };

        t3.setDaemon(true);
        t3.start();



        //输入线程
        Thread t2 = new Thread() {
            @Override
            public void run() {
                input();
            }
        };

        t2.setDaemon(true);
        t2.start();
    }

    protected static void print() {
        while (true) {
            synchronized (q) {
                while (q.isEmpty() || inputFlag) {
                    try {
                        q.wait();
                    } catch (InterruptedException e) {
                    }
                }
                String msg = q.poll();
                System.out.println(msg);
            }
        }
    }

    protected static void input() {
        System.out.println("�按回车输入聊天内容");

        while (true) {
            new Scanner(System.in).nextLine();
            inputFlag = true; //开始输入,打开信号灯

            System.out.print("输入聊天内容: ");
            String msg = new Scanner(System.in).nextLine();
            out.println(msg); //ע注意:必须有ln
            out.flush();

            inputFlag = false; //输入完成,关闭信号灯
            synchronized (q) {
                q.notifyAll(); //ͨ通知打印线程,已经输入完成,可以继续打印
                         }
        }
    }

    protected static void receive() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                //收到的数据,加入队列
                synchronized (q) {
                    q.offer(line);
                    q.notifyAll();
                }
            }
        } catch (Exception e) {
        }

        System.out.println("已经与服务器断开连接");
    }
}


