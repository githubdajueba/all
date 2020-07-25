public class Main {
    public static void main(String[] args) {
        // 创建多线程测试
        /**
         * 多线程: 延迟访问性
         *         随机性
         *   共同保证多线程下的数据安全
         */
        MyTicket t1 = new MyTicket();
        t1.start();
       MyTicket t2 = new MyTicket();
       t2.start();
       MyTicket t3 = new MyTicket();
       t3.start();
       MyTicket t4 = new MyTicket();
       t4.start();



    }
}
