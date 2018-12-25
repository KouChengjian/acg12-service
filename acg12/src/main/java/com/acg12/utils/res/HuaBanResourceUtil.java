package com.acg12.utils.res;

import com.acg12.entity.dto.Acg12AlbumDto;
import com.acg12.entity.dto.Acg12PaletteDto;
import com.acg12.utils.UrlEncoderUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/18.
 */
public class HuaBanResourceUtil {

    // 获取画集
    public static final String URL_ALBUM = "http://huaban.com/favorite/anime/?ieks59gh&limit=20&wfl=1";//&max=474845357
    // 获取画板
    public static final String URL_PALETTE = "http://huaban.com/boards/favorite/anime/?iemf5hf8&limit=20&wfl=1"; //&max=24465404
    // 获取画板中的画集
    public static final String URL_PALETTE_ALBUM = "http://huaban.com/boards/";
    // 搜索 - 图片
    public static final String URL_SEARCH_ALBUM   = "http://huaban.com/search/?category=anime&q=";
    // 搜索 - 画集
    public static final String URL_SEARCH_PALETTE = "http://huaban.com/search/boards/?q=";

    // 获取新的画集
    public static synchronized List<Acg12AlbumDto> getAlbumList(String max) {
        List<Acg12AlbumDto> albumList = new ArrayList<Acg12AlbumDto>();
        try {
            Document document = Jsoup.connect(URL_ALBUM + "&max=" + max)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            String content = document.toString();

            if (content != null && !content.isEmpty()) {
                Elements var = document.body().select("script");
                for (Element div : var) {
                    String str1 = "app.page[" + "\"" + "ads" + "\"" + "] = ";
                    String str2 = "app.page[" + "\"" + "pins" + "\"" + "] = ";
                    String str3 = StringUtils.substringBetween(div.toString(), str2, str1);
                    if (str3 != null && !str3.isEmpty()) {
                        JSONArray array = new JSONArray(str3);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject json = (JSONObject) array.get(i);
                            JSONObject jsomUrl = (JSONObject) json.get("file");
//                            ArrayList<String> url = new ArrayList<String>();
                            int width = 0, height = 0;
                            width = 720 / 2 - 30;
                            height = width * jsomUrl.getInt("height")
                                    / jsomUrl.getInt("width");
                            if (height > 500) {
                                height = 500;
                            }
                            Acg12AlbumDto album = new Acg12AlbumDto();
                            album.setContent(json.getString("raw_text"));
                            album.setPinId(String.valueOf(json.getInt("pin_id")));
//                            url.add("http://img.hb.aicdn.com/" + jsomUrl.getString("key") + "_fw658");
//                            album.setUrlList(url);
                            album.setImage("http://img.hb.aicdn.com/" + jsomUrl.getString("key") + "_fw658");
                            album.setResWidth(width);
                            album.setResHight(height);
                            album.setLove(json.getInt("like_count"));
                            album.setFavorites(json.getInt("repin_count"));
                            albumList.add(album);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return albumList;
    }

    // 获取画板
    public static synchronized List<Acg12PaletteDto> getPaletteList(String max) {
        List<Acg12PaletteDto> paletteList = new ArrayList<Acg12PaletteDto>();
        try {
            Document document = Jsoup
                    .connect(URL_PALETTE + "&max=" + max)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            String content = document.toString();

            if (content != null && !content.isEmpty()) {
                Elements var = document.body().select("script");
                for (Element div : var) {
                    String str1 = "app.page[" + "\"" + "boards" + "\"" + "] = ";
                    String str2 = "app.page[" + "\"" + "promotions" + "\"" + "] = ";
                    String str3 = StringUtils.substringBetween(div.toString(), str1, str2);
                    if (str3 != null && !str3.isEmpty()) {
                        JSONArray json = new JSONArray(str3);
                        for (int i = 0; i < json.length(); i++) {
                            Acg12PaletteDto palette = new Acg12PaletteDto();
                            ArrayList<String> url = new ArrayList<String>();
                            JSONObject boardjs = (JSONObject) json.get(i);
                            JSONArray array = boardjs.getJSONArray("pins");
                            for (int j = 0; j < array.length(); j++) {
                                JSONObject js = (JSONObject) array.get(j);
                                JSONObject jsfile = js.getJSONObject("file");
                                url.add("http://img.hb.aicdn.com/"
                                        + jsfile.getString("key") + "_fw658");
                            }
                            palette.setName(boardjs.getString("title"));
                            palette.setNum(boardjs.getInt("pin_count"));
                            palette.setUrlAlbum(url);
                            palette.setBoardId(boardjs.getString("board_id"));
                            paletteList.add(palette);
                        }
                    }
                }
//                System.out.println(new Gson().toJson(paletteList));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return paletteList;
    }

    // 画板中详细内容
    public static synchronized List<Acg12AlbumDto> getBoardsToAlbumList(String boardId, String max) {
        List<Acg12AlbumDto> albumList = new ArrayList<Acg12AlbumDto>();
        try {
            Document document = Jsoup
                    .connect(URL_PALETTE_ALBUM + boardId + "/?iemf5hfr&limit=20&wfl=1&max=" + max)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            String content = document.toString();
            if (content != null && !content.isEmpty()) {
                Elements var = document.body().select("script");
                for (Element div : var) {
                    String str1 = "app.page[" + "\"" + "board" + "\"" + "] = ";
                    String str2 = "app._csr = true";
                    String str3 = StringUtils.substringBetween(div.toString(), str1, str2);
                    if (str3 != null && !str3.isEmpty()) {
                        JSONObject json = new JSONObject(str3);
                        JSONArray jsonpins = json.getJSONArray("pins");
                        for (int i = 0; i < jsonpins.length(); i++) {
                            JSONObject pins = jsonpins.getJSONObject(i);
                            JSONObject jsomUrl = (JSONObject) pins.get("file");
//                            ArrayList<String> url = new ArrayList<String>();
                            int width = 0, height = 0;
                            width = 720 / 2 - 30;
                            height = width * jsomUrl.getInt("height")
                                    / jsomUrl.getInt("width");
                            if (height > 500) {
                                height = 500;
                            }
                            Acg12AlbumDto album = new Acg12AlbumDto();
                            album.setContent(pins.getString("raw_text"));
//                            url.add("http://img.hb.aicdn.com/" + jsomUrl.getString("key") + "_fw658");
                            album.setPinId(String.valueOf(pins.getInt("pin_id")));
//                            album.setUrlList(url);
                            album.setImage("http://img.hb.aicdn.com/" + jsomUrl.getString("key") + "_fw658");
                            album.setResWidth(width);
                            album.setResHight(height);
                            album.setLove(pins.getInt("like_count"));
                            album.setFavorites(pins.getInt("repin_count"));
                            albumList.add(album);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return albumList;
    }

    // 搜索-图片
    public static synchronized List<Acg12AlbumDto> getSearchAlbum(String key, String page) {
        List<Acg12AlbumDto> albumList = new ArrayList<Acg12AlbumDto>();
        try {
            System.out.println(key);
            Document document = Jsoup
                    .connect(URL_SEARCH_ALBUM + (UrlEncoderUtil.hasUrlEncoded(key) ? key : UrlEncoderUtil.encode(key)) + "&page=" + page)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            String content = document.toString();
//            System.out.println(content);
            if (content != null && !content.isEmpty()) {
                Elements var = document.body().select("script");
//                System.out.println(var.toString());
//                System.out.println(var.size());
                for (Element div : var) {
                    String str1 = "app.page[" + "\"" + "pins" + "\"" + "] = ";
//                    System.out.println(str1);
                    String str2 = "app.page[" + "\"" + "page" + "\"" + "] = ";
//                    System.out.println(str2);
                    String str3 = StringUtils.substringBetween(div.toString(), str1, str2);
//                    System.out.println(str3);
                    if (str3 != null && !str3.isEmpty()) {
                        str3 = str3.replace(";", "");
//                        System.out.println(str3);
                        JSONArray array = new JSONArray(str3);

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject json = (JSONObject) array.get(i);
//                            System.out.println(json.toString());
                            JSONObject jsomUrl = (JSONObject) json.get("file");
//                            ArrayList<String> url = new ArrayList<String>();
                            int width = 0, height = 0;
                            width = 720 / 2 - 30;
                            height = width * jsomUrl.getInt("height")
                                    / jsomUrl.getInt("width");
                            if (height > 500) {
                                height = 500;
                            }
                            Acg12AlbumDto album = new Acg12AlbumDto();
                            album.setContent(json.getString("raw_text"));
                            album.setPinId(String.valueOf(json.getInt("pin_id")));
//                            url.add("http://img.hb.aicdn.com/" + jsomUrl.getString("key") + "_fw658"); //  236
//                            album.setUrlList(url);
                            album.setImage("http://img.hb.aicdn.com/" + jsomUrl.getString("key") + "_fw658");
                            album.setResWidth(width);
                            album.setResHight(height);
                            album.setLove(json.getInt("like_count"));
                            album.setFavorites(json.getInt("repin_count"));
                            albumList.add(album);
                        }
                        continue;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return albumList;
    }

    // 搜索-画板
    public static synchronized List<Acg12PaletteDto> getSearchPalette(String key, String page) {
        List<Acg12PaletteDto> paletteList = new ArrayList<Acg12PaletteDto>();
        try {
            Document document = Jsoup
                    .connect(URL_SEARCH_PALETTE + (UrlEncoderUtil.hasUrlEncoded(key) ? key : UrlEncoderUtil.encode(key)) + "&page=" + page)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            String content = document.toString();
            if (content != null && !content.isEmpty()) {
                Elements var = document.body().select("script");
//                System.out.println(UrlConstant.URL_SEARCH_PALETTE + key + "&page=" + page);
//                System.out.println(var.toString());
                for (Element div : var) {
                    String str1 = "app.page[" + "\"" + "boards" + "\"" + "] = ";
                    String str2 = "app._csr = true";
                    String str3 = StringUtils.substringBetween(div.toString(), str1, str2);
                    if (str3 != null && !str3.isEmpty()) {
                        JSONArray json = new JSONArray(str3);

                        for (int i = 0; i < json.length(); i++) {
                            Acg12PaletteDto palette = new Acg12PaletteDto();
                            ArrayList<String> url = new ArrayList<String>();
                            JSONObject boardjs = (JSONObject) json.get(i);
                            System.out.println(boardjs.toString());
                            JSONArray array = boardjs.getJSONArray("pins");
                            for (int j = 0; j < array.length(); j++) {
                                JSONObject js = (JSONObject) array.get(j);
                                JSONObject jsfile = js.getJSONObject("file");
                                url.add("http://img.hb.aicdn.com/" + jsfile.getString("key") + "_fw658");
                            }
                            palette.setName(boardjs.getString("title"));
                            palette.setNum(boardjs.getInt("pin_count"));
                            palette.setUrlAlbum(url);
                            palette.setBoardId(boardjs.getLong("board_id")+"");
                            paletteList.add(palette);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return paletteList;
    }
}
