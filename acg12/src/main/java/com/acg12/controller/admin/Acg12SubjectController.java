package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.Acg12SubjectEntity;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.Acg12SubjectService;
import com.acg12.service.SystemUserService;
import com.acg12.support.Message;
import com.acg12.utils.StringUtil;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.doc.GSONUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/15 18:08
 * Description:
 */
@Controller("acg12SubjectController")
@RequestMapping("/admin/subject")
public class Acg12SubjectController extends GenericController {

    @Resource
    private SystemUserService userService;

    @Resource
    private Acg12SubjectService acg12SubjectEntityService;

    /**
     * 列表查询
     *
     * @param pageable
     * @param model
     * @return
     */
    @RequiresPermissions("admin:system:subject")
    @RequestMapping(value = {"/list"}, method = {RequestMethod.GET})
    public String list(Pageable pageable, HttpServletRequest request, ModelMap model) {
        return "/admin/subject/list";
    }

    // 新列表查询
    @RequestMapping(value = "/listNew.json", method = RequestMethod.GET)
    public @ResponseBody
    String listNew(HttpServletRequest request, Integer pageNumber, Integer pageSize, ModelMap model) {
        Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
        Map map = new HashMap();
        for (String key : paramter.keySet()) {
            if (!StringUtil.isEmpty(paramter.get(key).toString())) {
                map.put(key, paramter.get(key));
            }
        }
        String filter_id = request.getParameter("filter_id");
//        String filter_ide = request.getParameter("filter_ide");
        if (!StringUtil.isEmpty(filter_id)) {
            map.put("id", filter_id);
        }
//        if (!StringUtil.isEmpty(filter_ide)) {
//            map.put("filter_ide", filter_ide.replace("-", ""));
//        }
        String filter_sId = request.getParameter("filter_sId");
//        String filter_side = request.getParameter("filter_side");
        if (!StringUtil.isEmpty(filter_sId)) {
            map.put("sId", filter_sId);
        }
//        if (!StringUtil.isEmpty(filter_side)) {
//            map.put("filter_side", filter_side.replace("-", ""));
//        }
        String filter_type = request.getParameter("filter_type");
//        String filter_typee = request.getParameter("filter_typee");
        if (!StringUtil.isEmpty(filter_type)) {
            map.put("type", filter_type);
        }
//        if (!StringUtil.isEmpty(filter_typee)) {
//            map.put("filter_typee", filter_typee.replace("-", ""));
//        }
        String filter_epsCounts = request.getParameter("filter_epsCounts");
        String filter_epsCounte = request.getParameter("filter_epsCounte");
        if (!StringUtil.isEmpty(filter_epsCounts)) {
            map.put("filter_epsCounts", filter_epsCounts.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_epsCounte)) {
            map.put("filter_epsCounte", filter_epsCounte.replace("-", ""));
        }
        String filter_airWeekdays = request.getParameter("filter_airWeekdays");
        String filter_airWeekdaye = request.getParameter("filter_airWeekdaye");
        if (!StringUtil.isEmpty(filter_airWeekdays)) {
            map.put("filter_airWeekdays", filter_airWeekdays.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_airWeekdaye)) {
            map.put("filter_airWeekdaye", filter_airWeekdaye.replace("-", ""));
        }
        String filter_lockStatuss = request.getParameter("filter_lockStatuss");
        String filter_lockStatuse = request.getParameter("filter_lockStatuse");
        if (!StringUtil.isEmpty(filter_lockStatuss)) {
            map.put("filter_lockStatuss", filter_lockStatuss.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_lockStatuse)) {
            map.put("filter_lockStatuse", filter_lockStatuse.replace("-", ""));
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
        String filter_createTimes = request.getParameter("filter_createTimes");
        String filter_createTimee = request.getParameter("filter_createTimee");
        if (!StringUtil.isEmpty(filter_createTimes)) {
            map.put("filter_createTimes", filter_createTimes.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_createTimee)) {
            map.put("filter_createTimee", filter_createTimee.replace("-", ""));
        }
        String filter_updateTimes = request.getParameter("filter_updateTimes");
        String filter_updateTimee = request.getParameter("filter_updateTimee");
        if (!StringUtil.isEmpty(filter_updateTimes)) {
            map.put("filter_updateTimes", filter_updateTimes.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_updateTimee)) {
            map.put("filter_updateTimee", filter_updateTimee.replace("-", ""));
        }

        Long total = acg12SubjectEntityService.count(map);

        map.put("pageNumber", (pageNumber - 1) * pageSize);
        map.put("pageSize", pageSize);
        map = this.dateAndOrderMap(map, request);

        List<Acg12SubjectEntity> acg12SubjectEntitys = acg12SubjectEntityService.findListNewByPage(map);
        Map returnMap = new HashMap();
        returnMap.put("total", total);
        returnMap.put("rows", acg12SubjectEntitys);
        String str = GSONUtils.valueToString(returnMap);
        return str;
    }

    /**
     * 跳转添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(ModelMap model) {
        return "/admin/subject/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Acg12SubjectEntity acg12SubjectEntity, RedirectAttributes redirectAttributes) {
        SystemUserEntity user = userService.getCurrent();


        acg12SubjectEntityService.save(acg12SubjectEntity);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model) {
        Acg12SubjectEntity acg12SubjectEntity = acg12SubjectEntityService.find(id);
        model.addAttribute("acg12Subject", acg12SubjectEntity);
        return "/admin/subject/edit";
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, ModelMap model) {
        Acg12SubjectEntity acg12SubjectEntity = acg12SubjectEntityService.find(id);
        model.addAttribute("acg12SubjectEntity", acg12SubjectEntity);
        return "/admin/subject/view";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Acg12SubjectEntity acg12SubjectEntity, RedirectAttributes redirectAttributes) {
        acg12SubjectEntityService.update(acg12SubjectEntity);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
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
        if (!StringUtil.isEmpty(sortName) && sortName.equals("sid")) {
            map.put("order", " sId  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("type")) {
            map.put("order", " type  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("typeName")) {
            map.put("order", " type_name  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("name")) {
            map.put("order", " name  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("nameCn")) {
            map.put("order", " name_cn  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("summary")) {
            map.put("order", " summary  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("image")) {
            map.put("order", " image  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("epsCount")) {
            map.put("order", " eps_count  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("airDate")) {
            map.put("order", " air_date  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("airWeekday")) {
            map.put("order", " air_weekday  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("lockStatus")) {
            map.put("order", " lock_status  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("endDate")) {
            map.put("order", " end_date  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("createtime")) {
            map.put("order", " createTime  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("updatetime")) {
            map.put("order", " updateTime  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("createTime")) {
            map.put("order", " create_time  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("updateTime")) {
            map.put("order", " update_time  " + sortOrder);
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
            this.acg12SubjectEntityService.delete(long1);
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
                this.acg12SubjectEntityService.delete(Long.parseLong(idss[i]));
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
    @ResponseBody
    public Message updateColumnSearchLists(HttpServletRequest request, ModelMap model) {
        return SUCCESS_MESSAGE;
    }

}
