package com.acg12.utils.stack;

/**
 * Created by Administrator on 2018/5/14.
 */
public interface IStack<E> {
    //1.判断空栈
    public boolean isEmpty();
    //2.判断栈满
    public boolean isMax();
    //3.入栈
    public boolean push(E e);
    //4.出栈
    public E pop();
    //5.返回栈顶
    public E peek();
    //6.返回元素在栈中的位置
    public int getIndex(E e);
    //7.返回栈的实际长度
    public int size();
    //8.返回栈容量
    public int getStackSize();
    //9.打印栈
    public void display();
}
