package com.acg12.shiro.realm;

import com.acg12.constant.Constant;
import com.acg12.entity.po.SystemAclEntity;
import com.acg12.entity.po.SystemRoleEntity;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.SystemRoleService;
import com.acg12.service.SystemUserService;
import com.acg12.shiro.AuthenticationToken;
import com.acg12.shiro.Principal;
import com.acg12.utils.HttpSend;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/10/16 17:09
 * Description: 权限认证
 */
public class AuthenticationRealm extends AuthorizingRealm {

    @Resource
    private SystemUserService systemUserService;

    @Resource
    private SystemRoleService systemRoleService;

    /**
     * 获取认证信息
     *
     * @param token 令牌
     * @return 认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) throws AuthenticationException {
        AuthenticationToken authenticationToken = (AuthenticationToken) token;
        String username = authenticationToken.getUsername();
        String password = new String(authenticationToken.getPassword());
        String ip = authenticationToken.getHost();
        String captchaId = authenticationToken.getCaptchaId();
        String captcha = authenticationToken.getCaptcha();
        //if (!captchaService.isValid(CaptchaType.adminLogin, captchaId, captcha)) {
        //	throw new UnsupportedTokenException();
        //}

//        JedisStringCache	jedisStringCache=	jedisCache.getJedisStringCache(captchaId);

        //验证验证码
//			System.err.println("jedisStringCache==="+jedisStringCache.get());
//			if (jedisStringCache.get()==null||captcha==null) {
//				throw new UnsupportedTokenException();
//			}
//
//			if(!jedisStringCache.get().toUpperCase().equals(captcha.toUpperCase())){
//				throw new UnsupportedTokenException();
//			}

        if (username != null && password != null) {
            SystemUserEntity user = null;
            try {
                user = systemUserService.find("username", username);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (user == null) {
                throw new UnknownAccountException();
            }

            if (user.getIsEnabled() != 1) {
                throw new DisabledAccountException();
            }

            if (user.getIsLocked() != 1) {
                throw new DisabledAccountException();
            }

            if (!DigestUtils.md5Hex(password).equals(user.getPassword())) {
                int loginFailureCount = user.getLoginFailureCount() + 1;
                if (loginFailureCount >= 5) {
                    user.setIsLocked(0);
                    user.setLockedDate(new Date());
                }
                user.setLoginFailureCount(loginFailureCount);

                //登录地址
                String str = HttpSend.getSend("http://restapi.amap.com/v3/ip?key=" + Constant.Gaode_webapi_key + "&ip=" + ip);
                JSONObject json = JSONObject.fromObject(str);
                user.setLoginadress(json.getString("province") + " " + json.getString("city"));

                systemUserService.update(user);
                throw new IncorrectCredentialsException();
            }
            user.setLoginIp(ip);
            user.setLoginDate(new Date());
            user.setLoginFailureCount(0);

            //登录地址
            String str = HttpSend.getSend("http://restapi.amap.com/v3/ip?key=" + Constant.Gaode_webapi_key + "&ip=" + ip);
            System.err.println(str);
            JSONObject json = JSONObject.fromObject(str);
            user.setLoginadress(json.getString("province") + " " + json.getString("city"));

            systemUserService.update(user);
            SystemRoleEntity role = systemRoleService.find(user.getRoleId());

            return new SimpleAuthenticationInfo(new Principal(user.getId(), StringUtils.isEmpty(user.getNickname()) ? username : user.getNickname(), role.getName(), user.getAvatar()), password, getName());
        }
        throw new UnknownAccountException();
    }

    /**
     * 获取授权信息
     *
     * @param principals
     *            principals
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
        if (principal != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            SystemUserEntity user = systemUserService.find(principal.getId());
            SystemRoleEntity role = systemRoleService.findRoleAndAcls(user.getRoleId());
            for (SystemAclEntity acl : role.getAuthorities()){
                if (org.apache.commons.lang.StringUtils.isNotEmpty(acl.getPermission())){
                    info.addStringPermission(acl.getPermission());
                }
            }
            return info;
        }
        return null;
    }


}
