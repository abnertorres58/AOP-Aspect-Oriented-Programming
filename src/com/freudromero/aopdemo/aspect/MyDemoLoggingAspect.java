package com.freudromero.aopdemo.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* com.freudromero.aopdemo.dao.*.*(..))")
    private void forDaoPackage(){};


    // Create pointcut for getter methods
    @Pointcut("execution(* com.freudromero.aopdemo.dao.*.get*(..))")
    private void getter() {}

    // Create a pointcut for setter methods
    @Pointcut("execution(* com.freudromero.aopdemo.dao.*.set*(..))")
    private void setter() {}

    // Combine pointcut: include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {}

    @Before("forDaoPackageNoGetterSetter()")
    public  void beforeAddAccountAdvice() {
        System.out.println("\n======>>>> executing @Before advice on addAccount()");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public  void performApiAnalytics() {
        System.out.println("\n======>>>> Performing API analytics");
    }

}
