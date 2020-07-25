public class MyTicket extends Thread {
    // 四个窗口 共卖100张票
    static int tickets = 100;
    // int tickets = 100; 成员变量/实例变量,堆内存

    @Override
    public void run() {
       while(true){
           if(tickets > 0){
               try {
                   // 问题: 超卖  重复卖
                   // 毫秒
                   Thread.sleep(10);
               } catch (Exception e){
                   e.printStackTrace();

               }
               System.out.println(getName() + "==" + tickets--);
           } else { //tickets<=0
               break; //设置出口
           }
       }
    }
}
