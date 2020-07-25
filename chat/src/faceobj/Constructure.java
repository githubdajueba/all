package faceobj;

public class Constructure {
    public static void main(String[] args) {
        Son son = new Son("s");
        System.out.println("=============");
         Father father = new Father("fa");
    }
}
class Father{
    static{
        System
                .out.println("father static");
    }

    Father(){
        System.out.println("father constructure  no param");
    }
    Father(String name){
        System.out.println("father constructure");
    }
    {
        System.out.println("father method");
    }
}
class Son extends  Father{
    static {
        System.out.println("son static");
    }
   void  f(){
       System.out.println("sf()");
   }
    Son (){
        System.out.println("son constructure no param");
    }
    Son (String name){
        System.out.println("son constructure");
    }
    {
        f();

        System.out.println("son method");
    }
}
