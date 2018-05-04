package com.acg12.learn.queue;

/**
 * Created by Administrator on 2017/12/5.
 */
public class RoundQueue {

    private long[] a;
    private int size;     //数组大小
    private int nItems;    //实际存储数量
    private int front;     //头
    private int rear;       //尾

    public RoundQueue(int maxSize) {
        this.size = maxSize;
        a = new long[size];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void insert(long value) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        rear = ++rear % size;
        a[rear] = value;  //尾指针满了就循环到0处，这句相当于下面注释内容
        nItems++;
    }

    public long remove() {
        if (isEmpty()) {
            System.out.println("队列为空!");
            return 0;
        }
        nItems--;
        front = front % size;
        return a[front++];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("队列为空!");
            return;
        }
        int item = front;
        for (int i = 0; i < nItems; i++) {
            System.out.println(a[item++ % size] + " ");
        }
        System.out.println("");
    }

    public long peek() {
        if (isEmpty()) {
            System.out.println("队列为空！");
            return 0;
        }
        return a[front];
    }

    public boolean isFull() {
        return (nItems == size);
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public int size() {
        return nItems;
    }
}
