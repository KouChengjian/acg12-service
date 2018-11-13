package com.acg12.controller.admin;

import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/12 20:12
 * Description:
 */
@Controller("adminAnimationController")
@RequestMapping("/admin/animation")
public class Acg12AnimationController {

    /**
     * 列表查询
     *
     * @param pageable
     * @param model
     * @return
     */
    @RequiresPermissions("admin:system:animation")
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public String list(Pageable pageable, HttpServletRequest request, ModelMap model) {
        return "/admin/animation/list";
    }

//    // 新列表查询
//    @RequestMapping(value = "/listNew.json", method = RequestMethod.GET)
//    public @ResponseBody String listNew(HttpServletRequest request, Integer pageNumber, Integer pageSize, ModelMap model) {
//        Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
//        Map map = new HashMap();
//        for (String key : paramter.keySet()) {
//            if (!StringUtil.isEmpty(paramter.get(key).toString())) {
//                map.put(key, paramter.get(key));
//            }
//        }
//        String filter_ids = request.getParameter("filter_ids");
//        String filter_ide = request.getParameter("filter_ide");
//        if(!StringUtil.isEmpty(filter_ids)) {
//            map.put("filter_ids", filter_ids.replace("-", ""));
//        }
//        if(!StringUtil.isEmpty(filter_ide)) {
//            map.put("filter_ide", filter_ide.replace("-", ""));
//        }
//        String filter_sorts = request.getParameter("filter_sorts");
//        String filter_sorte = request.getParameter("filter_sorte");
//        if(!StringUtil.isEmpty(filter_sorts)) {
//            map.put("filter_sorts", filter_sorts.replace("-", ""));
//        }
//        if(!StringUtil.isEmpty(filter_sorte)) {
//            map.put("filter_sorte", filter_sorte.replace("-", ""));
//        }
//        String filter_evenTypes = request.getParameter("filter_evenTypes");
//        String filter_evenTypee = request.getParameter("filter_evenTypee");
//        if(!StringUtil.isEmpty(filter_evenTypes)) {
//            map.put("filter_evenTypes", filter_evenTypes.replace("-", ""));
//        }
//        if(!StringUtil.isEmpty(filter_evenTypee)) {
//            map.put("filter_evenTypee", filter_evenTypee.replace("-", ""));
//        }
//
//        Long total = syEventReportIteamService.count(map);
//
//        map.put("pageNumber", (pageNumber - 1) * pageSize);
//        map.put("pageSize", pageSize);
//        map= this.dateAndOrderMap(map, request);
//
//        List<SyEventReportIteam> syEventReportIteams = syEventReportIteamService.findListNewByPage(map);
//        Map returnMap = new HashMap();
//        returnMap.put("total", total);
//        returnMap.put("rows", syEventReportIteams);
//        String str= GSONUtils.valueToString(returnMap);
//        return str;
//
//    }
}
