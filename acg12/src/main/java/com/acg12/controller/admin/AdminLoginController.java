package com.acg12.controller.admin;

import com.acg12.controller.GenericController;
import com.acg12.service.RSAService;
import com.acg12.shiro.Principal;
import com.acg12.support.Message;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/9/11 16:05
 * Description: 登录页面
 */
@Controller("adminLoginController")
@RequestMapping("/admin")
public class AdminLoginController extends GenericController { //

    @Resource
    private RSAService rsaService;

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(HttpServletRequest request) {
        System.out.println("=============================");
        return "gdfsdfdsf";
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) { //
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null && principal.getId() != null) {
                return "redirect:/admin/common/main.html";
            }
        }

        RSAPublicKey publicKey = null;
        try {
            publicKey = rsaService.generateKey(request);
            String modulus = Base64.encodeBase64String(publicKey.getModulus().toByteArray());
            String exponent = Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray());
            System.out.println(modulus);
            System.out.println(exponent);
            model.addAttribute("modulus", modulus);
            model.addAttribute("exponent", exponent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (publicKey == null) {
            return "redirect:/admin/common/main.html";
        }

        String captchaId = UUID.randomUUID().toString();
        model.addAttribute("captchaId", captchaId);
        return "/admin/login/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String index(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String message = null;
        String loginFailure = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if (StringUtils.isNotEmpty(loginFailure)) {
            if (loginFailure.equals("org.apache.shiro.authc.pam.UnsupportedTokenException")) {
                message = "admin.captcha.invalid";
            } else if (loginFailure.equals("org.apache.shiro.authc.UnknownAccountException")) {
                message = "admin.login.unknownAccount";
            } else if (loginFailure.equals("org.apache.shiro.authc.DisabledAccountException")) {
                message = "admin.login.disabledAccount";
            } else if (loginFailure.equals("org.apache.shiro.authc.LockedAccountException")) {
                message = "admin.login.lockedAccount";
            } else if (loginFailure.equals("org.apache.shiro.authc.IncorrectCredentialsException")) {
                message = "admin.login.incorrectCredentials";
            } else if (loginFailure.equals("org.apache.shiro.authc.AuthenticationException")) {
                message = "admin.login.authentication";
            }
            addFlashMessage(redirectAttributes, Message.error(message));

            return "redirect:/admin/login.html";
        }

        return "redirect:/admin/common/main.html";
    }
}
