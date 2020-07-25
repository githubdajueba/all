package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureAndCallable {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        //Future实例是取餐条
        Future<Double> future = pool.submit(new C1());
        //主线程继续执行其他运算
        System.out.println("1.");
        System.out.println("2.");
        System.out.println("3.");
        //直到需要任务结果时,可以暂停等待任务结束
        Double r = future.get(); //用取餐条取餐,获取Callable任务的计算结果
        System.out.println(r);

        pool.shutdown(); //销毁线程池中所有的线程
    }
    /*
     * Callable与Runnable区别:
     * 1. 泛型
     * 2. 返回值
     * 3. 异常
     */
    static class C1 implements Callable<Double> {
        @Override
        public Double call() throws Exception {
            Thread.sleep(8000);
            double d = Math.random();
            return d;
        }
    }
}
