package com.freudromero.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

    @Pointcut("execution(* com.freudromero.aopdemo.dao.*.*(..))")
    public void forDaoPackage(){};


    // Create pointcut for getter methods
    @Pointcut("execution(* com.freudromero.aopdemo.dao.*.get*(..))")
    public void getter() {}

    // Create a pointcut for setter methods
    @Pointcut("execution(* com.freudromero.aopdemo.dao.*.set*(..))")
    public void setter() {}

    // Combine pointcut: include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}
}
