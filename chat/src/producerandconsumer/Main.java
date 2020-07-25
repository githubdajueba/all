package producerandconsumer;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Character> q = new LinkedList<Character>();
        //alt+shift+L, 抽取局部变量
        Producer p = new Producer(q);
        Consumer c = new Consumer(q);

        p.start();
        c.start();

        //主线程
        //模拟不操作数据,直接发出通知
        while (true) {
            synchronized (q) {
                q.notifyAll();
            }
        }
    }
}
