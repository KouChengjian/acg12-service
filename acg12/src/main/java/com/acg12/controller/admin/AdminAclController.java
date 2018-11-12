package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.dto.SystemAclDTO;
import com.acg12.entity.po.SystemAclEntity;
import com.acg12.service.SystemAclService;
import com.acg12.service.SystemRoleAclService;
import com.acg12.shiro.Principal;
import com.acg12.support.Message;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/9 14:00
 * Description:
 */
@Controller("adminAclController")
@RequestMapping("/admin/acl")
public class AdminAclController extends GenericController {

    @Resource
    private SystemAclService aclService;
    @Resource
    private SystemRoleAclService roleAclService;

    /**
     * 列表
     */
    @RequiresPermissions("admin:system:acl")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<SystemAclEntity> acls = aclService.findRoots();
        for (SystemAclEntity acl : acls) {
            acl.getChildren().addAll(aclService.findChildrens(acl.getId()));
        }
        model.addAttribute("acls", acls);
        return "/admin/acl/list";
    }

    /**
     * 获取ROOT菜单
     */
    @RequestMapping(value = "/ajax_roots", method = RequestMethod.GET)
    public @ResponseBody
    List<SystemAclDTO> ajax_roots() {
        return new SystemAclDTO().build(aclService.findRoots());
    }

    /**
     * 获取子菜单
     */
    @RequestMapping(value = "/ajax_children", method = RequestMethod.GET)
    public @ResponseBody
    List<SystemAclDTO> ajax_children(Long id) {
        return new SystemAclDTO().build(aclService.findChildrens(id));
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model) {
        model.addAttribute("acl", aclService.find(id));
        List<SystemAclEntity> acls = aclService.findRoots();
        for (SystemAclEntity acl : acls) {
            acl.getChildren().addAll(aclService.findChildrens(acl.getId()));
        }
        model.addAttribute("acls", acls);
        return "/admin/acl/edit";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        return "/admin/acl/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public String save(SystemAclEntity acl, RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        if (acl.getId() == null) {
            acl.setCreateDate(new Date());
            if (subject != null) {
                Principal principal = (Principal) subject.getPrincipal();
                if (principal != null && principal.getId() != null) {
                    acl.setCreator(principal.getId());
                }
            }
            aclService.save(acl);
        } else {
            if (subject != null) {
                Principal principal = (Principal) subject.getPrincipal();
                if (principal != null && principal.getId() != null) {
                    acl.setUpdator(principal.getId());
                }
            }
            acl.setUpdateDate(new Date());
            aclService.update(acl);
        }
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long id) {
        if (CollectionUtils.isEmpty(aclService.findChildrens(id))) {
            aclService.delete(id);
            return SUCCESS_MESSAGE;
        }
        return Message.error("不可删除,包含子菜单!");
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(SystemAclEntity acl, RedirectAttributes redirectAttributes) {
        acl.setUpdateDate(new Date());
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null && principal.getId() != null) {
                acl.setUpdator(principal.getId());
            }
        }
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }
}
