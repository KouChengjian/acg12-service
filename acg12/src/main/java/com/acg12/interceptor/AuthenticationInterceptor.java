package com.acg12.interceptor;

import cn.hutool.core.util.StrUtil;
import com.acg12.constant.AdminConstant;
import com.acg12.constant.AppConstants;
import com.acg12.entity.dto.UserDao;
import com.acg12.utils.AuthenticationPatten;
import com.acg12.utils.RedisUtils;
import com.acg12.utils.StringUtil;
import com.acg12.utils.result.Result;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;


public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    private SySystemUserVersionLogService sySystemUserVersionLogService;

    @Autowired
    RedisUtils redisUtils;

    public static final Integer min15 = 60 * 15;
    private static final Long BANGSHOU_TYPE = 1L;
    private String sessionName = "sessionId";
    private String GET = "GET";
    private String POST = "POST";
    private String ALL = "ALL";
    // 不需要登录的接口控制
    private Set<AuthenticationPatten> freeSet = new HashSet<>();
    // 需要签名的并且防重复的url了解一下
    private Set<AuthenticationPatten> needSignAndCantRepeatSet = new HashSet<>();

    //防盗链
    private final static String EABLE_REFERER = "https://api.shangyizhijia.com";
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");

    {
        freeSet.add(new AuthenticationPatten(ALL, Pattern.compile("/api/app/common/login.json")));
        freeSet.add(new AuthenticationPatten(ALL, Pattern.compile("/api/app/common/register.json")));
        freeSet.add(new AuthenticationPatten(ALL, Pattern.compile("/api/app/common/verify.json")));
        freeSet.add(new AuthenticationPatten(ALL, Pattern.compile("/api/app/common/index.json")));
        freeSet.add(new AuthenticationPatten(ALL, Pattern.compile("/api/app/common/newList.json")));
        freeSet.add(new AuthenticationPatten(ALL, Pattern.compile("/api/app/common/calendarList.json")));
        freeSet.add(new AuthenticationPatten(ALL, Pattern.compile("/api/app/common/albumList.json")));
        freeSet.add(new AuthenticationPatten(ALL, Pattern.compile("/api/app/common/boardList.json")));
        freeSet.add(new AuthenticationPatten(ALL, Pattern.compile("/api/app/common/boardList/albums.json")));

        needSignAndCantRepeatSet.add(new AuthenticationPatten(POST, Pattern.compile("/api/app/user/cancelWithdrawal.json"))); //取消提现
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();//2、结束时间
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long consumeTime = endTime - beginTime;//3、消耗的时间
        if (consumeTime > 500) {//此处认为处理时间超过500毫秒的请求为慢请求
            System.err.println(
                    String.format("AuthenticationInterceptor " + request.getMethod() + " ====> %s consume %d millis", request.getRequestURI(), consumeTime));
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long beginTime = System.currentTimeMillis();//1、开始时间
        startTimeThreadLocal.set(beginTime);

        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();
        System.err.println(AuthenticationInterceptor.class.getSimpleName() + ":" + request.getMethod().toUpperCase() + ":" + uri);

        for (AuthenticationPatten authenticationPatten : freeSet) {
            if (authenticationPatten.getPattern().matcher(uri).find()) {
                String method = request.getMethod().toUpperCase();
                if (authenticationPatten.getMethod().equals(ALL)) {
                    return true;
                }
                if (authenticationPatten.getMethod().equals(method)) {
                    return true;
                }
                return false;
            }
        }

        /**
         * 签名和放重复接口验证
         */
//        if (sysConstant.getSignValidateOpen() != null && sysConstant.getSignValidateOpen()) {
//            for (AuthenticationPatten authenticationPatten : needSignAndCantRepeatSet) {
//                if (authenticationPatten.getPattern().matcher(uri).find()) {
//                    if (!vali(request, response, false)) {
//                        return false;
//                    }
//                }
//            }
//
//            /**
//             * 接口签名验证
//             */
//
//            if (!vali(request, response, true)) {
//                return false;
//            }
//        }

        String sessionId = request.getHeader(sessionName);
        if (StringUtil.isEmpty(sessionId)) {
            sessionId = request.getParameter(sessionName);
        }
        if (StringUtil.isEmpty(sessionId)) {
            responseJsonMessage(response, Result.error(AppConstants.AppError5000101, "用户未登录").toString());
            return false;
        }

        // 判断LoginUser是否过期
        UserDao loginUser = validateRedisLoginUser(sessionId);
        if (loginUser == null) {
            responseJsonMessage(response, Result.error(AppConstants.AppError5000103, "会话信息失效").toString());
            return false;
        }

        request.setAttribute("LoginUser", loginUser);
        return true;
    }

    public UserDao validateRedisLoginUser(String sessionId) {
        if (StringUtil.isEmpty(sessionId)) {
            return null;
        }
        String sessionIdRedis = redisUtils.get(StrUtil.format(AdminConstant.TOKEN_PRFFIX, sessionId));
        if (sessionIdRedis == null) {
            return null;
        }

        JSONObject jsonObject = JSONObject.fromObject(sessionIdRedis);// 将json字符串转换为json对象

        UserDao loginUser = (UserDao) JSONObject.toBean(jsonObject, UserDao.class);// 将建json对象转换为Person对象

        String key = StrUtil.format(AdminConstant.USER_TOKEN_KEY_TEMPLATE, loginUser.getId(), loginUser.getSessionId());
        redisUtils.set(key, System.currentTimeMillis());
        return loginUser;
    }

    private void responseJsonMessage(HttpServletResponse response, String msg) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证
     * 签名 防重复
     *
     * @param request
     * @param response
     * @return
     */
//    public boolean vali(HttpServletRequest request, HttpServletResponse response, boolean onlySign) {
//
//        /**
//         * 测试阶段使用
//         */
//        if (request.getHeader("isSignTest") == null) {
//            return true;
//        }
//
//        /**
//         * 防盗链
//         */
//        String referer = request.getHeader("referer");
//        if (referer == null || !referer.startsWith(EABLE_REFERER)) {
//            responseJsonMessage(response, ReturnJson.jsonStringError("打开的方式不对", AppRequestValiUtil.REFERER_ERROR));
//            return false;
//        }
//
//        //app签名
//        String requestSign = request.getHeader(AppRequestValiUtil.SIGN_KEY);
//        //手机唯一标识
//        String requestPhoneCode = request.getHeader(AppRequestValiUtil.IDENTIFICATION);
//        //请求时间
//        String requestTime = request.getHeader(AppRequestValiUtil.REQTIME);
//
//        if (requestSign == null || requestPhoneCode == null || requestTime == null) {
//            responseJsonMessage(response, ReturnJson.jsonStringError("签名错误", AppRequestValiUtil.SIGN_ERROR));
//            return false;
//        }
//
//        String paramters = AppRequestValiUtil.getParamters(request);
//        //后台签名
//        String sign = AppRequestValiUtil.sign(requestPhoneCode, paramters, requestTime);
//
//        if (!sign.equalsIgnoreCase(requestSign)) {
//            //签名错误
//            responseJsonMessage(response, ReturnJson.jsonStringError("签名错误", AppRequestValiUtil.SIGN_ERROR));
//            return false;
//        }
//
//        //如果只需要签名则通过
//        if (onlySign) {
//
//            return true;
//        }
//
//        /**
//         * 防签名重复
//         */
//        String paramsMd5 = AppRequestValiUtil.getParams(paramters);
//        String key = requestPhoneCode + ":" + paramsMd5;
//
//        String value = redisUtils.get(key);
//
//        if (value == null) {
//            redisUtils.set(key, paramsMd5, AppRequestValiUtil.SIGN_EXPIRE_TIME);
//            return true;
//        } else {
//
//            responseJsonMessage(response, ReturnJson.jsonStringError("重复请求请一分钟后重试", AppRequestValiUtil.REPEAT_REQUEST_ERROR));
//            return false;
//        }
//
//    }
}
