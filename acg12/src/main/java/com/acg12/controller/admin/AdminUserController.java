package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.SystemRoleService;
import com.acg12.service.SystemUserService;
import com.acg12.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
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
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/8 10:40
 * Description:
 */
@Controller("adminUserController")
@RequestMapping("/admin/user")
public class AdminUserController extends GenericController {

    @Resource
    private SystemUserService userService;
    @Resource
    private SystemRoleService roleService;

    /**
     * 检查用户名是否存在
     */
    @RequestMapping(value = "/check_username", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return false;
        }
        if (userService.usernameExists(username)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        model.addAttribute("roles", roleService.findAll());
        return "/admin/user/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(SystemUserEntity user, RedirectAttributes redirectAttributes) {
        if (userService.usernameExists(user.getUsername())) {
            return ERROR_VIEW;
        }
        String password = user.getPassword();
        user.setPassword(DigestUtils.md5Hex(password));
        user.setIsLocked(1);
        user.setLoginFailureCount(0);
        user.setIsEnabled(1);
        user.setLockedDate(null);
        user.setLoginDate(null);
        user.setLoginIp(null);
        user.setCreateDate(new Date());
        userService.save(user);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", userService.find(id));
        return "/admin/user/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(SystemUserEntity user, RedirectAttributes redirectAttributes) {
        String password = user.getPassword();
        if (StringUtils.isNotEmpty(password)) {
            user.setPassword(DigestUtils.md5Hex(password));
        }
        user.setUpdateDate(new Date());
        userService.update(user);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 列表
     */
    @RequiresPermissions("admin:system:user")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Pageable pageable, ModelMap model) {
        Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
        pageable.setParameter(paramter);
        model.addAttribute("page", userService.findByPage(pageable));
        model.addAttribute("paramter", paramter);
        return "/admin/user/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody public Message delete(Long[] ids) {
        for (Long id : ids) {
            userService.delete(id);
        }

        return SUCCESS_MESSAGE;
    }
}
