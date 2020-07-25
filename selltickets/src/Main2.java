public class Main2 {
    public static void main(String[] args) {
        // thread() 中的参数,可以是一个(runnable),也可以是两个(runnable,名字)
        MyTicketRunnable r = new MyTicketRunnable();

        Thread t1 = new Thread(
                r,"1 window: ");
        t1.start();

        Thread t2 = new Thread(r,"2 window: ");
        t2.start();
        //MyTicketRunnable r3 = new MyTicketRunnable();
        Thread t3 = new Thread(r,"3 window : ");
        t3.start();
        Thread t4 = new Thread(r, "4 window: ");
        t4.start();


    }
}
