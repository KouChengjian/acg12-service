package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.SystemGenSchemeEntity;
import com.acg12.entity.po.SystemGenTableEntity;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.SystemGenSchemeService;
import com.acg12.service.SystemGenTableService;
import com.acg12.service.SystemUserService;
import com.acg12.support.Message;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;
import com.loippi.core.gen.strategy.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
 * Date: 2018/11/13 16:19
 * Description: 生成方案
 */
@Controller("adminGenSechemeController")
@RequestMapping("/admin/gen_scheme")
public class AdminGenSchemeController extends GenericController {

    @Resource
    private SystemUserService userService;
    @Resource
    private SystemGenSchemeService schemeService;
    @Resource
    private SystemGenTableService tableService;

    /**
     * 列表
     */
    @RequiresPermissions("admin:gen:scheme")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, HttpServletRequest request, ModelMap model) {
        processQueryConditions(pageable, request);
        Page<SystemGenSchemeEntity> page = schemeService.findByPage(pageable);
        model.addAttribute("paramter", pageable.getParameter());
        model.addAttribute("page", page);
        return "/admin/gen_scheme/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        List<SystemGenTableEntity> tables = tableService.findAll();
        model.addAttribute("tables", tables);
        return "/admin/gen_scheme/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request, SystemGenSchemeEntity scheme, RedirectAttributes redirectAttributes) {
        SystemUserEntity user = userService.getCurrent();
        scheme.setCreateDate(new Date());
        scheme.setUpdateDate(new Date());
        scheme.setStrategy(1);
        scheme.setCreator(user.getId());
        scheme.setUpdator(user.getId());
        schemeService.save(scheme);
        generate(request, scheme, tableService.find(scheme.getGenTableId()));
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 生成
     */
    @RequestMapping(value = "/gen", method = RequestMethod.POST)
    public @ResponseBody Message gen(HttpServletRequest request, Long id) {
        SystemGenSchemeEntity scheme = schemeService.find(id);
        generate(request, scheme, tableService.find(scheme.getGenTableId()));
        return SUCCESS_MESSAGE;
    }


    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Message delete(Long[] ids) {
        schemeService.deleteAll(ids);
        return SUCCESS_MESSAGE;
    }


    private void generate(HttpServletRequest request, SystemGenSchemeEntity scheme, SystemGenTableEntity table) {
        String fullPath = this.getClass().getResource("").getPath().replace("classes/com/framework/loippi/controller/admin/", "generated-codes");

        fullPath = "D:/daima/shangyi";

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("scheme", scheme);
        data.put("table", table);
        Context context = null;
        if (scheme.getTemplate() == 1) {
            context = new Context(new OnlyPersistenceStrategy(), data, fullPath, table.getClassName(), scheme.getPackageName());
        } else if (scheme.getTemplate() == 2) {
            context = new Context(new OnlyBusinessStrategy(), data, fullPath, table.getClassName(), scheme.getPackageName());
        } else if (scheme.getTemplate() == 3) {
            context = new Context(new OnlyViewStrategy(), data, fullPath, table.getClassName(), scheme.getPackageName());
        } else if (scheme.getTemplate() == 4) {
            context = new Context(new PersistenceBusinessStrategy(), data, fullPath, table.getClassName(), scheme.getPackageName());
        } else if (scheme.getTemplate() == 5) {
            context = new Context(new FullStrategy(), data, fullPath, table.getClassName(), scheme.getPackageName());
        }
        if (context != null) {
            context.generate();
        }
    }
}
