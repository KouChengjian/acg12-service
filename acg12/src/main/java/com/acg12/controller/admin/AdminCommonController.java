package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.entity.po.SystemAclEntity;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.SystemAclService;
import com.acg12.service.SystemUserService;
import com.acg12.shiro.Principal;
import com.acg12.utils.StringUtil;
import com.framework.loippi.support.Pageable;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/10/17 16:59
 * Description:
 */
@Controller("adminCommonController")
@RequestMapping("/admin/common")
public class AdminCommonController extends GenericController implements ServletContextAware {

    @Value("${system.name}")
    private String systemName;
    @Value("${system.version}")
    private String systemVersion;
    @Value("${system.description}")
    private String systemDescription;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Resource
    private SystemAclService aclService;
    @Resource
    private SystemUserService userService;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request, ModelMap model) {
        model.addAttribute("systemName", systemName);
        model.addAttribute("systemVersion", systemVersion);
        model.addAttribute("systemDescription", systemDescription);
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        model.addAttribute("javaHome", System.getProperty("java.home"));
        model.addAttribute("osName", System.getProperty("os.name"));
        model.addAttribute("osArch", System.getProperty("os.arch"));
        model.addAttribute("serverInfo", servletContext.getServerInfo());
        model.addAttribute("servletVersion", servletContext.getMajorVersion() + "." + servletContext.getMinorVersion());
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null && principal.getId() != null) {
                model.addAttribute("principal", principal);
            }
        }

        Map map = new HashMap();
        map.put("order", "PARENT_ID,sorts");
        List<SystemAclEntity> allAcls = aclService.findByParams(map);
        List<SystemAclEntity> acls = aclService.findRoots();
        for (SystemAclEntity acl : acls) {
            acl.getChildren().addAll(this.getChildrens(allAcls, acl.getId()));
        }
        model.addAttribute("acls", acls);

        SystemUserEntity user = userService.getCurrent();
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("logindate", StringUtil.date2String42(user.getLoginDate()));
        model.addAttribute("loginaddress", user.getLoginadress());

        if (user != null && user.getTheme() != null && user.getTheme() == 2) {
            return "/admin/common/main_classic";
        }
        return "/admin/common/main";
    }

    private List<SystemAclEntity> getChildrens(List<SystemAclEntity> allAcls, Long pId) {
        List<SystemAclEntity> childrens = new ArrayList();
        for (Iterator iterator = allAcls.iterator(); iterator.hasNext(); ) {
            SystemAclEntity acl = (SystemAclEntity) iterator.next();
            if (pId.equals(acl.getParentId())) {
                childrens.add(acl);
            }
        }
        return childrens;
    }

    /**
     * 首页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Pageable pageable, ModelMap model) {
        SystemUserEntity user = userService.getCurrent();
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("logindate",StringUtil.date2String42(user.getLoginDate()) );
        model.addAttribute("loginaddress", user.getLoginadress());

        model.addAttribute("systemName", systemName);
        model.addAttribute("systemVersion", systemVersion);
        model.addAttribute("systemDescription", systemDescription);
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        model.addAttribute("javaHome", System.getProperty("java.home"));
        model.addAttribute("osName", System.getProperty("os.name"));
        model.addAttribute("osArch", System.getProperty("os.arch"));
        model.addAttribute("serverInfo", servletContext.getServerInfo());
        model.addAttribute("servletVersion", servletContext.getMajorVersion() + "." + servletContext.getMinorVersion());

        return "/admin/common/index";
    }


    /**
     * 资源不存在
     */
    @RequestMapping("/resource_not_found")
    public String resourceNotFound() {
        return "/admin/common/resource_not_found";
    }

    /**
     * 注销
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        SecurityUtils.getSubject().logout();
        return "redirect:/admin/login.html?jssionid="+session.getId();
    }
}
