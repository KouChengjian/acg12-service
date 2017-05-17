package com.acg12;

import com.acg12.beans.Video;
import com.acg12.utils.HttpUtil;

import java.util.List;

/**
 * Created by kouchengjian on 2017/3/7.
 */
public class test {

    public static void main(String[] args) {
        //ReptileUtil.getHomeContent().toString();
        //System.err.println(ReptileUtil.getHomeContent().toString());

        List<Video> albumList = HttpUtil.getSearchVideo("点兔" , 1+"");
        System.err.println(albumList.size());
    }


}
