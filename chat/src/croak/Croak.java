package croak;

import java.util.Scanner;

public class Croak {
    /**
     *给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 “croak” ）的组合。
     * 由于同一时间可以有多只青蛙呱呱作响，所以 croakOfFrogs 中会混合多个 “croak” 。
     * 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
     *
     * 注意：要想发出蛙鸣 “croak”，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。
     * 如果没有输出全部五个字母，那么它就不会发出声音。
     *
     * 如果字符串 croakOfFrogs 不是由若干有效的 “croak” 字符混合而成，请返回 -1 。
     *
     * 示例 1：
     * 输入：croakOfFrogs = "croakcroak"
     * 输出：1
     * 解释：一只青蛙 “呱呱” 两次
     *
     * 示例 2：
     * 输入：croakOfFrogs = "crcoakroak"
     * 输出：2
     * 解释：最少需要两只青蛙，“呱呱” 声用黑体标注
     * 第一只青蛙 "crcoakroak"
     * 第二只青蛙 "crcoakroak"
     *
     * 示例 3：
     * 输入：croakOfFrogs = "croakcrook"
     * 输出：-1
     * 解释：给出的字符串不是 "croak" 的有效组合。
     *
     * 示例 4：
     * 输入：croakOfFrogs = "croakcroa"
     * 输出：-1
     *
     * 提示：
     * 1 <= croakOfFrogs.length <= 10^5
     * 字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k'
     */
    public static void main(String[] args) {
        while(true) {
            String s = new Scanner(System.in).nextLine();
            int count = f(s);
            System.out.println(count);
        }
    }

    private static int f(String s) {
        // 返回的是下标值
        //int i = s.indexOf("croak");
        //s.charAt(2) 指定下标位置
        int c = 0,r = 0,o = 0,a = 0,k=0,count=0;
        char[] ch = s.toCharArray();
        System.out.println(ch);
        for(int i = 0;i<ch.length;i++){
            //ch[i];
//            System.out.println(ch1);
//            if(ch[i]=='c'){
//                c++;
//            } else if(ch[i]=='r') r++;
//            else if(ch[i]=='o') o++;
//            else if(ch[i]=='a') a++;
            // s.charAt(i);
            if(s.charAt(i)=='c') c++;
            else if(s.charAt(i)=='r') r++;
            else if(s.charAt(i)=='o') o++;
            else if(s.charAt(i)=='a') a++;
            else
               // if(ch[i]=='k')
                    k++;
           // else return -1;
            // 次序不对
            if(!(c>=r&&r>=o&&o>=a&&a>=k)) return -1;
            if(i == ch.length-1 && !(c==r&&r==o&&o==a&&a==k))
                return -1; //最后不相等
            count = // count >c?count:(c>r?c:(r>o?o:(o>a?o:(a>k?a:k))));
                    Math.max(count,Math.max(c,Math.max(r,Math.max(o,Math.max(a,k)))));

            if(c>=1&&r>=1&&o>=1&&a>=1&&k>=1) {
                c--;
                r--;
                o--;
                a--;
                k--;
            }







        }


        return count;
    }
}
