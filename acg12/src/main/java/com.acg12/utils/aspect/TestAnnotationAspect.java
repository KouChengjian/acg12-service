package com.acg12.utils.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/12/8.
 */
@Aspect
@Component
public class TestAnnotationAspect {

    public TestAnnotationAspect() {

    }

    //    @Pointcut("execution(* com.acg12.service.ResServiceImpl.*())")
    @Pointcut("execution(* com.acg12.service.ResServiceImpl.getIndex(..))")
    private void pointCutMethod() {
    }

    //声明前置通知
    @Before("pointCutMethod()")
    public void doBefore() {
        System.out.println("前置通知");
    }

    //声明后置通知
    @AfterReturning(pointcut = "pointCutMethod()") // , returning = "result"
    public void doAfterReturning() { //String result
        System.out.println("后置通知");
//        System.out.println("---" + result + "---");
    }

    //声明最终通知
    @After("pointCutMethod()")
    public void doAfter() {
        System.out.println("最终通知");
    }

    //声明环绕通知
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("进入方法---环绕通知");
        Object o = pjp.proceed();
        System.out.println("退出方法---环绕通知");
        return o;
    }

    //声明例外通知
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        System.out.println("例外通知");
        System.out.println(e.getMessage());
    }
}
