package com.freudromero.aopdemo.aspect;


import com.freudromero.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.freudromero.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{

        // Print out which method are we advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n===========> Executing @Around on method: " + method);

        // Get begin timestamp
        long begin = System.currentTimeMillis();

        // Now, let's execute the method
        Object result = theProceedingJoinPoint.proceed();

        // Get the end timestamp
        long end = System.currentTimeMillis();

        // Compute duration and display it
        long duration = end - begin;
        System.out.println("\n=============> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

    @After("execution(* com.freudromero.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJointPoint) {

        // Print out which method are we advising on
        String method = theJointPoint.getSignature().toShortString();
        System.out.println("\n===========> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.freudromero.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint theJointPoint, Throwable theExc) {

        // Print out which method are we advising on
        String method = theJointPoint.getSignature().toShortString();
        System.out.println("\n===========> Executing @AfterTrowing on method: " + method);

        // Log the exception
        System.out.println("\n===========> The exception is: " + theExc);

    }

    @AfterReturning(
            pointcut = "execution(* com.freudromero.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJointPoint, List<Account> result){
        // Print out which method are we advising on
        String method = theJointPoint.getSignature().toShortString();
        System.out.println("\n===========> Executing @AfterReturning on method: " + method);

        // Print out the results of the method call
        System.out.println("\n===========> Result is: " + result);

        // Let's post-process the data ... let's modify it

        // Convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\n===========> Result is: " + result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        // Loop through accounts
        for(Account tempAccount : result) {
            // Get uppercase version of name
            // Update the name on the account
            tempAccount.setName(tempAccount.getName().toUpperCase());
        }
    }


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
