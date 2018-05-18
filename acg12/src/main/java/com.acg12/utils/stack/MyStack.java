package com.acg12.utils.stack;

/**
 * Created by Administrator on 2018/5/14.
 */
public class MyStack<E> implements IStack<E>{

    private Object[] data = null; //数据域
    private int top = -1;           //栈顶指针初始化为-1
    private int maxSize = 0;      //栈最大容量

    //默认设置栈容量为10
    public MyStack(){
        this(10);
    }

    public MyStack(int initialSize){
        if(initialSize >= 0){
            this.data = new Object[initialSize]; //初始化数组
            this.maxSize = initialSize; //设置栈最大容量
            this.top = -1;
        }
    }

    public boolean isEmpty() {
        return top == -1 ? true : false; //根据栈顶值判断,如果栈顶指针没有更新，则为空栈
    }

    public boolean isMax() {
        return top >= maxSize-1 ? true : false; //根据栈顶值判断，如果栈顶指针大于最大容量，则为满栈
    }

    public boolean push(E e) {
        if(isMax()){
            System.err.println("对不起,栈已满,无法入栈");
            return false;
        }
        top ++; //更新栈顶下标
        data[top] = e; //将元素添加到表中
//        System.out.println("添加" + e + "成功");
        return true;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        if(isEmpty()){
            System.err.println("对不起，目前是空栈，没有元素可以出栈");
            return null;
        }
        E e = (E) data[top]; //返回当前的栈顶元素
        top--; //更新栈顶
        return e;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if(isEmpty()){
            System.err.println("对不起，目前是空栈，无法返回栈顶元素");
            return null;
        }
        return (E) data[top]; //返回栈顶元素
    }

    public int getIndex(E e) {
        //根据栈顶和栈底(-1)遍历栈
        while(top != -1){
            //peek()返回当前栈顶
            if(peek().equals(e)){
                return top;
            }
            top --;
        }

        return -1;
    }

    public int size() {
        return this.top+1; //栈顶值+1，为栈元素的实际个数
    }

    public int getStackSize() {
        return this.maxSize; //返回栈实际长度
    }

    public void display() {
        //根据栈顶和栈底(-1)遍历
        while(top != -1){
            System.out.println(top);
            top --;
        }
    }

//    public static void main(String[] args) {
//        MyStack<Integer> stack = new MyStack<Integer>();
//        //入栈
//        for (int i = 0; i < 10; i++) {
//            stack.push(i);
//        }
        //出栈
//        stack.pop();
        //返回栈顶
//        System.out.println(stack.peek());
        //求长
//        System.out.println(stack.size());

//    }

}
