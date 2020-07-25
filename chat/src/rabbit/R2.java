package rabbit;

import java.util.Scanner;

public class R2 {
    /**
     * 题目：有一对兔子，从出生后第4个月起每个月都生一对兔子，
     * 假如兔子都不死，问第十个月兔子总数？
     *
     * 月份       1  2  3  4  5   6   7…
     * 兔子总数   2  2  2  6  10  14  18...
     * 对数      1  1  1  3   5   7  9...
     */
    public static void main(String[] args) {
        while(true) {
            int n = new Scanner(System.in).nextInt();
            int count = f(n);
            System.out.println(count);
        }
    }


    public static int f(int n) {
        if(n <= 0){

            return 0 ;
        }
        if(n >= 1 && n < 4){
            return 2;
        }

        if(n ==4 ){
            return 4;
        }
        if(n < 8){
            return (n-4+1)*4+2;
        }
        return  (n-8+1)*4*2+(n-4+1)*4+2;
    }
}