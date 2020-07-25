public class SingletonHungry {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        //Singleton singleton = new Singleton();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);

    }

}
class Singleton {
    private Singleton(){}
    static private Singleton singleton =
            new Singleton();
    static public Singleton getInstance(){
        return singleton;
    }
}