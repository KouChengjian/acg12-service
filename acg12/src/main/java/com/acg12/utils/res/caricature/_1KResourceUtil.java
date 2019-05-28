package com.acg12.utils.res.caricature;

import com.acg12.utils.UrlEncoderUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/12/27 16:30
 * Description:
 */
public class _1KResourceUtil {

    public static synchronized void searchKey(String key) {
        String url =  String.format("http://en.1kkk.com/search?title=%s&language=1" , UrlEncoderUtil.hasUrlEncoded(key) ? key : UrlEncoderUtil.encode(key));
//        url ="http://en.1kkk.com/search?title=%E5%90%91%E5%B1%B1%E8%BF%9B%E5%8F%91&language=1";
        try {

//            Document document = Jsoup
//                    .connect(url).ignoreContentType(true)
//                    .data("jquery", "java").userAgent("Mozilla")
//                    .cookie("auth", "token").timeout(50000).get();
//            String content = document.toString();
//            System.out.printf("content = "+UrlEncoderUtil.replacer(new StringBuffer(content)));

            OkHttpClient okHttpClient = new OkHttpClient(); // 创建OkHttpClient对象
            Request request = new Request.Builder().url(url).build(); // 创建一个请求
            Response response = okHttpClient.newCall(request).execute(); // 返回实体
            if (!response.isSuccessful()) { // 判断是否成功
                System.out.println("失败"); // 链接失败
                return;

            }
            /**获取返回的数据，可通过response.body().string()获取，默认返回的是utf-8格式；
             * string()适用于获取小数据信息，如果返回的数据超过1M，建议使用stream()获取返回的数据，
             * 因为string() 方法会将整个文档加载到内存中。*/
            String content = response.body().string();
            Document document = Jsoup.parse(content);
            System.out.println(document.toString()); // 打印数据
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("e = "+e.toString());
        }
    }

    public static void main(String[] args) {
        searchKey("火影");
    }

}
