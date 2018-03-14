package com.acg12.utils.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/12/8.
 */
@Aspect
@Component
public class TestAnnotationAspect {

    public TestAnnotationAspect() {

    }

//    @Pointcut("execution(* com.acg12.web.admin.UserController.*(..))")
    private void pointCutMethod1() {
    }

    //声明前置通知
//    @Before("pointCutMethod1()")
    public void doBefore(JoinPoint point) {
        System.out.println("前置通知");
        System.out.println("@Before：模拟权限检查...");
        System.out.println("@Before：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
    }

    //声明后置通知
//    @AfterReturning(pointcut = "pointCutMethod1()") // , returning = "result"
    public void doAfterReturning() { //String result
        System.out.println("后置通知");
//        System.out.println("---" + result + "---");
    }

    //声明最终通知
//    @After("pointCutMethod1()")
    public void doAfter() {
        System.out.println("最终通知");
    }

    //声明环绕通知
//    @Around("pointCutMethod1()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("进入方法---环绕通知");
        Object o = pjp.proceed();
        System.out.println("退出方法---环绕通知");
        return o;
    }

    //声明例外通知
//    @AfterThrowing(pointcut = "pointCutMethod1()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        System.out.println("例外通知");
        System.out.println(e.getMessage());
    }
}
