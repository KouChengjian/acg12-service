package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.Acg12PersonDetailEntity;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.Acg12PersonDetailService;
import com.acg12.service.SystemUserService;
import com.acg12.support.Message;
import com.acg12.utils.StringUtil;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.doc.GSONUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller - personDetail
 *
 * @author kcj
 * @version 2.0
 */
@Controller("adminAcg12PersonDetailController")
@RequestMapping({"/admin/person_detail"})
public class Acg12PersonDetailController extends GenericController {


    @Resource
    private SystemUserService userService;

    @Resource
    private Acg12PersonDetailService acg12PersonDetailService;

    /**
     * 跳转添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(ModelMap model) {
        return "/admin/person_detail/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Acg12PersonDetailEntity acg12PersonDetail, RedirectAttributes redirectAttributes) {
        SystemUserEntity user = userService.getCurrent();


        acg12PersonDetailService.save(acg12PersonDetail);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model) {
        Acg12PersonDetailEntity acg12PersonDetail = acg12PersonDetailService.find(id);
        model.addAttribute("acg12PersonDetail", acg12PersonDetail);
        return "/admin/person_detail/edit";
    }


    /**
     * 详情
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, ModelMap model) {
        Acg12PersonDetailEntity acg12PersonDetail = acg12PersonDetailService.find(id);
        model.addAttribute("acg12PersonDetail", acg12PersonDetail);
        return "/admin/person_detail/view";
    }


    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Acg12PersonDetailEntity acg12PersonDetail, RedirectAttributes redirectAttributes) {
        acg12PersonDetailService.update(acg12PersonDetail);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 列表查询
     *
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = {"/list"}, method = {RequestMethod.GET})
    public String list(Pageable pageable, @RequestParam("person_id") Long person_id, HttpServletRequest request, ModelMap model) {
        model.addAttribute("person_id", person_id);

        return "/admin/person_detail/list";
    }

    // 新列表查询
    @RequestMapping(value = "/listNew.json", method = RequestMethod.GET)
    public @ResponseBody
    String listNew(HttpServletRequest request, Integer pageNumber, Integer pageSize,
                   ModelMap model) {

        Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
        Map map = new HashMap();
        for (String key : paramter.keySet()) {
            if (!StringUtil.isEmpty(paramter.get(key).toString())) {
                map.put(key, paramter.get(key));
            }
        }
        String baseId = request.getParameter("person_id");
        map.put("personId", baseId);

        String filter_ids = request.getParameter("filter_ids");
        String filter_ide = request.getParameter("filter_ide");
        if (!StringUtil.isEmpty(filter_ids)) {
            map.put("filter_ids", filter_ids.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_ide)) {
            map.put("filter_ide", filter_ide.replace("-", ""));
        }
        String filter_personIds = request.getParameter("filter_personIds");
        String filter_personIde = request.getParameter("filter_personIde");
        if (!StringUtil.isEmpty(filter_personIds)) {
            map.put("filter_personIds", filter_personIds.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_personIde)) {
            map.put("filter_personIde", filter_personIde.replace("-", ""));
        }

        Long total = acg12PersonDetailService.count(map);

        map.put("pageNumber", (pageNumber - 1) * pageSize);
        map.put("pageSize", pageSize);
        map = this.dateAndOrderMap(map, request);

        List<Acg12PersonDetailEntity> acg12PersonDetails = acg12PersonDetailService.findListNewByPage(map);
        Map returnMap = new HashMap();
        returnMap.put("total", total);
        returnMap.put("rows", acg12PersonDetails);
        String str = GSONUtils.valueToString(returnMap);
        return str;

    }


    private Map dateAndOrderMap(Map map, HttpServletRequest request) {
        String sortOrder = request.getParameter("sortOrder");
        String sortName = request.getParameter("sortName");
        if (StringUtil.isEmpty(sortName)) {
            map.put("order", sortOrder.replace("+", " "));
        } else {
            map.put("order", sortName + "  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("id")) {
            map.put("order", " id  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("personId")) {
            map.put("order", " person_id  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("otherTitle")) {
            map.put("order", " other_title  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("otherValue")) {
            map.put("order", " other_value  " + sortOrder);
        }
        return map;
    }

    /**
     * 删除操作
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    Message delete(Long[] ids) {
        for (Long long1 : ids) {
            this.acg12PersonDetailService.delete(long1);
        }
        return SUCCESS_MESSAGE;
    }

    /**
     * 删除操作
     *
     * @return
     */
    @RequestMapping(value = {"/deletes"}, method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    Message deletes(HttpServletRequest request) {

        String idsStrings = request.getParameter("ids");
        idsStrings = StringUtil.delChar(idsStrings, ",");
        if (!StringUtil.isEmpty(idsStrings)) {
            String[] idss = idsStrings.split(",");
            for (int i = 0; i < idss.length; i++) {
                this.acg12PersonDetailService.delete(Long.parseLong(idss[i]));
            }
        }

        return SUCCESS_MESSAGE;
    }


    /**
     * 修改显示隐藏列表
     *
     * @return
     */
    @RequestMapping(value = {"/updateListColumns"}, method = {RequestMethod.GET})
    public @ResponseBody
    Message updateListColumns(HttpServletRequest request, ModelMap model) {
        return SUCCESS_MESSAGE;
    }

    /**
     * 修改显示隐藏搜索字段
     *
     * @return
     */
    @RequestMapping(value = {"/updateColumnSearchLists"}, method = {RequestMethod.GET})
    public @ResponseBody
    Message updateColumnSearchLists(HttpServletRequest request, ModelMap model) {
        return SUCCESS_MESSAGE;
    }

}
