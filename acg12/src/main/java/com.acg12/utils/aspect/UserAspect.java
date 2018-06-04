package com.acg12.utils.aspect;

import com.acg12.config.Constant;
import com.acg12.entity.dto.Result;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/1/2.
 */
@Aspect
@Component
public class UserAspect {

    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    public UserAspect() {

    }

//    @Pointcut("execution(* com.acg12.web.api.admin.UserController.*(..))")
    private void pointCutMethod() {

    }

    //声明前置通知
//    @Before("pointCutMethod()")
    public void doBefore() throws Exception {
        Result r = new Result();
        if(!r.verifySign(request)){
            r.writeFailure(Constant.HTTP_RESULT_ERROR,"签名失败",response);
            throw new Exception("签名失败");
        }
//        System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
    }
}
