package com.acg12.learn;

import com.acg12.utils.BangumiUtil;

/**
 * Created by Administrator on 2017/12/5.
 */
public class LearnMain {

    static int[] arg = {5 , 7 , 6, 9 , 0 , 10 , 12 , 9 , 4 , 2 , 16};

    public static void main(String[] args) {
//        ArrayStack arrayStack = new ArrayStack(2);
//        arrayStack.push(1);


        sort(arg);
        for (int i = 0 ; i < arg.length ; i++){
            System.out.print(arg[i]+" ");
        }

    }

    public static void sort(int[] args) {
        int len = args.length;
        for (int i = 0, k = 0; i < len; i++, k = i) {
            // 在这一层循环中找最小
            for (int j = i + 1; j < len; j++) {
                // 如果后面的元素比前面的小，那么就交换下标，每一趟都会选择出来一个最小值的下标
                if (args[k] > args[j]) k = j;
            }

            if (i != k) {
                int tmp = args[i];
                args[i] = args[k];
                args[k] = tmp;
            }
        }
    }

    public static void sort1(int[] args) {
        //第一层循环从数组的最后往前遍历
        for (int i = args.length - 1; i > 0; --i) {
            //这里循环的上界是 i - 1，在这里体现出 “将每一趟排序选出来的最大的数从sorted中移除”
            for (int j = 0; j < i; j++) {
                //保证在相邻的两个数中比较选出最大的并且进行交换(冒泡过程)
                if (args[j] > args[j + 1]) {
                    int temp = args[j];
                    args[j] = args[j + 1];
                    args[j + 1] = temp;
                }
            }
        }
    }
}
