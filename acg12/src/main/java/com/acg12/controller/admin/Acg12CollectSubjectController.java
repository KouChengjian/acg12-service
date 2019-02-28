package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.Acg12CollectSubjectEntity;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.Acg12CollectSubjectService;
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
 * Controller - collectSubject
 *
 * @author kcj
 * @version 2.0
 */
@Controller("adminAcg12CollectSubjectController")
@RequestMapping({ "/admin/acg12_collect_subject" })
public class Acg12CollectSubjectController extends GenericController {


    @Resource
    private SystemUserService userService;

    @Resource
    private Acg12CollectSubjectService acg12CollectSubjectService;

    /**
     * 跳转添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
    public String add(ModelMap model) {
        return "/admin/acg12_collect_subject/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Acg12CollectSubjectEntity acg12CollectSubject, RedirectAttributes redirectAttributes) {
        SystemUserEntity user=userService.getCurrent();


        acg12CollectSubjectService.save(acg12CollectSubject);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model) {
        Acg12CollectSubjectEntity acg12CollectSubject = acg12CollectSubjectService.find(id);
        model.addAttribute("acg12CollectSubject", acg12CollectSubject);
        return "/admin/acg12_collect_subject/edit";
    }


    /**
     * 详情
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, ModelMap model) {
        Acg12CollectSubjectEntity acg12CollectSubject = acg12CollectSubjectService.find(id);
        model.addAttribute("acg12CollectSubject", acg12CollectSubject);
        return "/admin/acg12_collect_subject/view";
    }


    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Acg12CollectSubjectEntity acg12CollectSubject, RedirectAttributes redirectAttributes) {
        acg12CollectSubjectService.update(acg12CollectSubject);
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
    @RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
    public String list(Pageable pageable,HttpServletRequest request, ModelMap model) {



        return "/admin/acg12_collect_subject/list";
    }

    // 新列表查询
    @RequestMapping(value = "/listNew.json", method = RequestMethod.GET)
    public @ResponseBody String listNew(HttpServletRequest request, Integer pageNumber, Integer pageSize,
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
        if(!StringUtil.isEmpty(filter_ids)) {
            map.put("filter_ids", filter_ids.replace("-", ""));
        }
        if(!StringUtil.isEmpty(filter_ide)) {
            map.put("filter_ide", filter_ide.replace("-", ""));
        }
        String filter_relevanceIds = request.getParameter("filter_relevanceIds");
        String filter_relevanceIde = request.getParameter("filter_relevanceIde");
        if(!StringUtil.isEmpty(filter_relevanceIds)) {
            map.put("filter_relevanceIds", filter_relevanceIds.replace("-", ""));
        }
        if(!StringUtil.isEmpty(filter_relevanceIde)) {
            map.put("filter_relevanceIde", filter_relevanceIde.replace("-", ""));
        }
        String filter_types = request.getParameter("filter_types");
        String filter_typee = request.getParameter("filter_typee");
        if(!StringUtil.isEmpty(filter_types)) {
            map.put("filter_types", filter_types.replace("-", ""));
        }
        if(!StringUtil.isEmpty(filter_typee)) {
            map.put("filter_typee", filter_typee.replace("-", ""));
        }
        String filter_createTimes = request.getParameter("filter_createTimes");
        String filter_createTimee = request.getParameter("filter_createTimee");
        if(!StringUtil.isEmpty(filter_createTimes)) {
            map.put("filter_createTimes", filter_createTimes.replace("-", ""));
        }
        if(!StringUtil.isEmpty(filter_createTimee)) {
            map.put("filter_createTimee", filter_createTimee.replace("-", ""));
        }
        String filter_updateTimes = request.getParameter("filter_updateTimes");
        String filter_updateTimee = request.getParameter("filter_updateTimee");
        if(!StringUtil.isEmpty(filter_updateTimes)) {
            map.put("filter_updateTimes", filter_updateTimes.replace("-", ""));
        }
        if(!StringUtil.isEmpty(filter_updateTimee)) {
            map.put("filter_updateTimee", filter_updateTimee.replace("-", ""));
        }

        Long total = acg12CollectSubjectService.count(map);

        map.put("pageNumber", (pageNumber - 1) * pageSize);
        map.put("pageSize", pageSize);
        map= this.dateAndOrderMap(map, request);

        List<Acg12CollectSubjectEntity> acg12CollectSubjects = acg12CollectSubjectService.findListNewByPage(map);
        Map returnMap = new HashMap();
        returnMap.put("total", total);
        returnMap.put("rows", acg12CollectSubjects);
        String str= GSONUtils.valueToString(returnMap);
        return str;

    }


    private Map dateAndOrderMap(Map map,HttpServletRequest  request){
        String sortOrder = request.getParameter("sortOrder");
        String sortName = request.getParameter("sortName");
        if (StringUtil.isEmpty(sortName)) {
            map.put("order", sortOrder.replace("+", " "));
        }else{
            map.put("order",  sortName+"  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("id")){
            map.put("order",  " id  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("relevanceId")){
            map.put("order",  " relevance_id  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("name")){
            map.put("order",  " name  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("nameCn")){
            map.put("order",  " name_cn  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("type")){
            map.put("order",  " type  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("typeName")){
            map.put("order",  " type_name  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("createTime")){
            map.put("order",  " create_time  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("updateTime")){
            map.put("order",  " update_time  "+ sortOrder);
        }
        return map;
    }

    /**
     * 删除操作
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = { "/delete" }, method = {  RequestMethod.POST,RequestMethod.GET  })
    public @ResponseBody Message delete(Long[] ids) {
        for (Long long1 : ids) {
            this.acg12CollectSubjectService.delete(long1);
        }
        return SUCCESS_MESSAGE;
    }

    /**
     * 删除操作
     *
     * @return
     */
    @RequestMapping(value = { "/deletes" }, method = { RequestMethod.POST,RequestMethod.GET })
    public @ResponseBody
    Message deletes(HttpServletRequest request) {

        String idsStrings=request.getParameter("ids");
        idsStrings=StringUtil.delChar(idsStrings, ",");
        if(!StringUtil.isEmpty(idsStrings)){
            String[] idss=idsStrings.split(",");
            for (int i = 0; i < idss.length; i++) {
                this.acg12CollectSubjectService.delete(Long.parseLong(idss[i]));
            }
        }

        return SUCCESS_MESSAGE;
    }


    /**
     * 修改显示隐藏列表
     *
     * @return
     */
    @RequestMapping(value = { "/updateListColumns" }, method = { RequestMethod.GET })
    public @ResponseBody Message updateListColumns(HttpServletRequest request, ModelMap model) {
        return SUCCESS_MESSAGE;
    }

    /**
     * 修改显示隐藏搜索字段
     *
     * @return
     */
    @RequestMapping(value = { "/updateColumnSearchLists" }, method = { RequestMethod.GET })
    public @ResponseBody Message updateColumnSearchLists(HttpServletRequest request, ModelMap model) {
        return SUCCESS_MESSAGE;
    }

}
