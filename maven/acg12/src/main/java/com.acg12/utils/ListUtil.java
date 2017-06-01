package com.acg12.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/5/31.
 */
public class ListUtil {

    public static <T> List<T> copyIterator(Iterator<T> iter) {
        List<T> copy = new ArrayList<T>();
        while (iter.hasNext())
            copy.add(iter.next());
        return copy;
    }

    public static <T> Iterator<T> copyList(List<T> list) {
        Iterator<T> iter = list.iterator();
        return iter;
    }

}
