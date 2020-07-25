package rabbit;

public class R1 {
    /**
     * 题目：有一对兔子，从出生后第3个月起每个月都生一对兔子，
     * 小兔子长到第三个月后每个月又生一对兔子，
     * 假如兔子都不死，问每个月的兔子对数为多少？
     *
     *      月份       1 2 3 4 5 6 7…
     * 对应的小兔子数是 1 0 1 1 2 3 5…
     * 对应的中兔子数是 0 1 0 1 1 2 3...
     * 对应的老兔子数是 0 0 1 1 2 3 5...
     * 所以兔子总数是   1 1 2 3 5 8 13...
     * 从第三个数开始，该数是前两个数之和
     *  斐波那契数
     *
     *
     */
    public static void main(String[] args) {
        A.ad();//调用方法
    }
}
class A{
    public static void ad() {
        int[] a=new int [30];//定义一个数组用来存放累加的值
        for (int i = 0; i <30; i++) {//for语句循环30次
            a[0]=1;//给数组a[0]a[1]赋初值
            a[1]=1;
            if (i>=2) {//当i等于2时开始做累加
                a[i]=a[i-1]+a[i-2];//把a[i-1]+a[i-2]的值赋给a[i]以实现后面一个数字加前面一个数字
            }
            int z=i+1;//统计月数的，因为是从0开始的，所以要加1，因为月份没有0月
            System.out.println("第"+z+"个月的兔子总对数:"+a[i]);//输出打印
        }
    }
}