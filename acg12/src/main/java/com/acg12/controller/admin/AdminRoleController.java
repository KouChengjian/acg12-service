package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.SystemAclEntity;
import com.acg12.entity.po.SystemRoleEntity;
import com.acg12.service.SystemAclService;
import com.acg12.service.SystemRoleService;
import com.acg12.support.Message;
import com.framework.loippi.support.Pageable;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/9 17:53
 * Description:
 */
@Controller("adminRoleController")
@RequestMapping("/admin/role")
public class AdminRoleController extends GenericController {

    @Resource
    private SystemAclService aclService;

    @Resource
    private SystemRoleService roleService;

    /**
     * 列表
     */
    @RequiresPermissions("admin:system:role")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {
        model.addAttribute("page", roleService.findByPage(pageable));
        return "/admin/role/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        List<SystemAclEntity> roots = aclService.findRoots();
        Map parameter = new HashMap();
        parameter.put("order", "sorts asc");
        List<SystemAclEntity> aclAlls = aclService.findByParams(parameter);

        for (SystemAclEntity acl : roots) {
            List<SystemAclEntity> childrens = this.acls(aclAlls, acl.getId());
            for (SystemAclEntity child : childrens) {
                List<SystemAclEntity> btns = this.acls(aclAlls, child.getId());
                child.getChildren().addAll(btns);
            }
            acl.getChildren().addAll(childrens);

        }
        model.addAttribute("roots", roots);
        return "/admin/role/add";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model) {
        List<SystemAclEntity> roots = aclService.findRoots();
        Map parameter = new HashMap();
        parameter.put("order", "sorts asc");
        List<SystemAclEntity> aclAlls = aclService.findByParams(parameter);


        for (SystemAclEntity acl : roots) {
            List<SystemAclEntity> childrens = this.acls(aclAlls, acl.getId());
            for (SystemAclEntity child : childrens) {
                List<SystemAclEntity> btns = this.acls(aclAlls, child.getId());
                child.getChildren().addAll(btns);
            }
            acl.getChildren().addAll(childrens);

        }
        model.addAttribute("roots", roots);
        model.addAttribute("role", roleService.findRoleAndAcls(id));
        return "/admin/role/edit";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(SystemRoleEntity role, Long[] ids, RedirectAttributes redirectAttributes) {
        role.setUpdateDate(new Date());
        role.setCreateDate(new Date());
        roleService.save(role, ids);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(SystemRoleEntity role, Long[] ids, RedirectAttributes redirectAttributes) {
        roleService.update(role, ids);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Message delete(Long[] ids) {
        for (Long id : ids) {
            roleService.delete(id);
        }
        return SUCCESS_MESSAGE;
    }


    private List<SystemAclEntity> acls(List<SystemAclEntity> aclAlls, Long id) {
        List<SystemAclEntity> acls = new ArrayList();
        for (Iterator iterator = aclAlls.iterator(); iterator.hasNext(); ) {
            SystemAclEntity acl = (SystemAclEntity) iterator.next();
            if (id.equals(acl.getParentId())) {
                acls.add(acl);
            }
        }
        return acls;
    }
}
