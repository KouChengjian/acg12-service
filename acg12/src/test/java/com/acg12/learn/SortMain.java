package com.acg12.learn;

/**
 * Created by Administrator on 2017/12/22.
 */
public class SortMain {
    int[] src = new int[]{1, 2, 50, 20, 11, 14, 19, 2, 7, 9, 6, 10, 25, 45, 49, 6};

    public static void main(String[] args) {

    }

    // 冒泡排序1
    public static void bubbleSort(int[] array) {
        long start = System.nanoTime();
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1000.0 + "ms");
    }

    // 冒泡排序1
    public static void bubbleSort2(int[] array) {
        long start = System.nanoTime();
        int len = array.length;
        int i = len - 1;
        while (i > 0) {
            int pos = 0;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    pos = j;
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
            i = pos;
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1000.0 + "ms");
    }
}
