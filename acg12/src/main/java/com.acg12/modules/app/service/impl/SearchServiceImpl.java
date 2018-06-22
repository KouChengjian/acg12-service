package com.acg12.modules.app.service.impl;

import com.acg12.common.constant.Condition;
import com.acg12.modules.app.service.SearchService;
import com.acg12.common.utils.crawler.BgmCrawler;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/14.
 */
@Service("searchServiceImpl")
public class SearchServiceImpl implements SearchService {

    @Override
    public Map<String, Object> subjectSearchList(Map<String, Object> map) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        String key = (String) map.get("key");
        String start = (String) map.get("start");
        String type = (String) map.get("type");
        System.err.println(type+"===");
        JSONObject msg = BgmCrawler.getBgmSearchSubjectList(key, 0, Integer.valueOf(start).intValue());
        if (msg == null) {
            return Condition.create202();
        }
        return Condition.create200(msg.toString());
    }
}
