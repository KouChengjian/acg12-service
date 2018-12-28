package com.acg12.utils.res;

import com.acg12.utils.UrlEncoderUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.lang.model.util.Elements;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/12/27 20:07
 * Description: 布卡漫画
 */
public class BukaResourceUtil {

     // http://www.buka.cn/search?word=%E5%90%91%E5%B1%B1%E8%BF%9B%E5%8F%91
     // http://www.buka.cn/detail/213876

    public static synchronized void buKaSearch(String key){
        String url =  String.format("http://www.buka.cn/search?word=%s" , UrlEncoderUtil.hasUrlEncoded(key) ? key : UrlEncoderUtil.encode(key));
        try{
            OkHttpClient okHttpClient = new OkHttpClient(); // 创建OkHttpClient对象
            Request request = new Request.Builder().url(url).build(); // 创建一个请求
            Response response = okHttpClient.newCall(request).execute(); // 返回实体
            if (!response.isSuccessful()) { // 判断是否成功
                System.out.println("失败"); // 链接失败
                return;
            }
            String content = response.body().string();
            Document document = Jsoup.parse(content);
//            Elements
        }catch (Exception e){

        }
    }

    public static void main(String[] args) {
        buKaSearch("向山进发");
    }
}
