package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.Acg12UserEntity;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.Acg12UserService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller - 用户表
 *
 * @author kcj
 * @version 2.0
 */
@Controller("adminAcg12UserController")
@RequestMapping({"/admin/acg12_user"})
public class Acg12UserController extends GenericController {


    @Resource
    private SystemUserService userService;

    @Resource
    private Acg12UserService acg12UserService;

    /**
     * 跳转添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(ModelMap model) {
        return "/admin/acg12_user/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Acg12UserEntity acg12User, RedirectAttributes redirectAttributes) {
        SystemUserEntity user = userService.getCurrent();


        acg12UserService.save(acg12User);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model) {
        Acg12UserEntity acg12User = acg12UserService.find(id);
        model.addAttribute("acg12User", acg12User);
        return "/admin/acg12_user/edit";
    }


    /**
     * 详情
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, ModelMap model) {
        Acg12UserEntity acg12User = acg12UserService.find(id);
        model.addAttribute("acg12User", acg12User);
        return "/admin/acg12_user/view";
    }


    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Acg12UserEntity acg12User, RedirectAttributes redirectAttributes) {
        acg12UserService.update(acg12User);
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
    @RequiresPermissions("admin:user:list")
    @RequestMapping(value = {"/list"}, method = {RequestMethod.GET})
    public String list(Pageable pageable, HttpServletRequest request, ModelMap model) {


        return "/admin/acg12_user/list";
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
        String filter_ids = request.getParameter("filter_ids");
        String filter_ide = request.getParameter("filter_ide");
        if (!StringUtil.isEmpty(filter_ids)) {
            map.put("filter_ids", filter_ids.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_ide)) {
            map.put("filter_ide", filter_ide.replace("-", ""));
        }
        String filter_sexs = request.getParameter("filter_sexs");
        String filter_sexe = request.getParameter("filter_sexe");
        if (!StringUtil.isEmpty(filter_sexs)) {
            map.put("filter_sexs", filter_sexs.replace("-", ""));
        }
        if (!StringUtil.isEmpty(filter_sexe)) {
            map.put("filter_sexe", filter_sexe.replace("-", ""));
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

        Long total = acg12UserService.count(map);

        map.put("pageNumber", (pageNumber - 1) * pageSize);
        map.put("pageSize", pageSize);
        map = this.dateAndOrderMap(map, request);

        List<Acg12UserEntity> acg12Users = acg12UserService.findListNewByPage(map);
        Map returnMap = new HashMap();
        returnMap.put("total", total);
        returnMap.put("rows", acg12Users);
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
        if (!StringUtil.isEmpty(sortName) && sortName.equals("username")) {
            map.put("order", " username  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("password")) {
            map.put("order", " password  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("email")) {
            map.put("order", " email  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("avatar")) {
            map.put("order", " avatar  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("nick")) {
            map.put("order", " nick  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("sex")) {
            map.put("order", " sex  " + sortOrder);
        }
        if (!StringUtil.isEmpty(sortName) && sortName.equals("sign")) {
            map.put("order", " sign  " + sortOrder);
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
            this.acg12UserService.delete(long1);
        }
        return SUCCESS_MESSAGE;
    }

    /**
     * 删除操作
     *
     * @return
     */
    @RequestMapping(value = {"/deletes"}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Message deletes(HttpServletRequest request) {

        String idsStrings = request.getParameter("ids");
        idsStrings = StringUtil.delChar(idsStrings, ",");
        if (!StringUtil.isEmpty(idsStrings)) {
            String[] idss = idsStrings.split(",");
            for (int i = 0; i < idss.length; i++) {
                this.acg12UserService.delete(Long.parseLong(idss[i]));
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
    @ResponseBody
    public Message updateListColumns(HttpServletRequest request, ModelMap model) {
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
