package number;

public class Number1 {
    public static void main(String[] args) {
        int i = 0;
        int j;
        j = ++i + i++ + ++i + ++i + i++ + i++;
        System.out.println("i1= " + i); // 6
        System.out.println("j1= " + j); // 18

        i = 0;
        j = ++i + i++ + ++i + ++i + ++i + i++;
        System.out.println("i2= " + i); // 6
        System.out.println("j2= " + j); // 19


    }
}
