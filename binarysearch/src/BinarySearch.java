import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class BinarySearch {
    /**
     * 二分法查找:
     *   将集合中的元素按从小到大顺序排列
     * @param args
     */

        public static void main(String[] args) {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        ArrayList<Integer> a = new ArrayList<>();
        while(true) {
            new Scanner(System.in).nextLine();

            int n = new Random().nextInt(100);

           // new Scanner(System.in).nextInt();
            System.out.println(n);
            int index = Collections.binarySearch(a,n);
            if(index<0) {
                index = (-index) - 1;
            }
            a.add(index,n);
            System.out.println(a);

            //for(int i=0;i<a.size();i++) {

            //System.out.println(a.get(i));


            //}
        }

    }

    }

