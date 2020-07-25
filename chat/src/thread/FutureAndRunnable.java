package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureAndRunnable {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        //任务丢进线程池,得到一个"取餐条"
        Future<?> future = pool.submit(new R1());
        System.out.println("1.");
        System.out.println("2.");
        System.out.println("3.");
        System.out.println("4.");
        System.out.println("在此等待R1任务结束后,再继续执行");
        future.get();//暂停,等待R1任务结束,对于Runnable任务是得不到任何计算结果的
        System.out.println("R1任务已结束,当前线程继续");
    }

    //任务类
    static class R1 implements Runnable {
        @Override
        public void run() {
            System.out.println("--耗时任务开始-------------");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            System.out.println("--任务结束-------------");
        }
    }
}
