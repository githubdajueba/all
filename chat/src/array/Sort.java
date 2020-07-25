package array;

import java.util.Arrays;
import java.util.Random;

public class Sort {
    public static void main(String[] args) {
        /**
         * 冒泡排序
         */
        int[] array = new int[10];
        for(int i=0;i<array.length;i++){
             array[i] = new Random().nextInt(100); //[0,100)
             // Math.random() // [0,1)


        }
        System.out.println(Arrays.toString(array));
        sort(array);
    }

    private static void sort(int[] array) {
       // [70, 70, 69, 38, 4, 19, 94, 97, 52, 71]
       for(int i= 0 ;i<array.length-1;i++){
           for(int j=i+1;j<array.length;j++){
               if(array[i] > array[j]) {
                   int t = array[i];
                   array[i] = array[j];
                   array[j] = t;
               }
           }
           System.out.println(Arrays.toString(array));

       }
        System.out.println(Arrays.toString(array));

    }
}
