package com.acg12.conf;

/**
 * Created by Administrator on 2018/1/9.
 */
public class UrlConstant {

    /**
     * -----------------------------------------花瓣网资源-------------------------------------------
     */

    /**
     * --------------------------------------动漫之家资源--------------------------------------------
     */
    // 动漫之家 资讯
    // http://v2.api.dmzj.com/v3/article/list/0/2/0.json?channel=Android&version=2.7.003 全部
    // http://v2.api.dmzj.com/v3/article/list/0/2/1.json?channel=Android&version=2.7.003
    // http://v2.api.dmzj.com/v3/article/list/1/2/0.json?channel=Android&version=2.7.003 动画情报
    // http://v2.api.dmzj.com/v3/article/list/2/2/0.json?channel=Android&version=2.7.003 漫画情报
    public static final String URL_DONGMANZHIJIA_NEWS = "http://v2.api.dmzj.com/v3/article/list/0/2/%s.json";


    // 动漫之家 最新漫画
    // http://v2.api.dmzj.com/latest/100/0.json?channel=Android&version=2.7.003
    // http://v2.api.dmzj.com/latest/1/0.json?channel=Android&version=2.7.003
    // http://v2.api.dmzj.com/latest/0/0.json?channel=Android&version=2.7.003

    // 动漫之家 分类
    //  http://v2.api.dmzj.com/0/category.json?channel=Android&version=2.7.003

    // 动漫之家 热门
    // http://v2.api.dmzj.com/rank/0/0/0/0.json?channel=Android&version=2.7.003

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

    /**
     * ---------------------------------------bilibili资源-------------------------------------------
     */
}
