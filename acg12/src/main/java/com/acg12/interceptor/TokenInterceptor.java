package com.acg12.interceptor;

import com.framework.loippi.utils.web.WebUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/10/15 18:30
 * Description:
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    /** "令牌"属性名称 */
    private static final String TOKEN_ATTRIBUTE_NAME = "token";

    /** "令牌"Cookie名称 */
    private static final String TOKEN_COOKIE_NAME = "token";

    /** "令牌"参数名称 */
    private static final String TOKEN_PARAMETER_NAME = "token";

    /** 错误消息 */
    private static final String ERROR_MESSAGE = "Bad or missing token!";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = WebUtils.getCookie(request, TOKEN_COOKIE_NAME);
        if (request.getMethod().equalsIgnoreCase("POST")) {
//			String requestType = request.getHeader("X-Requested-With");
//			if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
//				if (token != null && token.equals(request.getHeader(TOKEN_PARAMETER_NAME))) {
//					WebUtils.removeCookie(request, response, TOKEN_COOKIE_NAME);
//					return true;
//				} else {
//					response.addHeader("tokenStatus", "accessDenied");
//				}
//			} else {
//				if (token != null && token.equals(request.getParameter(TOKEN_PARAMETER_NAME))) {
//					WebUtils.removeCookie(request, response, TOKEN_COOKIE_NAME);
//					return true;
//				}
//			}
//			if (token == null) {
//				WebUtils.addCookie(request, response, TOKEN_COOKIE_NAME, DigestUtils.md5Hex(UUID.randomUUID() + RandomStringUtils.randomAlphabetic(30)));
//			}
//			response.sendError(HttpServletResponse.SC_FORBIDDEN, ERROR_MESSAGE);
//			return false;
            return true;
        } else {
            if (token == null) {
                token = DigestUtils.md5Hex(UUID.randomUUID() + RandomStringUtils.randomAlphabetic(30));
                WebUtils.addCookie(request, response, TOKEN_COOKIE_NAME, token);
            }
            request.setAttribute(TOKEN_ATTRIBUTE_NAME, token);
            return true;
        }
    }
}
