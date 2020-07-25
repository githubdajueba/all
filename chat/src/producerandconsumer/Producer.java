package producerandconsumer;

import java.util.Queue;
import java.util.Random;

public class Producer extends Thread {
    //数据容器不能自己创建,而是从外部接收一个容器对象
    private Queue<Character> q;

    //alt+shift+s, generate contructor using fiels...
    public Producer(Queue<Character> q) {
        super();
        //把从参数接收的集合对象,保存到成员变量
        this.q = q;
    }

    @Override
    public void run() {
        while (true) {
            //               'a'+  0,1,2,3....25
            char c = (char) ('a'+ new Random().nextInt(26));
            //抢指定对象的锁,多数情况下使用共享的数据对象最合适
            synchronized (q) {
                q.offer(c); //尾部添加
                System.out.println("<<< "+c);
                q.notifyAll();  //放入数据后,通知消费者取数据
            }
        }
    }
}

