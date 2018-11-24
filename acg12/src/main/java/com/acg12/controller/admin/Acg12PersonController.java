package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.Acg12PersonEntity;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.Acg12PersonService;
import com.acg12.service.SystemUserService;
import com.acg12.support.Message;
import com.acg12.utils.StringUtil;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.doc.GSONUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller - person
 *
 * @author kcj
 * @version 2.0
 */
@Controller("acg12PersonController")
@RequestMapping({"/admin/person"})
public class Acg12PersonController extends GenericController {


    @Resource
    private SystemUserService userService;

    @Resource
    private Acg12PersonService acg12PersonService;

    /**
     * 跳转添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(ModelMap model) {
        return "/admin/person/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Acg12PersonEntity acg12Person, RedirectAttributes redirectAttributes) {
        SystemUserEntity user = userService.getCurrent();


        acg12PersonService.save(acg12Person);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model) {
        Acg12PersonEntity acg12Person = acg12PersonService.find(id);
        model.addAttribute("acg12Person", acg12Person);
        return "/admin/person/edit";
    }


    /**
     * 详情
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, ModelMap model) {
        Acg12PersonEntity acg12Person = acg12PersonService.find(id);
        model.addAttribute("acg12Person", acg12Person);
        return "/admin/acg12_person/view";
    }


    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Acg12PersonEntity acg12Person, RedirectAttributes redirectAttributes) {
        acg12PersonService.update(acg12Person);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }

    /**
     * 列表查询
     *
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = {"/list"}, method = {RequestMethod.GET})
    public String list(Pageable pageable, HttpServletRequest request, ModelMap model) {


        return "/admin/person/list";
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
        String filter_ids = request.getParameter("filter_ids");
        String filter_ide = request.getParameter("filter_ide");
        if (!StringUtil.isEmpty(filter_ids)) {
            map.put("filter_ids", filter_ids.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_ide)) {
            map.put("filter_ide", filter_ide.replace("-", ""));
        }
        String filter_pIds = request.getParameter("filter_pIds");
        String filter_pIde = request.getParameter("filter_pIde");
        if (!StringUtil.isEmpty(filter_pIds)) {
            map.put("filter_pIds", filter_pIds.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_pIde)) {
            map.put("filter_pIde", filter_pIde.replace("-", ""));
        }
        String filter_bloodtypes = request.getParameter("filter_bloodtypes");
        String filter_bloodtypee = request.getParameter("filter_bloodtypee");
        if (!StringUtil.isEmpty(filter_bloodtypes)) {
            map.put("filter_bloodtypes", filter_bloodtypes.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_bloodtypee)) {
            map.put("filter_bloodtypee", filter_bloodtypee.replace("-", ""));
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

        Long total = acg12PersonService.count(map);

        map.put("pageNumber", (pageNumber - 1) * pageSize);
        map.put("pageSize", pageSize);
        map = this.dateAndOrderMap(map, request);

        List<Acg12PersonEntity> acg12Persons = acg12PersonService.findListNewByPage(map);
        Map returnMap = new HashMap();
        returnMap.put("total", total);
        returnMap.put("rows", acg12Persons);
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
        if (!StringUtil.isEmpty(sortName) && sortName.equals("pId")) {
            map.put("order", " p_id  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("name")) {
            map.put("order", " name  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("nameCn")) {
            map.put("order", " name_cn  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("image")) {
            map.put("order", " image  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("summary")) {
            map.put("order", " summary  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("height")) {
            map.put("order", " height  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("weight")) {
            map.put("order", " weight  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("alias")) {
            map.put("order", " alias  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("type")) {
            map.put("order", " type  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("gender")) {
            map.put("order", " gender  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("bloodtype")) {
            map.put("order", " bloodtype  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("birthday")) {
            map.put("order", " birthday  " + sortOrder);
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
            this.acg12PersonService.delete(long1);
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
                this.acg12PersonService.delete(Long.parseLong(idss[i]));
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
