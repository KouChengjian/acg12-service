package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.Acg12SubjectStaffEntity;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.Acg12SubjectStaffService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller - subjectStaff
 *
 * @author kcj
 * @version 2.0
 */
@Controller("adminAcg12SubjectStaffController")
@RequestMapping({"/admin/subject_staff"})
public class Acg12SubjectStaffController extends GenericController {


    @Resource
    private SystemUserService userService;

    @Resource
    private Acg12SubjectStaffService acg12SubjectStaffService;

    /**
     * 跳转添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(ModelMap model) {
        return "/admin/subject_staff/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Acg12SubjectStaffEntity acg12SubjectStaff, RedirectAttributes redirectAttributes) {
        SystemUserEntity user = userService.getCurrent();


        acg12SubjectStaffService.save(acg12SubjectStaff);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model) {
        Acg12SubjectStaffEntity acg12SubjectStaff = acg12SubjectStaffService.find(id);
        model.addAttribute("acg12SubjectStaff", acg12SubjectStaff);
        return "/admin/subject_staff/edit";
    }


    /**
     * 详情
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, ModelMap model) {
        Acg12SubjectStaffEntity acg12SubjectStaff = acg12SubjectStaffService.find(id);
        model.addAttribute("acg12SubjectStaff", acg12SubjectStaff);
        return "/admin/subject_staff/view";
    }


    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Acg12SubjectStaffEntity acg12SubjectStaff, RedirectAttributes redirectAttributes) {
        acg12SubjectStaffService.update(acg12SubjectStaff);
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
    public String list(Pageable pageable, @RequestParam("subject_id") Long subject_id,HttpServletRequest request, ModelMap model) {
        model.addAttribute("subject_id", subject_id);
        return "/admin/subject_staff/list";
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
        String filter_ids = request.getParameter("filter_ids");
        String filter_ide = request.getParameter("filter_ide");
        map.put("subjectId", baseId);
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
        String filter_sIds = request.getParameter("filter_sIds");
        String filter_sIde = request.getParameter("filter_sIde");
        if (!StringUtil.isEmpty(filter_sIds)) {
            map.put("filter_sIds", filter_sIds.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_sIde)) {
            map.put("filter_sIde", filter_sIde.replace("-", ""));
        }
        String filter_personIds = request.getParameter("filter_personIds");
        String filter_personIde = request.getParameter("filter_personIde");
        if (!StringUtil.isEmpty(filter_personIds)) {
            map.put("filter_personIds", filter_personIds.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_personIde)) {
            map.put("filter_personIde", filter_personIde.replace("-", ""));
        }
        String filter_pIds = request.getParameter("filter_pIds");
        String filter_pIde = request.getParameter("filter_pIde");
        if (!StringUtil.isEmpty(filter_pIds)) {
            map.put("filter_pIds", filter_pIds.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_pIde)) {
            map.put("filter_pIde", filter_pIde.replace("-", ""));
        }
        String filter_createtimes = request.getParameter("filter_createtimes");
        String filter_createtimee = request.getParameter("filter_createtimee");
        if (!StringUtil.isEmpty(filter_createtimes)) {
            map.put("filter_createtimes", filter_createtimes.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_createtimee)) {
            map.put("filter_createtimee", filter_createtimee.replace("-", ""));
        }
        String filter_updatetimes = request.getParameter("filter_updatetimes");
        String filter_updatetimee = request.getParameter("filter_updatetimee");
        if (!StringUtil.isEmpty(filter_updatetimes)) {
            map.put("filter_updatetimes", filter_updatetimes.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_updatetimee)) {
            map.put("filter_updatetimee", filter_updatetimee.replace("-", ""));
        }

        Long total = acg12SubjectStaffService.count(map);

        map.put("pageNumber", (pageNumber - 1) * pageSize);
        map.put("pageSize", pageSize);
        map = this.dateAndOrderMap(map, request);

        List<Acg12SubjectStaffEntity> acg12SubjectStaffs = acg12SubjectStaffService.findListNewByPage(map);
        Map returnMap = new HashMap();
        returnMap.put("total", total);
        returnMap.put("rows", acg12SubjectStaffs);
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
        if (!StringUtil.isEmpty(sortName) && sortName.equals("sId")) {
            map.put("order", " s_id  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("personId")) {
            map.put("order", " person_id  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("pId")) {
            map.put("order", " p_id  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("name")) {
            map.put("order", " name  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("job")) {
            map.put("order", " job  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("createtime")) {
            map.put("order", " createTime  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("updatetime")) {
            map.put("order", " updateTime  " + sortOrder);
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
            this.acg12SubjectStaffService.delete(long1);
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
                this.acg12SubjectStaffService.delete(Long.parseLong(idss[i]));
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
