package innerclass;

public class InnerClass1 {
    public static void main(String[] args) {
        //静态内部类可以独立创建实例
        A.Inner1 i1 = new A.Inner1();
        System.out.println(i1);

        //非静态内部类需要在外部实例中创建实例
        A a = new A();
        A.Inner2 i2 = a.new Inner2();
        System.out.println(i2);
    }
}

class A {
    static class Inner1 {
    }

    class Inner2 {
    }
}
