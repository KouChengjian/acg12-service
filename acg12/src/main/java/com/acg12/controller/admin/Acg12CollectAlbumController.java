package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.Acg12CollectAlbumEntity;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.Acg12CollectAlbumService;
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
 * Controller - CollectAlbum
 *
 * @author kcj
 * @version 2.0
 */
@Controller("adminAcg12CollectAlbumController")
@RequestMapping({ "/admin/acg12_collect_album" })
public class Acg12CollectAlbumController extends GenericController {


    @Resource
    private SystemUserService userService;

    @Resource
    private Acg12CollectAlbumService acg12CollectAlbumService;

    /**
     * 跳转添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
    public String add(ModelMap model) {
        return "/admin/acg12_collect_album/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Acg12CollectAlbumEntity acg12CollectAlbum, RedirectAttributes redirectAttributes) {
        SystemUserEntity user=userService.getCurrent();


        acg12CollectAlbumService.save(acg12CollectAlbum);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model) {
        Acg12CollectAlbumEntity acg12CollectAlbum = acg12CollectAlbumService.find(id);
        model.addAttribute("acg12CollectAlbum", acg12CollectAlbum);
        return "/admin/acg12_collect_album/edit";
    }


    /**
     * 详情
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, ModelMap model) {
        Acg12CollectAlbumEntity acg12CollectAlbum = acg12CollectAlbumService.find(id);
        model.addAttribute("acg12CollectAlbum", acg12CollectAlbum);
        return "/admin/acg12_collect_album/view";
    }


    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Acg12CollectAlbumEntity acg12CollectAlbum, RedirectAttributes redirectAttributes) {
        acg12CollectAlbumService.update(acg12CollectAlbum);
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



        return "/admin/acg12_collect_album/list";
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
        String filter_loves = request.getParameter("filter_loves");
        String filter_lovee = request.getParameter("filter_lovee");
        if(!StringUtil.isEmpty(filter_loves)) {
            map.put("filter_loves", filter_loves.replace("-", ""));
        }
        if(!StringUtil.isEmpty(filter_lovee)) {
            map.put("filter_lovee", filter_lovee.replace("-", ""));
        }
        String filter_favoritess = request.getParameter("filter_favoritess");
        String filter_favoritese = request.getParameter("filter_favoritese");
        if(!StringUtil.isEmpty(filter_favoritess)) {
            map.put("filter_favoritess", filter_favoritess.replace("-", ""));
        }
        if(!StringUtil.isEmpty(filter_favoritese)) {
            map.put("filter_favoritese", filter_favoritese.replace("-", ""));
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

        Long total = acg12CollectAlbumService.count(map);

        map.put("pageNumber", (pageNumber - 1) * pageSize);
        map.put("pageSize", pageSize);
        map= this.dateAndOrderMap(map, request);

        List<Acg12CollectAlbumEntity> acg12CollectAlbums = acg12CollectAlbumService.findListNewByPage(map);
        Map returnMap = new HashMap();
        returnMap.put("total", total);
        returnMap.put("rows", acg12CollectAlbums);
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
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("pinId")){
            map.put("order",  " pin_id  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("image")){
            map.put("order",  " image  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("linkUrl")){
            map.put("order",  " link_url  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("content")){
            map.put("order",  " content  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("love")){
            map.put("order",  " love  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("favorites")){
            map.put("order",  " favorites  "+ sortOrder);
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
            this.acg12CollectAlbumService.delete(long1);
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
                this.acg12CollectAlbumService.delete(Long.parseLong(idss[i]));
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
