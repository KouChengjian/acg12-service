package com.acg12.learn;

/**
 * Created by Administrator on 2017/12/5.
 */
public class ArrayStack {

    private long[] a;
    private int size;   //栈数组的大小
    private int top;   //栈顶

    public ArrayStack(int maxSize) {
        this.size = maxSize;
        this.a = new long[size];
        this.top = -1;   //表示空栈
    }

    public void push(long value) {   //入栈
        if (isFull()) {
            System.out.println("栈已满!");
            return;
        }
        a[++top] = value;
    }

    public long peek() {   //返回栈顶内容，但不删除
        if (isEmpty()) {
            System.out.println("栈中没有数据");
            return 0;
        }
        return a[top];
    }


    public long pop() {   //弹出栈顶内容
        if (isEmpty()) {
            System.out.println("栈中没有数据!");
            return 0;
        }
        return a[top--];
    }

    public int size() {
        return top + 1;
    }

    /**
     * 判断是否满了
     *
     * @return
     */
    public boolean isFull() {
        return (top == size - 1);
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return (top == -1);
    }


    public void display() {
        for (int i = top; i >= 0; i--) {
            System.out.println(a[i] + " ");
        }
        System.out.println("");
    }
}
