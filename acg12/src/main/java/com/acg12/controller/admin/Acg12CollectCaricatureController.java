package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.Acg12CollectCaricatureEntity;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.Acg12CollectCaricatureService;
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
 * Controller - CollectCaricature
 *
 * @author kcj
 * @version 2.0
 */
@Controller("adminAcg12CollectCaricatureController")
@RequestMapping({ "/admin/acg12_collect_caricature" })
public class Acg12CollectCaricatureController extends GenericController {


    @Resource
    private SystemUserService userService;

    @Resource
    private Acg12CollectCaricatureService acg12CollectCaricatureService;

    /**
     * 跳转添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
    public String add(ModelMap model) {
        return "/admin/acg12_collect_caricature/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Acg12CollectCaricatureEntity acg12CollectCaricature, RedirectAttributes redirectAttributes) {
        SystemUserEntity user=userService.getCurrent();


        acg12CollectCaricatureService.save(acg12CollectCaricature);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model) {
        Acg12CollectCaricatureEntity acg12CollectCaricature = acg12CollectCaricatureService.find(id);
        model.addAttribute("acg12CollectCaricature", acg12CollectCaricature);
        return "/admin/acg12_collect_caricature/edit";
    }


    /**
     * 详情
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, ModelMap model) {
        Acg12CollectCaricatureEntity acg12CollectCaricature = acg12CollectCaricatureService.find(id);
        model.addAttribute("acg12CollectCaricature", acg12CollectCaricature);
        return "/admin/acg12_collect_caricature/view";
    }


    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Acg12CollectCaricatureEntity acg12CollectCaricature, RedirectAttributes redirectAttributes) {
        acg12CollectCaricatureService.update(acg12CollectCaricature);
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



        return "/admin/acg12_collect_caricature/list";
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
        String filter_userIds = request.getParameter("filter_userIds");
        String filter_userIde = request.getParameter("filter_userIde");
        if(!StringUtil.isEmpty(filter_userIds)) {
            map.put("filter_userIds", filter_userIds.replace("-", ""));
        }
        if(!StringUtil.isEmpty(filter_userIde)) {
            map.put("filter_userIde", filter_userIde.replace("-", ""));
        }
        String filter_comicIds = request.getParameter("filter_comicIds");
        String filter_comicIde = request.getParameter("filter_comicIde");
        if(!StringUtil.isEmpty(filter_comicIds)) {
            map.put("filter_comicIds", filter_comicIds.replace("-", ""));
        }
        if(!StringUtil.isEmpty(filter_comicIde)) {
            map.put("filter_comicIde", filter_comicIde.replace("-", ""));
        }
        String filter_types = request.getParameter("filter_types");
        String filter_typee = request.getParameter("filter_typee");
        if(!StringUtil.isEmpty(filter_types)) {
            map.put("filter_types", filter_types.replace("-", ""));
        }
        if(!StringUtil.isEmpty(filter_typee)) {
            map.put("filter_typee", filter_typee.replace("-", ""));
        }
        String filter_createtimes = request.getParameter("filter_createtimes");
        String filter_createtimee = request.getParameter("filter_createtimee");
        if(!StringUtil.isEmpty(filter_createtimes)) {
            map.put("filter_createtimes", filter_createtimes.replace("-", ""));
        }
        if(!StringUtil.isEmpty(filter_createtimee)) {
            map.put("filter_createtimee", filter_createtimee.replace("-", ""));
        }
        String filter_updatetimes = request.getParameter("filter_updatetimes");
        String filter_updatetimee = request.getParameter("filter_updatetimee");
        if(!StringUtil.isEmpty(filter_updatetimes)) {
            map.put("filter_updatetimes", filter_updatetimes.replace("-", ""));
        }
        if(!StringUtil.isEmpty(filter_updatetimee)) {
            map.put("filter_updatetimee", filter_updatetimee.replace("-", ""));
        }

        Long total = acg12CollectCaricatureService.count(map);

        map.put("pageNumber", (pageNumber - 1) * pageSize);
        map.put("pageSize", pageSize);
        map= this.dateAndOrderMap(map, request);

        List<Acg12CollectCaricatureEntity> acg12CollectCaricatures = acg12CollectCaricatureService.findListNewByPage(map);
        Map returnMap = new HashMap();
        returnMap.put("total", total);
        returnMap.put("rows", acg12CollectCaricatures);
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
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("userId")){
            map.put("order",  " user_id  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("comicId")){
            map.put("order",  " comic_id  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("type")){
            map.put("order",  " type  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("cover")){
            map.put("order",  " cover  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("title")){
            map.put("order",  " title  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("createtime")){
            map.put("order",  " createTime  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("updatetime")){
            map.put("order",  " updateTime  "+ sortOrder);
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
    public @ResponseBody
    Message delete(Long[] ids) {
        for (Long long1 : ids) {
            this.acg12CollectCaricatureService.delete(long1);
        }
        return SUCCESS_MESSAGE;
    }

    /**
     * 删除操作
     *
     * @return
     */
    @RequestMapping(value = { "/deletes" }, method = { RequestMethod.POST,RequestMethod.GET })
    public @ResponseBody Message deletes(HttpServletRequest request) {

        String idsStrings=request.getParameter("ids");
        idsStrings=StringUtil.delChar(idsStrings, ",");
        if(!StringUtil.isEmpty(idsStrings)){
            String[] idss=idsStrings.split(",");
            for (int i = 0; i < idss.length; i++) {
                this.acg12CollectCaricatureService.delete(Long.parseLong(idss[i]));
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
