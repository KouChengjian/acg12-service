package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.Acg12SubjectOffprintEntity;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.Acg12SubjectOffprintService;
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
 * Controller - subjectOffprint
 *
 * @author kcj
 * @version 2.0
 */
@Controller("acg12SubjectOffprintController")
@RequestMapping({"/admin/subject_offprint"})
public class Acg12SubjectOffprintController extends GenericController {


    @Resource
    private SystemUserService userService;

    @Resource
    private Acg12SubjectOffprintService acg12SubjectOffprintService;

    /**
     * 跳转添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(ModelMap model) {
        return "/admin/subject_offprint/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Acg12SubjectOffprintEntity acg12SubjectOffprint, RedirectAttributes redirectAttributes) {
        SystemUserEntity user = userService.getCurrent();


        acg12SubjectOffprintService.save(acg12SubjectOffprint);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model) {
        Acg12SubjectOffprintEntity acg12SubjectOffprint = acg12SubjectOffprintService.find(id);
        model.addAttribute("acg12SubjectOffprint", acg12SubjectOffprint);
        return "/admin/subject_offprint/edit";
    }


    /**
     * 详情
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, ModelMap model) {
        Acg12SubjectOffprintEntity acg12SubjectOffprint = acg12SubjectOffprintService.find(id);
        model.addAttribute("acg12SubjectOffprint", acg12SubjectOffprint);
        return "/admin/subject_offprint/view";
    }


    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Acg12SubjectOffprintEntity acg12SubjectOffprint, RedirectAttributes redirectAttributes) {
        acg12SubjectOffprintService.update(acg12SubjectOffprint);
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
    public String list(Pageable pageable, @RequestParam("subject_id") Long subject_id, HttpServletRequest request, ModelMap model) {
        model.addAttribute("subject_id", subject_id);
        return "/admin/subject_offprint/list";
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

        String baseId = request.getParameter("subject_id");
        map.put("subjectId", baseId);

        String filter_ids = request.getParameter("filter_ids");
        String filter_ide = request.getParameter("filter_ide");
        if (!StringUtil.isEmpty(filter_ids)) {
            map.put("filter_ids", filter_ids.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_ide)) {
            map.put("filter_ide", filter_ide.replace("-", ""));
        }
        String filter_subjectIds = request.getParameter("filter_subjectIds");
        String filter_subjectIde = request.getParameter("filter_subjectIde");
        if (!StringUtil.isEmpty(filter_subjectIds)) {
            map.put("filter_subjectIds", filter_subjectIds.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_subjectIde)) {
            map.put("filter_subjectIde", filter_subjectIde.replace("-", ""));
        }
        String filter_parentSIds = request.getParameter("filter_parentSIds");
        String filter_parentSIde = request.getParameter("filter_parentSIde");
        if (!StringUtil.isEmpty(filter_parentSIds)) {
            map.put("filter_parentSIds", filter_parentSIds.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_parentSIde)) {
            map.put("filter_parentSIde", filter_parentSIde.replace("-", ""));
        }
        String filter_sIds = request.getParameter("filter_sIds");
        String filter_sIde = request.getParameter("filter_sIde");
        if (!StringUtil.isEmpty(filter_sIds)) {
            map.put("filter_sIds", filter_sIds.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_sIde)) {
            map.put("filter_sIde", filter_sIde.replace("-", ""));
        }

        Long total = acg12SubjectOffprintService.count(map);

        map.put("pageNumber", (pageNumber - 1) * pageSize);
        map.put("pageSize", pageSize);
        map = this.dateAndOrderMap(map, request);

        List<Acg12SubjectOffprintEntity> acg12SubjectOffprints = acg12SubjectOffprintService.findListNewByPage(map);
        Map returnMap = new HashMap();
        returnMap.put("total", total);
        returnMap.put("rows", acg12SubjectOffprints);
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
        if (!StringUtil.isEmpty(sortName) && sortName.equals("subjectId")) {
            map.put("order", " subject_id  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("parentSId")) {
            map.put("order", " parent_s_id  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("sId")) {
            map.put("order", " s_id  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("image")) {
            map.put("order", " image  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("name")) {
            map.put("order", " name  " + sortOrder);
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
            this.acg12SubjectOffprintService.delete(long1);
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
                this.acg12SubjectOffprintService.delete(Long.parseLong(idss[i]));
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
