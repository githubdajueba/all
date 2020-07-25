public class MyTicketRunnable implements  Runnable {
    /**
     *  synchronized 同步锁:
     *      同步方法:
     *      同步代码块:  synchronized(this){}
     */
    static int tickets = 100;
    @Override
     public void run() {
        while(true) {
            //  synchronized 同步代码快 ,把有问题的代码包起来,实现同步访问
            // 对象 : 必须是多个线程使用的是同一个对象
            synchronized (this) {
                if(tickets <= 0){
                    break;
                }
                if (tickets > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + tickets--);
              }
//                else {
//                    break;
//                }
            }
        }
    }
}
