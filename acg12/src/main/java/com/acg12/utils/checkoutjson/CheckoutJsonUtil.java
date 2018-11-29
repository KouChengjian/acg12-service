package com.acg12.utils.checkoutjson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/28 16:57
 * Description:
 */
public class CheckoutJsonUtil {

    public static String stringToJson(String s) {
        MyStack<IndexStack> stack = new MyStack<IndexStack>();
        List<Integer> list = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
//            System.out.println("i = " + i);
            char c = s.charAt(i);
//            System.out.println(c);
            switch (c) {
                case '\"':
//                    System.out.println("入栈");
                    stack.push(new IndexStack(i , c));
                    sb.append(c);
                    break;
                case ':':
                    if(String.valueOf(s.charAt(i-1)).equals("\"")){
//                        System.out.println("出栈 = stack.size() = "+stack.size());
                        if (stack.size() != 0) {
                            if (stack.size() == 2) {
                                stack.pop();
                                stack.pop();
                            } else if (stack.size() > 2) {
//                                System.out.println("数据异常大于2");
                            } else if (stack.size() < 2) {
//                                System.out.println("数据异常小于2");
                            }
                        }
//                        System.out.println("出栈 = stack.size() = "+stack.size());
                    }

                    sb.append(c);
                    break;
                case ',': // 目前不能识别段落里面的英文逗号
                    int num = stack.size();
                    for (int k = 0  ; k < num ; k++){
                        IndexStack indexStack = (IndexStack)stack.pop();
//                        System.out.println("indexStack.getPosition() = "+indexStack.getPosition());
//                        System.out.println("k = "+ k);
                        if(k == 0 || k == num - 1){

                        } else {
//                            System.out.println("执行");
//                            sb.replace(indexStack.getPosition() , indexStack.getPosition() +1 ,"&quot;");
                            list.add(indexStack.getPosition());
//                            System.out.println(sb.toString());
                        }
                    }
                    sb.append(c);
                    break;
                default:
                    sb.append(c);
            }
//            System.out.println("lenght = " +sb.length());
        }
//        System.out.println("list = " +list.size());
        Collections.sort(list);
        for (int i = list.size() -1 ; i >=  0 ; i--){
//            System.out.println(list.get(i));
            sb.replace(list.get(i) , list.get(i) +1 ,"&quot;");
        }
//        System.out.println(sb.toString());
        return sb.toString();
    }
}
