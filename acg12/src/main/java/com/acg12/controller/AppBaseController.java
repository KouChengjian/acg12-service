package com.acg12.controller;

import cn.hutool.core.util.StrUtil;
import com.acg12.constant.AdminConstant;
import com.acg12.constant.AppConstants;
import com.acg12.entity.dto.UserDao;
import com.acg12.entity.po.Acg12UserEntity;
import com.acg12.utils.RandomGUIDUtil;
import com.acg12.utils.RedisUtils;
import com.acg12.utils.result.Result;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/12/19 11:15
 * Description:
 */
public class AppBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    protected HttpServletRequest request;
    @Resource
    private RedisUtils redisUtils;

    public static final Integer year10 = 3600 * 24 * 366 * 10;
    public static final Integer days170 = 3600 * 24 * 170;
    public static final Integer days2 = 3600 * 24 * 2;


    /**
     * 处理系统异常
     *
     * @return
     */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Result Exception(HttpServletRequest request, Exception e) {
        logger.error("系统错误", e);
        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();
        logger.info("uri=" + uri);
        logger.info("ip=" + ip);
        return Result.error(AppConstants.AppError5000000, "系统错误");
    }

    /**
     * 验证码缓存相关
     */
    public UserDao setRedisDoctorLogin(Acg12UserEntity acg12UserEntity) {
        UserDao userDao = new UserDao();
        BeanUtils.copyProperties(acg12UserEntity, userDao);

        RandomGUIDUtil myGUID = new RandomGUIDUtil();
        String sessionId = myGUID.valueAfterMD5;

        String regex = StrUtil.format(AdminConstant.USER_TOKEN_KEY_TEMPLATE, acg12UserEntity.getId(), "*");
        List<String> keySet = redisUtils.scan(regex);

        if (keySet != null) {
            String[] sessionIds = keySet.stream().map(e -> {
                String id = "";
                String[] split = e.split(":");
                if (split.length >= 3) {
                    id = split[2];
                }
                return StrUtil.format(AdminConstant.TOKEN_PRFFIX, id);
            }).toArray(String[]::new);
            redisUtils.del(sessionIds);
            redisUtils.del(keySet.stream().toArray(String[]::new));
        }
        userDao.setSessionId(sessionId);
        redisUtils.set(StrUtil.format(AdminConstant.TOKEN_PRFFIX, sessionId), JSONObject.fromObject(userDao).toString(), year10);
        //设置token索引缓存
        String key = StrUtil.format(AdminConstant.USER_TOKEN_KEY_TEMPLATE, userDao.getId(), userDao.getSessionId());
        redisUtils.set(key, key, year10);
        return userDao;
    }

    /**
     * 获取当前登录的用户
     *
     * @return
     */
    protected UserDao getCurrentUser() {
        UserDao loginUser = (UserDao) request.getAttribute("LoginUser");
        return loginUser;
    }
}
