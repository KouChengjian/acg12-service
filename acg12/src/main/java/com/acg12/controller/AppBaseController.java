package com.acg12.controller;

import com.acg12.constant.AppConstants;
import com.acg12.utils.RedisUtils;
import com.acg12.utils.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/12/19 11:15
 * Description:
 */
public class AppBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private RedisUtils redisUtils;
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
}
