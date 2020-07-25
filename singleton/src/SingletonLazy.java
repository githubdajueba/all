/**
 * java 23种设计模式:
 * 单例模式:饿汉式,懒汉式单例,
 * 代理模式;工厂模式;委托模式;适配器模式;策略模式;
 * 简单工厂模式;包装模式
 */
public class SingletonLazy {
    public static void main(String[] args) {
        Singleton02 instance1 = Singleton02.getInstance();
        //Singleton singleton = new Singleton();
        Singleton02 instance2 = Singleton02.getInstance();
        System.out.println(instance1 == instance2);

    }

}

class Singleton02 {
    private Singleton02(){}
    // 懒汉式: 延迟加载(何时使用何时创建)
    //        线程/数据安全?
    static private Singleton02 singleton ;
    // 牺牲效率 提高安全
    synchronized  static public Singleton02 getInstance() {
       // 静态内不能使用 this : 由于内存加载时间有先后顺序,先加载静态,
        // 而此时this不一定存在
       // synchronized (this) {
            if (singleton == null) {
                singleton = new Singleton02();
            }
            return singleton;
        }
   // }
}