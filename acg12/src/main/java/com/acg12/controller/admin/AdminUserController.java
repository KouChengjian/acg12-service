package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.service.SystemUserService;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    /**
     * 列表
     */
//    @RequiresPermissions("admin:system:user")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Pageable pageable, ModelMap model) {
        Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
        pageable.setParameter(paramter);
        model.addAttribute("page", userService.findByPage(pageable));
        model.addAttribute("paramter", paramter);
        return "/admin/user/list";
    }

}
