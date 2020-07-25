package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService pool;

        //pool = Executors.newFixedThreadPool(5); //固定数量线程池
        //pool = Executors.newCachedThreadPool(); //足够多,有空闲线程用空闲线程,否则新建线程
        pool = Executors.newSingleThreadExecutor();//单线程

        for (int i = 1; i <= 1000; i++) {
            R1 r = new R1(i);
            pool.execute(r); //把任务对象丢进线程池
        }
    }

    //任务类
    static class R1 implements Runnable {
        int id; //任务编号
        // alt+shift+s, generate constructor using fiels...
        public R1(int id) {
            super();
            this.id = id;
        }

        @Override
        public void run() {
            String n = Thread.currentThread().getName();//获得当前线程名
            System.out.println(n+" --- "+id);
        }
    }
}
