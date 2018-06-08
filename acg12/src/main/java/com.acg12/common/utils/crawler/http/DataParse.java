package com.acg12.common.utils.crawler.http;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Administrator on 2018/1/22.
 */
public class DataParse {

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

}
