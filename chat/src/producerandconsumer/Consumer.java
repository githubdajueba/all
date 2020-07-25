package producerandconsumer;

import java.util.Queue;

public class Consumer extends  Thread {
    private Queue<Character> q;

    public Consumer(Queue<Character> q) {
        super();
        this.q = q;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (q) {
                while (q.isEmpty()) {
                    //没有数据,要暂停等待数据
                    try {
                        q.wait();
                    } catch (InterruptedException e) {
                    }
                }
                //从头部移出数据
                Character c = q.poll();
                System.out.println(">>>> "+c);
            }
        }
    }
}
