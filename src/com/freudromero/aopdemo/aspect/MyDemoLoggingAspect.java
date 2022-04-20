package com.freudromero.aopdemo.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // This is where we add all of our related advices for logging

    // Let's star with a @Before advice
    @Before("execution(public void add*())")
    public  void beforeAddAccountAdvice() {
        System.out.println("\n======>>>> executing @Before advice on addAccount()");

    }



}
