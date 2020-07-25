package thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ThreadInterrupt {
    public static void main(String[] args) {
        T1 t1 = new T1();
        t1.start();

        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("按回车捅醒t1线程");
                new Scanner(System.in).nextLine();
                t1.interrupt();//t2线程中,打断t1线程(捅醒t1线程)
            }
        };

        //t2线程设置成后台线程
        //必须在线程启动之前设置
        t2.setDaemon(true);

        t2.start();



    }

    static class T1 extends Thread {
        @Override
        public void run() {
            //时间格式工具
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            for(int i=0; i<5; i++) {
                //注意: java.util包,不是java.sql包
                Date d = new Date();//表示日期时间的对象,无参构造表示系统当前时间
                String s = sdf.format(d);//把日期格式化成指定格式的字符串
                System.out.println(s);
                try {
                    Thread.sleep(1000);//暂停状态被打断,会出现"打断异常"
                } catch (InterruptedException e) {
                    System.out.println("谁TMD捅醒了老子");
                    break;
                }
            }
        }
    }
}
