package com.acg12.service.search.impl;

import com.acg12.conf.Condition;
import com.acg12.entity.po.SubjectEntity;
import com.acg12.service.search.SearchService;
import com.acg12.utils.StringUtil;
import com.acg12.utils.search.SearchUtli;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/14.
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Override
    public Map<String, Object> subjectSearchList(Map<String, Object> map) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        String key = (String) map.get("key");
        String start = (String) map.get("start");
        JSONObject msg = SearchUtli.getSubjectSearchList(key, 0, Integer.valueOf(start).intValue());
        if (msg == null) {
            return Condition.create202();
        }
        return Condition.create200(msg.toString());
    }
}
