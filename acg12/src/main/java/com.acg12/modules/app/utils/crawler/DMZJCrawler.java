package com.acg12.modules.app.utils.crawler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/7/16 18:35
 * Description:
 */
public class DMZJCrawler {

    // 动漫之家 资讯
    // http://v2.api.dmzj.com/v3/article/list/0/2/0.json?channel=Android&version=2.7.003 全部
    // http://v2.api.dmzj.com/v3/article/list/0/2/1.json?channel=Android&version=2.7.003
    // http://v2.api.dmzj.com/v3/article/list/1/2/0.json?channel=Android&version=2.7.003 动画情报
    // http://v2.api.dmzj.com/v3/article/list/2/2/0.json?channel=Android&version=2.7.003 漫画情报
    public static final String URL_DONGMANZHIJIA_NEWS = "http://v2.api.dmzj.com/v3/article/list/0/2/%s.json";

    // 获取每日快报
    public static synchronized JSONArray getNewList(String pager) {
        try {
            Document document = Jsoup.connect(String.format(URL_DONGMANZHIJIA_NEWS, pager)).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            String content = document.body().text();
            if (content == null || content.isEmpty()) {
                return null;
            }
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                jsonObject.remove("col_pic_url");
                jsonObject.remove("comment_amount");
                jsonObject.remove("author_uid");
                jsonObject.remove("is_foreign");
                jsonObject.remove("foreign_url");
                jsonObject.remove("nickname");
                jsonObject.remove("author_id");
                jsonObject.remove("foreign_url");
                jsonObject.remove("cover");
                jsonObject.remove("from_name");
                jsonObject.remove("from_url");
                String row_pic_url = jsonObject.getString("row_pic_url");
                jsonObject.remove("row_pic_url");
                jsonObject.put("pic_url", row_pic_url);
            }
            System.out.println(jsonArray.toString());
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
