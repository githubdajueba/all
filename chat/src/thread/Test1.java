package thread;

public class Test1 {
    public static void main(String[] args) {
        new T1().start();
        System.out.println("main==");

    }

}
    class T1 extends Thread{
    @Override
    public void run() {
        System.out.println("T1==");
    }
}
