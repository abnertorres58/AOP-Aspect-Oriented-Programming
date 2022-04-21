package com.freudromero.aopdemo.aspect;


import com.freudromero.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
    @Before("com.freudromero.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public  void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n======>>>> executing @Before advice on addAccount()");

        // Display the method signature
        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method: " + methodSig);

        // Display method arguments

        // Get the args
        Object[] args = theJoinPoint.getArgs();

        // Loop through args
        for(Object tempArg : args) {
            System.out.println(tempArg);

            if(tempArg instanceof Account) {

                // Downcast and print Account Specific stuffs
                Account theAccount = (Account) tempArg;

                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }
    }
}
