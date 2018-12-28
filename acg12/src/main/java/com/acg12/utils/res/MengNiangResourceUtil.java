package com.acg12.utils.res;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: mayn
 * \* Date: 2018/6/20
 * \* Time: 11:46
 * \* Description: 萌娘百科
 * \
 */
public class MengNiangResourceUtil {

    /**
     * --------------------------------------萌娘百科资源--------------------------------------------
     */
    // 萌娘百科 搜索
    // https://m.moegirl.org/api.php?action=query&format=json&prop=pageprops%7Cpageimages&generator=prefixsearch&ppprop=displaytitle&piprop=thumbnail&pithumbsize=80&pilimit=15&redirects=&gpssearch=%E5%A4%8F%E5%A8%9C&gpsnamespace=0&gpslimit=15
    // 图片资源图片大小
    // https://commons.moegirl.org/thumb.php?f=%E8%AF%B7%E9%97%AE%E6%82%A8%E4%BB%8A%E5%A4%A9%E8%A6%81%E6%9D%A5%E7%82%B9%E5%85%94%E5%AD%90%E5%90%97%E6%BC%AB%E7%94%BB%E7%AC%AC6%E5%8D%B7%E5%B0%81%E9%9D%A2.jpg&width=300
    public static final String URL_MENGNIANBAIKE_SEARCH = "https://m.moegirl.org/api.php?action=query&format=json&prop=pageprops%7Cpageimages&generator=prefixsearch&ppprop=displaytitle&piprop=thumbnail&pithumbsize=80&pilimit=15&redirects=&gpsnamespace=0&gpslimit=15&gpssearch=";

    /**
     * 萌娘百科 版块
     */
    public static final String URL_MENGNIANBAIKE_SEARCH_INFO = "https://m.moegirl.org/";

    // 萌娘百科 搜索
    public static synchronized String getMoeGirlSearchKeyList(String title) {
        JSONArray jsonArray = new JSONArray();
        try {
            Document document = Jsoup.connect(URL_MENGNIANBAIKE_SEARCH + title).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            String content = document.body().text();
            if (content == null || content.isEmpty()) {
                return "";
            }
            System.err.println(content.toString());

            JSONObject jsonObject = new JSONObject(content);
            JSONObject query = jsonObject.getJSONObject("query");
            JSONObject pages = query.getJSONObject("pages");
            Iterator iterator = pages.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                JSONObject value = pages.getJSONObject(key);
                if (!value.isNull("thumbnail")) {
                    JSONObject thumbnai = value.getJSONObject("thumbnail");
                    String source = thumbnai.getString("source");
                    value.remove("thumbnail");
                    value.put("source", source);
                }
                jsonArray.put(value);
                System.err.println(value.toString());
            }
            return jsonArray.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // 萌娘百科 搜索详情
    public static synchronized String gettMoeGirlSearchKeyInfo(String title) {
        try {
            Document document = Jsoup.connect(URL_MENGNIANBAIKE_SEARCH_INFO + title)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            Element mw_mf_viewport = document.body().getElementById("mw-mf-viewport");
            Element mw_mf_page_center = mw_mf_viewport.getElementById("mw-mf-page-center");
            Element content = mw_mf_page_center.getElementById("content");
            Element bodyContent = content.getElementById("bodyContent");
            Element mw_content_text = bodyContent.getElementById("mw-content-text");
            Element mw_parser_output = mw_content_text.getElementsByClass("mw-parser-output").first();
            Element mf_section_0 = mw_parser_output.getElementById("mf-section-0");
//            System.err.println(mf_section_0);
            parseInfoHeader(mf_section_0);
            parseInfoBody(mw_parser_output);

            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }






    public static synchronized void parseInfoHeader(Element element) throws JSONException {
        Element infotemplatebox = element.getElementsByClass("infotemplatebox").first();
        if (infotemplatebox != null) {
            parseTemplate1_1(element);
            return;
        }
        infotemplatebox = element.select("table").first();
        if (infotemplatebox != null) {
            parseTemplate1_2(element);
            return;
        }
    }

    public static synchronized void parseInfoBody(Element element) {
        element.getElementById("mf-section-0").remove();
        Elements tabs = element.select("h2");
        for (int i = 0, total = tabs.size(); i < total; i++) {
            Element item = tabs.get(i);
            System.err.println(item.text());
            Elements mf_section = element.getElementsByClass("mf-section-" + (i + 1));
            System.err.println(mf_section.text());
        }
        System.err.println(tabs.size());
    }

    // 模板 - 1 人物模板解析
    public static synchronized void parseTemplate1_1(Element element) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonItem = new JSONObject();
        Element infotemplatebox = element.getElementsByClass("infotemplatebox").first();
        Elements elements = infotemplatebox.select("tr");
        for (int i = 0, total = elements.size(); i < total; i++) {
            Element item = elements.get(i);
//            System.err.println(item);
            if (i == 0) {
                jsonObject.put("cover", item.select("img").attr("src"));
            } else if (i > total - 3) {
                if (i == total - 1) {
                    jsonItem.put(elements.get(i - 1).text(), item.text());
                }
            } else {
                Elements th = item.select("th");
                Elements td = item.select("td");
                if (th.size() == 0 || td.size() == 0) {
                    jsonObject.put(td.size() == 0 ? th.text() : td.text(), jsonItem);
                } else {
                    jsonItem.put(th.text(), td.text());
                }
            }
        }

        System.err.println(jsonObject.toString());

        element.getElementsByClass("infoBox").remove();
        element.getElementsByClass("infotemplatebox").remove();
        elements = element.select("p");

//        System.err.println(elements.text());
    }

    // 模板 - 2 作品、名词解析
    public static synchronized void parseTemplate1_2(Element element) {
        Element infotemplatebox = element.select("table").first();
        Elements elements = infotemplatebox.select("tr");
        for (int i = 0, total = elements.size(); i < total; i++) {
            Element item = elements.get(i);
            if (i == 1) {
                System.err.println(item.select("img").attr("src"));
            } else {
                System.err.println(item.text());
            }
        }
        element.getElementsByClass("infoBox").remove();
        element.getElementsByClass("infotemplatebox").remove();
        elements = element.select("p");

        System.err.println(elements.text());
    }

    public static void main(String[] args) {
    }
}
