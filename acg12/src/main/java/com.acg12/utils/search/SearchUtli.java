package com.acg12.utils.search;

import com.acg12.conf.UrlConstant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Administrator on 2018/3/14.
 */
public class SearchUtli {

    // 搜索关键字获取列表
    public static synchronized void getSubjectSearchList(String key , int type){
        String url = String.format("https://api.bgm.tv/search/subject/%s?type=%d&responseGroup=large&max_results=20" , key , type);
        try {
            Document document = Jsoup.connect(url).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            String content = document.toString();
            System.err.println(document.body().text());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 关键字详情
    public static synchronized void getSubjectInfo(int subjectid ){
        String url = String.format("https://api.bgm.tv/subject/%d?responseGroup=large" , subjectid );
        try {
            Document document = Jsoup.connect(url).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            String content = document.toString();
            System.err.println(document.body().text());



        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
