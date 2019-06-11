package com.acg12.utils.res.caricature;

import com.acg12.entity.dto.Acg12CaricatureChaptersDto;
import com.acg12.entity.dto.Acg12CaricatureChaptersPageDto;
import com.acg12.entity.dto.Acg12CaricatureDto;
import com.acg12.entity.dto.Acg12CaricatureTagDto;
import com.acg12.utils.JsonParse;
import com.acg12.utils.UrlEncoderUtil;
import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/12/28 16:27
 * Description: 酷克漫画
 */
public class KukeResourceUtil {

    /**
     *
     */

    final static String sign = "package_name=com.vjson.anime&version_code=87&version_name=1.0.8.7&channel=coolapk&sign=dcf692dc1d4cead44ce1d5d1b9409e26&platform=android";

    // http://api.cookacg.com/comics?q[name_cont]=小埋&page=1
    // http://api.cookacg.com/comics/1?package_name=com.vjson.anime&version_code=87&version_name=1.0.8.7&channel=coolapk&sign=dcf692dc1d4cead44ce1d5d1b9409e26&platform=android
    // http://api.cookacg.com/comics/1/2?package_name=com.vjson.anime&version_code=87&version_name=1.0.8.7&channel=coolapk&sign=dcf692dc1d4cead44ce1d5d1b9409e26&platform=android

    public static synchronized List<Acg12CaricatureDto> kukeSearch(String key) {
        String url = String.format("http://api.cookacg.com/comics?q[name_cont]=%s&page=1&", UrlEncoderUtil.hasUrlEncoded(key) ? key : UrlEncoderUtil.encode(key)) + sign;
//        System.out.println(url);
        try {
            OkHttpClient okHttpClient = new OkHttpClient(); // 创建OkHttpClient对象
            Request request = new Request.Builder().url(url).build(); // 创建一个请求
            Response response = okHttpClient.newCall(request).execute(); // 返回实体
            if (!response.isSuccessful()) { // 判断是否成功
                System.out.println("失败"); // 链接失败
                return null;
            }
            String content = response.body().string();
            System.out.println(content);
            JSONObject object = new JSONObject(content);
            JSONArray entries = JsonParse.getJSONArray(object, "entries");
            List<Acg12CaricatureDto> caricatureDtoList = new ArrayList<>();
            for (int i = 0; i < entries.length(); i++) {
                JSONObject item = JsonParse.getJSONObject(entries, i);
//                System.out.println(item.toString());
                Acg12CaricatureDto caricatureDto = new Acg12CaricatureDto();
                caricatureDto.setComicId(JsonParse.getInt(item, "id"));
                caricatureDto.setType(1);
                caricatureDto.setCover(JsonParse.getString(item, "cover"));
                caricatureDto.setTitle(JsonParse.getString(item, "name"));
                caricatureDtoList.add(caricatureDto);
            }
            System.out.println(JSON.toJSON(caricatureDtoList));
            return caricatureDtoList;
        } catch (Exception e) {
//            System.out.println(e.toString());
        }
        return null;
    }

    public static synchronized Acg12CaricatureDto kukeCaricatureInfo(int id) {
        String url = "http://api.cookacg.com/comics/" + id + "?" + sign;
        try {
            OkHttpClient okHttpClient = new OkHttpClient(); // 创建OkHttpClient对象
            Request request = new Request.Builder().url(url).build(); // 创建一个请求
            Response response = okHttpClient.newCall(request).execute(); // 返回实体
            if (!response.isSuccessful()) { // 判断是否成功
                System.out.println("失败"); // 链接失败
                return null;
            }
            String content = response.body().string();
            System.out.println(content);
            JSONObject object = new JSONObject(content);
            Acg12CaricatureDto caricatureDto = new Acg12CaricatureDto();
            caricatureDto.setComicId(JsonParse.getInt(object, "id"));
            caricatureDto.setType(1);
            caricatureDto.setCover(JsonParse.getString(object, "cover"));
            caricatureDto.setTitle(JsonParse.getString(object, "name"));

            JSONArray entries = JsonParse.getJSONArray(object, "chapters");
            List<Acg12CaricatureChaptersDto> chapterList = new ArrayList<>();
            for (int i = 0; i < entries.length(); i++) {
                JSONObject item = JsonParse.getJSONObject(entries, i);
//                System.out.println(item.toString());
                Acg12CaricatureChaptersDto chaptersDto = new Acg12CaricatureChaptersDto();
                chaptersDto.setComicId(JsonParse.getInt(item, "comic_id"));
                chaptersDto.setIndex(JsonParse.getInt(item, "index"));
                chaptersDto.setTitle(JsonParse.getString(item, "name"));
                chapterList.add(chaptersDto);
            }
            caricatureDto.setChaptersList(chapterList);
            System.out.println(JSON.toJSON(caricatureDto));
            return caricatureDto;
        } catch (Exception e) {
//            System.out.println(e.toString());
        }
        return null;
    }

    public static synchronized Acg12CaricatureChaptersDto kukeCaricatureChapters(int id, int index) {
        String url = "http://api.cookacg.com/comics/" + id + "/" + index + "?" + sign;
        try {
            OkHttpClient okHttpClient = new OkHttpClient(); // 创建OkHttpClient对象
            Request request = new Request.Builder().url(url).build(); // 创建一个请求
            Response response = okHttpClient.newCall(request).execute(); // 返回实体
            if (!response.isSuccessful()) { // 判断是否成功
                System.out.println("失败"); // 链接失败
                return null;
            }
            String content = response.body().string();
            System.out.println(content);
            JSONObject object = new JSONObject(content);
            Acg12CaricatureChaptersDto caricatureDto = new Acg12CaricatureChaptersDto();
            caricatureDto.setComicId(JsonParse.getInt(object, "comic_id"));
            caricatureDto.setTitle(JsonParse.getString(object, "name"));
            caricatureDto.setIndex(JsonParse.getInt(object, "index"));

            JSONArray entries = JsonParse.getJSONArray(object, "pages");
            List<Acg12CaricatureChaptersPageDto> chapterList = new ArrayList<>();
            for (int i = 0; i < entries.length(); i++) {
                JSONObject item = JsonParse.getJSONObject(entries, i);
//                System.out.println(item.toString());
                Acg12CaricatureChaptersPageDto chaptersDto = new Acg12CaricatureChaptersPageDto();
                chaptersDto.setUrl(JsonParse.getString(item, "track_url"));
                chaptersDto.setSort(i);
                chapterList.add(chaptersDto);
            }
            caricatureDto.setPags(chapterList);
            System.out.println(JSON.toJSON(caricatureDto));
            return caricatureDto;
        } catch (Exception e) {
//            System.out.println(e.toString());
        }
        return null;
    }

    public static synchronized List<Acg12CaricatureTagDto> kukeTagList() {
        List<Acg12CaricatureTagDto> list = new ArrayList<>();
//        list.add(new Acg12CaricatureTagDto(1, "竞技", kukeTagTypeList(1, 1, "竞技", 6)));
        list.add(new Acg12CaricatureTagDto(2, "热血", kukeTagTypeList(2, 1, "热血", 6)));
//        list.add(new Acg12CaricatureTagDto(3, "高清单行", kukeTagTypeList(3, 1, "高清单行", 6)));
//        list.add(new Acg12CaricatureTagDto(4, "职场", kukeTagTypeList(4, 1, "职场", 6)));
        list.add(new Acg12CaricatureTagDto(5, "冒险", kukeTagTypeList(5, 1, "冒险", 6)));
        list.add(new Acg12CaricatureTagDto(6, "魔法", kukeTagTypeList(6, 1, "魔法", 6)));
        list.add(new Acg12CaricatureTagDto(7, "萌系", kukeTagTypeList(7, 1, "萌系", 6)));
//        list.add(new Acg12CaricatureTagDto(8, "生活", kukeTagTypeList(8, 1, "生活", 6)));
//        list.add(new Acg12CaricatureTagDto(9, "爱情", kukeTagTypeList(9, 1, "爱情", 6)));

        list.add(new Acg12CaricatureTagDto(10, "校园", kukeTagTypeList(10, 1, "校园", 6)));
//        list.add(new Acg12CaricatureTagDto(11, "神鬼", kukeTagTypeList(11, 1, "神鬼", 6)));
        list.add(new Acg12CaricatureTagDto(12, "悬疑", kukeTagTypeList(12, 1, "悬疑", 6)));
        list.add(new Acg12CaricatureTagDto(13, "治愈", kukeTagTypeList(13, 1, "治愈", 6)));
        list.add(new Acg12CaricatureTagDto(15, "耽美", kukeTagTypeList(15, 1, "耽美", 6)));
        list.add(new Acg12CaricatureTagDto(16, "科幻", kukeTagTypeList(16, 1, "科幻", 6)));
//        list.add(new Acg12CaricatureTagDto(17, "伪娘", kukeTagTypeList(17, 1, "伪娘", 6)));
//        list.add(new Acg12CaricatureTagDto(18, "后宫", kukeTagTypeList(18, 1, "后宫", 6)));

//        list.add(new Acg12CaricatureTagDto(22, "历史", kukeTagTypeList(22, 1, "历史", 6)));
//        list.add(new Acg12CaricatureTagDto(23, "机战", kukeTagTypeList(23, 1, "机战", 6)));
//        list.add(new Acg12CaricatureTagDto(24, "美食", kukeTagTypeList(24, 1, "美食", 6)));
//        list.add(new Acg12CaricatureTagDto(25, "百合", kukeTagTypeList(25, 1, "百合", 6)));
//        list.add(new Acg12CaricatureTagDto(26, "格斗", kukeTagTypeList(26, 1, "格斗", 6)));
//        list.add(new Acg12CaricatureTagDto(28, "战争", kukeTagTypeList(28, 1, "战争", 6)));

        list.add(new Acg12CaricatureTagDto(30, "搞笑", kukeTagTypeList(30, 1, "搞笑", 6)));
//        list.add(new Acg12CaricatureTagDto(31, "四格", kukeTagTypeList(31, 1, "四格", 6)));
//        list.add(new Acg12CaricatureTagDto(33, "励志", kukeTagTypeList(33, 1, "励志", 6)));
//        list.add(new Acg12CaricatureTagDto(38, "恐怖", kukeTagTypeList(38, 1, "恐怖", 6)));
//        list.add(new Acg12CaricatureTagDto(39, "武侠", kukeTagTypeList(39, 1, "武侠", 6)));

//        list.add(new Acg12CaricatureTagDto(40, "奇幻", kukeTagTypeList(40, 1, "奇幻", 6)));
//        list.add(new Acg12CaricatureTagDto(41, "魔幻", kukeTagTypeList(41, 1, "魔幻", 6)));
        return list;
    }

    // 热血2 冒险5 魔幻41 神鬼11 搞笑30 萌系7 治愈 校园10 爱情9 科幻16 魔法6 格斗26 武侠39 机战23 战争28 竞技1
    // 生活8 励志33 历史22 伪娘17 耽美15 百合25 后宫18 治愈13 美食24 悬疑12 恐怖38 四格31
    public static synchronized List<Acg12CaricatureDto> kukeTagTypeList(int id, int page, String name, int limit) {
        List<Acg12CaricatureDto> list = new ArrayList<>();
        String url = String.format("http://api.cookacg.com/comics?q[tags_id_eq]=%d&page=%d&name=%s&", id, page, name) + sign;
        try {
            OkHttpClient okHttpClient = new OkHttpClient(); // 创建OkHttpClient对象
            Request request = new Request.Builder().url(url).build(); // 创建一个请求
            Response response = okHttpClient.newCall(request).execute(); // 返回实体
            if (!response.isSuccessful()) { // 判断是否成功
                System.out.println("失败"); // 链接失败
                return list;
            }
            String content = response.body().string();
            System.out.println(content);
            JSONObject jsonObject = JsonParse.stringToJson(content);
            JSONArray jsonArray = JsonParse.getJSONArray(jsonObject, "entries");
            for (int i = 0 , num = jsonArray.length() ; i < num ; i++){
                if(i >= limit)continue;
                JSONObject item = JsonParse.getJSONObject(jsonArray ,i);
                Acg12CaricatureDto caricatureDto = new Acg12CaricatureDto();
                caricatureDto.setComicId(JsonParse.getInt(item , "id"));
                caricatureDto.setTitle(JsonParse.getString(item,"name"));
                caricatureDto.setCover(JsonParse.getString(item,"cover"));
                caricatureDto.setAuthor(JsonParse.getString(item,"author"));
                caricatureDto.setType(1);
                caricatureDto.setIsCollect(0);
                list.add(caricatureDto);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(list.toString());
        return list;
    }

    public static void main(String[] args) {
//        kukeSearch("夏娜");
//        kukeCaricatureInfo(16953);
//        kukeCaricatureChapters(48246 , 1);
//        kukeTagTypeList(4, 1, "", 6);
        kukeTagList();
    }
}
