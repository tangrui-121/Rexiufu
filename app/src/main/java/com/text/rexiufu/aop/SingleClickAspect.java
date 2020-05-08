package com.text.rexiufu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SingleClickAspect {

    @Pointcut("execution(@com.util.click.SingleClick * *(..))")
    public void methodClick() {
        System.out.println("methodClick................");
    }// 该方法不会被执行

    @Before("methodClick()")
    public void before() {
        System.out.println("before................");
    }

    @After("methodClick()")
    public void after() {
        System.out.println("after.................");
    }

    @AfterReturning("methodClick()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("afterReturning.................");
    }

    @AfterThrowing("methodClick()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("afterThrowing...................");
    }


    @Around("methodClick()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before............");
        joinPoint.proceed(); //执行完成目标方法
        System.out.println("around after..............");
    }
}