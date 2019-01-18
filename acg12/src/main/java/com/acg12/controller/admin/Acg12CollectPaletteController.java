package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.Acg12CollectPaletteEntity;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.Acg12CollectPaletteService;
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
 * Controller - CollectPalette
 *
 * @author kcj
 * @version 2.0
 */
@Controller("adminAcg12CollectPaletteController")
@RequestMapping({ "/admin/acg12_collect_palette" })
public class Acg12CollectPaletteController extends GenericController {


    @Resource
    private SystemUserService userService;

    @Resource
    private Acg12CollectPaletteService acg12CollectPaletteService;

    /**
     * 跳转添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
    public String add(ModelMap model) {
        return "/admin/acg12_collect_palette/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Acg12CollectPaletteEntity acg12CollectPalette, RedirectAttributes redirectAttributes) {
        SystemUserEntity user=userService.getCurrent();


        acg12CollectPaletteService.save(acg12CollectPalette);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model) {
        Acg12CollectPaletteEntity acg12CollectPalette = acg12CollectPaletteService.find(id);
        model.addAttribute("acg12CollectPalette", acg12CollectPalette);
        return "/admin/acg12_collect_palette/edit";
    }


    /**
     * 详情
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, ModelMap model) {
        Acg12CollectPaletteEntity acg12CollectPalette = acg12CollectPaletteService.find(id);
        model.addAttribute("acg12CollectPalette", acg12CollectPalette);
        return "/admin/acg12_collect_palette/view";
    }


    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Acg12CollectPaletteEntity acg12CollectPalette, RedirectAttributes redirectAttributes) {
        acg12CollectPaletteService.update(acg12CollectPalette);
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



        return "/admin/acg12_collect_palette/list";
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
        String filter_nums = request.getParameter("filter_nums");
        String filter_nume = request.getParameter("filter_nume");
        if(!StringUtil.isEmpty(filter_nums)) {
            map.put("filter_nums", filter_nums.replace("-", ""));
        }
        if(!StringUtil.isEmpty(filter_nume)) {
            map.put("filter_nume", filter_nume.replace("-", ""));
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

        Long total = acg12CollectPaletteService.count(map);

        map.put("pageNumber", (pageNumber - 1) * pageSize);
        map.put("pageSize", pageSize);
        map= this.dateAndOrderMap(map, request);

        List<Acg12CollectPaletteEntity> acg12CollectPalettes = acg12CollectPaletteService.findListNewByPage(map);
        Map returnMap = new HashMap();
        returnMap.put("total", total);
        returnMap.put("rows", acg12CollectPalettes);
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
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("boardId")){
            map.put("order",  " board_id  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("title")){
            map.put("order",  " title  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("sign")){
            map.put("order",  " sign  "+ sortOrder);
        }
        if(!StringUtil.isEmpty(sortName)&&sortName.equals("num")){
            map.put("order",  " num  "+ sortOrder);
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
            this.acg12CollectPaletteService.delete(long1);
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
                this.acg12CollectPaletteService.delete(Long.parseLong(idss[i]));
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
